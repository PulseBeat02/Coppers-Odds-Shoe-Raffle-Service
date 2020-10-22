package com.pulsebeat02.shoeraffleservice.application.session;

import java.io.IOException;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;

public class ClearSessions extends Thread {

    @Override
    public void run() {

	for (;;) {

	    try {
		Thread.sleep(28800000);
		ShoeRaffleService.service.getInstanceManager().MANAGE_SESSIONS.deleteOldSessions();
	    } catch (InterruptedException | IOException e) {
		e.printStackTrace();
	    }

	    ShoeRaffleService.service.getInstanceManager().MANAGE_THREADS.utilityThreads.add(Thread.currentThread());

	}

    }

}
