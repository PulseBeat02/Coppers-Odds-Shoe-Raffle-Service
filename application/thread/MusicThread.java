package com.pulsebeat02.shoeraffleservice.application.thread;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;
import com.pulsebeat02.shoeraffleservice.application.MenuMusic;

public class MusicThread extends Thread {

    public int currentMusic = MenuMusic.uplifting;

    public void run() {
	run(currentMusic);
    }

    public void run(int music) {
	ShoeRaffleService.service.getInstanceManager().MENU_MUSIC.playMusic(music);
    }

}
