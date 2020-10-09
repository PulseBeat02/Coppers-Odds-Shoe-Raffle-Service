package com.pulsebeat02.shoeraffleservice.application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;

public class AboutUs {

    private JFrame frmAboutUs;

    public AboutUs() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    initialize();
		    frmAboutUs.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	this.frmAboutUs = new JFrame();
	this.frmAboutUs.getContentPane().setBackground(Color.LIGHT_GRAY);
	this.frmAboutUs.setTitle("About Us");
	this.frmAboutUs.setIconImage(Toolkit.getDefaultToolkit().getImage(
		AboutUs.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/infoButton/infoicon.png")));
	this.frmAboutUs.setBounds(100, 100, 400, 250);
	this.frmAboutUs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.frmAboutUs.getContentPane().setLayout(null);

	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(new ImageIcon(
		AboutUs.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/infoButton/infoicon.png")));
	lblNewLabel.setBounds(21, 11, 46, 41);
	this.frmAboutUs.getContentPane().add(lblNewLabel);

	JLabel lblNewLabel_1 = new JLabel("About Us");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
	lblNewLabel_1.setBounds(68, 18, 297, 26);
	this.frmAboutUs.getContentPane().add(lblNewLabel_1);

	JSeparator separator = new JSeparator();
	separator.setBounds(21, 55, 344, 2);
	this.frmAboutUs.getContentPane().add(separator);

	JLabel lblNewLabel_2 = new JLabel(
		"<html>About Us: We are working to give away shoes with raffle tickets. The programmer of this software is Brandon Li, and the person who requested it is Nate Besthoff. Raffle hosted by Nate Besthoff, you are able to win shoes by buying raffle tickets. If you win, you will recieve an email saying you can pick up your shoe at a specific location. All payments of tickets or raffles are payed in PayPal by secure checking, Thank you for reading. This program was made by Brandon Li. Thank you for reading.<html>");
	lblNewLabel_2.setForeground(Color.WHITE);
	lblNewLabel_2.setBackground(Color.WHITE);
	lblNewLabel_2.setBounds(21, 56, 359, 166);
	this.frmAboutUs.getContentPane().add(lblNewLabel_2);
	
	this.frmAboutUs.setResizable(false);
	
    }

}
