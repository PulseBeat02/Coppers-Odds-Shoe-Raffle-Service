package com.pulsebeat02.main.gui.application.splash;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JProgressBar;
import java.awt.Font;
import javax.swing.SwingConstants;

import com.pulsebeat02.main.util.SplashScreenLoading;

import java.awt.Color;

public class LoadingScreen {

    public JFrame frmCoppersOddsIs;
    public boolean isDone;

    public LoadingScreen() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    initialize();
		    frmCoppersOddsIs.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public void initialize() {

	this.frmCoppersOddsIs = new JFrame();
	this.frmCoppersOddsIs.setTitle("Coppers Odds is Loading");
	this.frmCoppersOddsIs.setIconImage(Toolkit.getDefaultToolkit()
		.getImage(LoadingScreen.class.getResource("/com/pulsebeat02/main/resources/loadingscreen/co1.PNG")));
	this.frmCoppersOddsIs.setBounds(100, 100, 450, 255);
	this.frmCoppersOddsIs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.frmCoppersOddsIs.getContentPane().setLayout(null);
	this.frmCoppersOddsIs.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

	if (isDone) {

	    this.frmCoppersOddsIs.setVisible(false);
	    this.frmCoppersOddsIs.dispose();

	}

	JProgressBar progressBar = new JProgressBar();
	progressBar.setIndeterminate(true);
	progressBar.setForeground(Color.GRAY);
	progressBar.setBounds(10, 175, 414, 30);
	this.frmCoppersOddsIs.getContentPane().add(progressBar);

	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setBounds(22, 11, 64, 64);
	lblNewLabel.setIcon(new ImageIcon(
		LoadingScreen.class.getResource("/com/pulsebeat02/main/resources/images/loadingscreen.gif")));
	this.frmCoppersOddsIs.getContentPane().add(lblNewLabel);

	JLabel lblNewLabel_1 = new JLabel("Coppers Odds is Loading");
	lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
	lblNewLabel_1.setBounds(84, 11, 325, 64);
	this.frmCoppersOddsIs.getContentPane().add(lblNewLabel_1);

	JLabel lblNewLabel_2 = new JLabel("<htmL> Please wait, as Coppers Odds is loading the program for you.");
	lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
	lblNewLabel_2.setBounds(21, 95, 388, 53);
	this.frmCoppersOddsIs.getContentPane().add(lblNewLabel_2);

	while (true) {
	    if (!SplashScreenLoading.isLoaded) {
		this.frmCoppersOddsIs.setVisible(true);
	    } else {
		this.frmCoppersOddsIs.setVisible(false);
	    }

	}
    }
}
