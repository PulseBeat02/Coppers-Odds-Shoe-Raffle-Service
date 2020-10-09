package com.pulsebeat02.main.gui.application.session;

import com.pulsebeat02.main.gui.application.thread.ManageThreads;

public class ClearSessions extends Thread {

    @Override
    public void run() {

	for (;;) {

	    try {
		Thread.sleep(28800000);
		ManageSessions.deleteOldSessions();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	    ManageThreads.utilityThreads.add(Thread.currentThread());

	}

    }

}
