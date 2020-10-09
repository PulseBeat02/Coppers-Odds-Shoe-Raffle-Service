package com.pulsebeat02.shoeraffleservice.application.privacy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.pulsebeat02.shoeraffleservice.application.application.session.Session;

public class Location {

    public String IP;
    public String hostname;
    public String city;
    public String region_state;
    public String country;
    public String organization;

    public int zipcode;

    public double[] coords;

    public Location(String IP, String hostname, String city, String region_state, String country, String organization,
	    int zipcode, double[] coords) {

	this.IP = IP;
	this.hostname = hostname;
	this.city = city;
	this.region_state = region_state;
	this.country = country;
	this.organization = organization;
	this.zipcode = zipcode;

	this.coords = coords;

    }

    public Location() throws IOException {

	String url = "https://ipinfo.io/" + getIP() + "/json";

	URL obj = new URL(url);

	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("GET");
	con.setRequestProperty("User-Agent", "Mozilla/5.0");

	System.out.println("\nSending 'GET' request to URL : " + url);
	System.out.println("Response Code : " + con.getResponseCode());

	BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();
	while ((inputLine = in.readLine()) != null) {
	    response.append(inputLine);
	}
	in.close();

	String output = response.toString();

	Object object = JSONValue.parse(output);
	JSONObject jsonObject = (JSONObject) object;

	this.IP = (String) jsonObject.get("ip");
	this.hostname = (String) jsonObject.get("hostname");
	this.city = (String) jsonObject.get("city");
	this.region_state = (String) jsonObject.get("region");
	this.country = (String) jsonObject.get("country");
	this.organization = (String) jsonObject.get("org");
	this.zipcode = Integer.parseInt((String) jsonObject.get("postal"));

	String[] coordinates = ((String) jsonObject.get("loc")).split(",");
	double[] coordinatesFinal = { Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]) };
	this.coords = coordinatesFinal;

    }

    public static String toStringText(Location location) {
	return location.IP;
    }

    public static Location getLocation(ConcurrentHashMap<String, Session> sessions, String ip) {
	return sessions.get(ip).location;
    }

    public static String getIP() throws IOException {

	URL whatismyip = new URL("http://checkip.amazonaws.com");
	BufferedReader in = null;

	try {
	    in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
	    String ip = in.readLine();
	    return ip;

	} finally {
	    if (in != null) {
		try {
		    in.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}

    }

}
