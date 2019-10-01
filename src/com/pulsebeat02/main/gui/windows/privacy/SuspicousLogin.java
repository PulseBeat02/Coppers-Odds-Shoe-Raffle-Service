package com.pulsebeat02.main.gui.windows.privacy;

import java.util.ArrayList;

import com.pulsebeat02.main.gui.windows.session.ManageSessions;

public class SuspicousLogin extends Thread {

	static ArrayList<Location> locations = new ArrayList<Location>();
	
	@Override
	public void run() {

		for (;;) {

			ManageSessions.check();

			try {
				Thread.sleep(900000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
