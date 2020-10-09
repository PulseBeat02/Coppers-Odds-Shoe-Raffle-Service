package com.pulsebeat02.shoeraffleservice.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MenuMusic {
    
    public FileInputStream file = null;
    public Player playMP3 = null;

    public int off = 0;
    public int calm = 1;
    public int sad = 2;
    public int uplifting = 3;
    public int dreamy = 4;
    public int chooseFile = 5;

    public int current = 3;
    
    public void play() {
	playMusic(3);
    }

    public void playMusic(int type) {
	
	String cwd = System.getProperty("user.dir");
	
	// https://www.youtube.com/watch?v=9eK1Rfe1IIo

	if (type == off) {
	    playMP3.close();
	} else if (type == calm) {
	    try {
		file = new FileInputStream(cwd + "/src/com/pulsebeat02/main/resources/music/calm/calm.mp3");
		playMP3 = new Player(file);
		playMP3.play();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} else if (type == sad) {
	    try {
		file = new FileInputStream(cwd + "/src/com/pulsebeat02/main/resources/music/sad/sad.mp3");
		playMP3 = new Player(file);
		playMP3.play();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} else if (type == uplifting) {
	    try {
		file = new FileInputStream(cwd + "/src/com/pulsebeat02/main/resources/music/uplifting/happy.mp3");
		playMP3 = new Player(file);
		playMP3.play();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} else if (type == dreamy) {
	    try {
		file = new FileInputStream(cwd + "/src/com/pulsebeat02/main/resources/music/dreamy/dreamy.mp3");
		playMP3 = new Player(file);
		playMP3.play();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} else if (type == chooseFile) {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	    int result = fileChooser.showOpenDialog(fileChooser);
	    if (result == JFileChooser.APPROVE_OPTION) {
		File selectedFile = fileChooser.getSelectedFile();
		System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		try {
		    FileInputStream fileInput = new FileInputStream(selectedFile.getAbsolutePath());
		    try {
			playMP3 = new Player(fileInput);
		    } catch (JavaLayerException e1) {
			e1.printStackTrace();
		    }
		    try {
			playMP3.play();
		    } catch (JavaLayerException e) {
			e.printStackTrace();
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}

	    }

	}

    }

}
