package com.pulsebeat02.main.gui.windows.ads;

import java.util.Random;

public class SetupVideo {
	
	public static void main(String [] args) {
		
		start();
		
	}
	
	public static void start() {
		
		String [] links = ReadLinks.getLinks();
		String [] videoID = new String[links.length];
		
		for (int i = 0; i < links.length; i++) {
			
			videoID[i] = links[i].substring(32, links[i].length());
			
			System.out.println(videoID[i]);
			
		}
		
		Random rand = new Random();
		
		int randomInt = rand.nextInt(videoID.length);
		
		PlayVideo.setupVideo(videoID[randomInt]);
		
	}

}
