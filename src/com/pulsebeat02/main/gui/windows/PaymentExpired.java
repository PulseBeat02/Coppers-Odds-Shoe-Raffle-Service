package com.pulsebeat02.main.gui.windows;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;

public class PaymentExpired extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentExpired frame = new PaymentExpired();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaymentExpired() {
		setTitle("Payment Expired");
		setBounds(100, 100, 450, 275);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("We Are Sorry, But Your Payment Expired");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 25, 414, 33);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>This means that you didn't pay for the raffle tickets within the 15 minutes given. Please either close this window and make a new Payment (the old Payment expired), or close this window and don't make a new payment. Thank you.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(7, 75, 414, 72);
		getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(6, 65, 414, 12);
		getContentPane().add(separator);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setBounds(160, 170, 117, 29);
		getContentPane().add(btnNewButton);

	}
}
