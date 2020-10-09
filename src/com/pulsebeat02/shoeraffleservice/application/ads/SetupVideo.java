package com.pulsebeat02.shoeraffleservice.application.ads;

import java.io.IOException;
import java.util.Random;

public class SetupVideo {

    public static void main(String[] args) throws IOException {
	start();
    }

    public static void start() throws IOException {

	String[] links = ReadLinks.getLinks();
	String[] videoID = new String[links.length];

	for (int i = 0; i < links.length; i++) {
	    videoID[i] = links[i].substring(32, links[i].length());
	}

	PlayVideo.setupVideo(videoID[new Random().nextInt(videoID.length)]);

    }

}
