package com.pulsebeat02.main.util.array_management;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Search {
	
	public static String sha256(String string) { 
		
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageDigest.update(string.getBytes());
		String encryptedString = new String(messageDigest.digest());
		
		return encryptedString;
		
	}

}
