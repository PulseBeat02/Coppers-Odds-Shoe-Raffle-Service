package com.pulsebeat02.main.gui.windows.ads;

public class RandomSelection {

	public static void start() {

		for (int i = 0; i < 10; i++) {

			SetupVideo.start();

			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
