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

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExceptionHandling window = new ExceptionHandling();
					window.frmError.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ExceptionHandling() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Logger.LOG.info("Loading Exception Class");
		
		frmError = new JFrame();
		frmError.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmError.setTitle("Error");
		frmError.setIconImage(Toolkit.getDefaultToolkit().getImage(ExceptionHandling.class.getResource("/com/pulsebeat02/main/resources/images/passwordWrongIcon.png")));
		frmError.setBounds(100, 100, 500, 225);
		frmError.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmError.getContentPane().setLayout(null);
		
		JLabel lblThereWasAn = new JLabel("There was an Error in the Program");
		lblThereWasAn.setBounds(79, 20, 326, 27);
		lblThereWasAn.setForeground(Color.WHITE);
		lblThereWasAn.setFont(new Font("Segoe UI", Font.BOLD, 20));
		frmError.getContentPane().add(lblThereWasAn);
		
		JLabel lblWeAreSorry = new JLabel("<html> We are sorry for the inconveinence that was caused. We are trying our best to fix the issue.");
		lblWeAreSorry.setForeground(Color.WHITE);
		lblWeAreSorry.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblWeAreSorry.setBounds(10, 63, 464, 122);
		frmError.getContentPane().add(lblWeAreSorry);
	}

}
