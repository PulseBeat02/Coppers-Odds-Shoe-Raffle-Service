package com.pulsebeat02.main.gui.windows.privacy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.pulsebeat02.main.gui.windows.session.Session;

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

	public Location() {

		String url = "https://ipinfo.io/" + getIP() + "/json";

		URL obj = null;
		try {
			obj = new URL(url);
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) obj.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// optional default is GET
		try {
			con.setRequestMethod("GET");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = 0;
		try {
			responseCode = con.getResponseCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inputLine;
		StringBuffer response = new StringBuffer();

		try {
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// print result
		String output = response.toString();
		
		System.out.println(output);

		Object object = JSONValue.parse(output);
		JSONObject jsonObject = (JSONObject) object;

//		public String IP;
//		public String hostname;
//		public String city;
//		public String region_state;
//		public String country;
//		public String organization;
//		public String zipcode;
//
//		public double[] coords;

//		{
//			  "ip": "8.8.8.8",
//			  "hostname": "google-public-dns-a.google.com",
//			  "city": "Mountain View",
//			  "region": "California",
//			  "country": "US",
//			  "loc": "37.3860,-122.0838",
//			  "org": "AS15169 Google Inc.",
//			  "postal": "94040"
//			}

		this.IP = (String) jsonObject.get("ip");
		this.hostname = (String) jsonObject.get("hostname");
		this.city = (String) jsonObject.get("city");
		this.region_state = (String) jsonObject.get("region");
		this.country = (String) jsonObject.get("country");
		this.organization = (String) jsonObject.get("org");
		this.zipcode = Integer.parseInt((String)jsonObject.get("postal"));

		String[] coordinates = ((String) jsonObject.get("loc")).split(",");

		double[] coordinatesFinal = { Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]) };
		this.coords = coordinatesFinal;
		
		
	}

	public static String toStringText(Location location) {

		String string = location.IP;

		return string;

	}

	public static Location getLocation(ConcurrentHashMap<String, Session> sessions, String ip) {

		return sessions.get(ip).location;

	}

	public static String getIP() {

		URL whatismyip = null;
		try {
			whatismyip = new URL("http://checkip.amazonaws.com");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
            String ip = in.readLine();
            return ip;
            
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return "";

	}

}
