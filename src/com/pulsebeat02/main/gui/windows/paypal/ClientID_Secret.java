package com.pulsebeat02.main.gui.windows.paypal;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ClientID_Secret {

	static String cwd = System.getProperty("user.dir");

	public static String getClientID() {

		try {
			List<String> lines = Files.readAllLines(Paths.get(cwd + "/Paypal-ClientID-Secret"), StandardCharsets.UTF_8);
			return lines.get(0).substring(10, lines.get(0).length());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e1) {
			
			return "empty";
			
		}
		
		return null;

	}

	public static String getSecret() {
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(cwd + "/Paypal-ClientID-Secret"), StandardCharsets.UTF_8);
			return lines.get(1).substring(7, lines.get(1).length());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e1) {
			
			return "empty";
			
		}
		
		return null;

	}

}
