package com.pulsebeat02.shoeraffleservice.application.privacy;

import java.util.ArrayList;

import com.pulsebeat02.shoeraffleservice.application.application.session.ManageSessions;

public class SuspicousLogin extends Thread {

    public ArrayList<Location> locations = new ArrayList<Location>();

    @Override
    public void run() {
	for (;;) {
	    ManageSessions.check();
	    try {
		Thread.sleep(900000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

}
