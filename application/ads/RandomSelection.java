package com.pulsebeat02.shoeraffleservice.application.ads;

import java.io.IOException;

public class RandomSelection {

    public static void start() throws IOException {

	for (int i = 0; i < 10; i++) {

	    SetupVideo.start();
	    try {
		Thread.sleep(7000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}

    }

}
