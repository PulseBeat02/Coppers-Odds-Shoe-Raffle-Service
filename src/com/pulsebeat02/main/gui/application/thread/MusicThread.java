package com.pulsebeat02.main.gui.application.thread;

import com.pulsebeat02.main.gui.application.MenuMusic;

public class MusicThread extends Thread {

    public int currentMusic = MenuMusic.uplifting;

    public void run() {
	run(currentMusic);
    }

    public void run(int music) {
	MenuMusic.playMusic(music);
    }

}
