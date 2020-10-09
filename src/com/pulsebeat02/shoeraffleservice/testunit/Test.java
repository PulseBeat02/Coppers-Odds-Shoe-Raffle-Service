package com.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {
	
	public static void main (String [] args) {
		
		if (isConnected()) {
			
			System.out.println("Wifi Connected");
			
		}
	}
	
	public static boolean isConnected() {

		try {
			return "127.0.0.1".equals(InetAddress.getLocalHost().getHostAddress().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
