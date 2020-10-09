package com.pulsebeat02.shoeraffleservice.application.password;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

public class TooManyPasswordChances {

	private JFrame frmPasswordIsDisabled;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TooManyPasswordChances window = new TooManyPasswordChances();
					window.frmPasswordIsDisabled.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TooManyPasswordChances() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Logger.LOG.info("Opening Too Many Password Chances");
		
		frmPasswordIsDisabled = new JFrame();
		frmPasswordIsDisabled.setIconImage(Toolkit.getDefaultToolkit().getImage(
				TooManyPasswordChances.class.getResource("/com/pulsebeat02/main/resources/images/disabled.png")));
		frmPasswordIsDisabled.setTitle("Password is Disabled");
		frmPasswordIsDisabled.setBounds(100, 100, 450, 240);
		frmPasswordIsDisabled.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPasswordIsDisabled.getContentPane().setLayout(null);
		frmPasswordIsDisabled.setResizable(false);

		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(10, 147, 165, 48);
		frmPasswordIsDisabled.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				Logger.LOG.info("Closing Window");
				frmPasswordIsDisabled.setVisible(false);
				frmPasswordIsDisabled.dispose();

			}

		});

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(269, 147, 165, 48);
		frmPasswordIsDisabled.getContentPane().add(btnMainMenu);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				TooManyPasswordChances.class.getResource("/com/pulsebeat02/main/resources/images/disabled.png")));
		lblNewLabel.setBounds(24, 11, 46, 57);
		frmPasswordIsDisabled.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("The Password has Been Disabled");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(80, 25, 331, 25);
		frmPasswordIsDisabled.getContentPane().add(lblNewLabel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(24, 66, 387, 2);
		frmPasswordIsDisabled.getContentPane().add(separator);

		JLabel lblNewLabel_2 = new JLabel(
				"<html>Due to too many attempts for the password, the password has been disabled for the current user. Please wait 10 more minutes.<html>");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(24, 79, 387, 57);
		frmPasswordIsDisabled.getContentPane().add(lblNewLabel_2);

		btnMainMenu.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				
				Logger.LOG.info("Closing Window");
				frmPasswordIsDisabled.setVisible(false);
				frmPasswordIsDisabled.dispose();

			}

		});

	}

}
