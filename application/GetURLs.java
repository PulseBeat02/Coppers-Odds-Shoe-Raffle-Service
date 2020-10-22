package com.pulsebeat02.shoeraffleservice.application;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

@Deprecated
public class GetURLs {

    static String cwd = System.getProperty("user.dir");

    public static String[] getLinks() {

	String[] links = new String[2];
	List<String> lines = null;

	try {
	    lines = Files.readAllLines(Paths.get(cwd + "/totalpayment.txt"), StandardCharsets.UTF_8);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	int currentLink = 0;

	for (int i = 0; i < lines.size(); i++) {

	    if (lines.get(i).contains("href")) {

		for (int z = 0; z < lines.get(i).length(); z++) {

		    char c = lines.get(i).charAt(z);

		    int quotationAppearance = 0;
		    int beginning = 0;
		    int ending = 0;

		    if (c == '"') {

			quotationAppearance++;

		    }

		    if (quotationAppearance == 3) {

			beginning = z;

		    }

		    else if (quotationAppearance == 4) {

			ending = z;

		    }

		    links[currentLink] = lines.get(i).substring(beginning + 1, ending - 1);

		    quotationAppearance = 0;

		}

		currentLink++;

	    }

	}

	return links;

    }

//	public static boolean isApproved() {
//		
//		String [] links = getLinks();
//
//		String command = "curl -v https://api.sandbox.paypal.com/v1/oauth2/token \\\n"
//				+ "   -H \"Accept: application/json\" \\\n" + "   -H \"Accept-Language: en_US\" \\\n"
//				+ "   -u \"AXWWfp8KsCf6kojBHqRj_T_UbMANBywrKpYKF4RYk8d4RAPJuKOLVTssutZFG3QaphfckVX5kMEK9ASq:EKhXVGxGanyOnEp5rzDryPqnu0nxXJA9l80U9S-mETsAVGyqtnD_g6r6ESjVI3AzBy4wczBdjqJ2mX2w\" \\\n"
//				+ "   -d \"grant_type=client_credentials\"";
//
//		String output = execCmd(command);
//
//		JSONParser parser = new JSONParser();
//		JSONObject json = null;
//		try {
//			json = (JSONObject) parser.parse(output);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		String access_token = (String) json.get("access_token");
//
//		String command2 = "curl -v -X GET " + links[0] + "\\\n" + "-H \"Content-Type: application/json\" \\\n"
//				+ "-H \"Authorization: Bearer " + access_token + "\"";
//
//		String outputtedResults = execCmd(command2);
//
//		try {
//			json = (JSONObject) parser.parse(outputtedResults);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		if (json.get("state").equals("APPROVED")) {
//
//			return true;
//
//		}
//
//		else {
//
//			return false;
//
//		}
//
//	}

    public static String getSelfURL() {

	return getLinks()[0];

    }

    public static String getApprovalURL() {

	return getLinks()[1];

    }

    public static String getExecuteURL() {

	return getLinks()[2];

    }

    public static String execCmd(String cmd) {
	Process proc = null;
	try {
	    proc = Runtime.getRuntime().exec(cmd);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	java.io.InputStream is = proc.getInputStream();
	java.util.Scanner s = new java.util.Scanner(is);
	s.useDelimiter("\\A");
	String val = "";
	if (s.hasNext()) {
	    val = s.next();
	} else {
	    val = "";
	}

	s.close();

	return val;
    }

}
