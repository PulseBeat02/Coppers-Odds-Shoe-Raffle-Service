package com.pulsebeat02.shoeraffleservice.application.privacy;

import java.util.ArrayList;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;

public class SuspicousLogin extends Thread {

    public ArrayList<Location> locations = new ArrayList<Location>();

    @Override
    public void run() {
	for (;;) {
	    ShoeRaffleService.service.getInstanceManager().MANAGE_SESSIONS.check();
	    try {
		Thread.sleep(900000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

}
