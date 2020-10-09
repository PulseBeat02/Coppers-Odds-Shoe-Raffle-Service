package com.pulsebeat02.main.gui.application.ads;

import javax.swing.SwingUtilities;

public class PlayVideo {

    public static void setupVideo(String videoID) {

	SwingUtilities.invokeLater(new Runnable() {

	    public void run() {
		PlayVideoJFrame.setUrl(
			"https://www.youtube.com/embed/" + videoID + "?autoplay=0&showinfo=0&controls=0&disablekb=1");
		System.out.println(PlayVideoJFrame.url);
		PlayVideoJFrame.startVideo(null);
	    }

	});

    }

}
