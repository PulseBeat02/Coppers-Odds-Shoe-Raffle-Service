package com.pulsebeat02.shoeraffleservice.application.login;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Loading extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Loading() {
	
	this.setUndecorated(true);
	this.setTitle("Loading");
	this.setBounds(100, 100, 256, 256);
	this.getContentPane().setLayout(new BorderLayout());

	JLabel label = new JLabel("");
	label.setIcon(new ImageIcon(Loading.class.getResource("/com/pulsebeat02/main/resources/images/login/loading.gif")));
	this.getContentPane().add(label, BorderLayout.NORTH);
	
    }

}
