package com.pulsebeat02.main.gui.windows.exception;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;

import com.pulsebeat02.main.util.logging.Logger;

import java.awt.Font;
import java.awt.Color;

public class ExceptionHandling {

    private JFrame frmError;

    public ExceptionHandling() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    ExceptionHandling window = new ExceptionHandling();
		    window.initialize();
		    window.frmError.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }
    
    private void initialize() {

	Logger.LOG.info("Loading Exception Class");

	this.frmError = new JFrame();
	this.frmError.getContentPane().setBackground(Color.LIGHT_GRAY);
	this.frmError.setTitle("Error");
	this.frmError.setIconImage(Toolkit.getDefaultToolkit().getImage(
		ExceptionHandling.class.getResource("/com/pulsebeat02/main/resources/images/passwordWrongIcon.png")));
	this.frmError.setBounds(100, 100, 500, 225);
	this.frmError.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.frmError.getContentPane().setLayout(null);

	JLabel lblThereWasAn = new JLabel("There was an Error in the Program");
	lblThereWasAn.setBounds(79, 20, 326, 27);
	lblThereWasAn.setForeground(Color.WHITE);
	lblThereWasAn.setFont(new Font("Segoe UI", Font.BOLD, 20));
	this.frmError.getContentPane().add(lblThereWasAn);

	JLabel lblWeAreSorry = new JLabel(
		"<html> We are sorry for the inconveinence that was caused. We are trying our best to fix the issue.");
	lblWeAreSorry.setForeground(Color.WHITE);
	lblWeAreSorry.setFont(new Font("Segoe UI", Font.BOLD, 20));
	lblWeAreSorry.setBounds(10, 63, 464, 122);
	this.frmError.getContentPane().add(lblWeAreSorry);
    }

}
