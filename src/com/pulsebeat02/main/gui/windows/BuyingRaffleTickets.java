package com.pulsebeat02.main.gui.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.pulsebeat02.main.gui.Shoe;
import com.pulsebeat02.main.util.logging.Logger;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSeparator;

public class BuyingRaffleTickets {

	static Shoe s;

	private JFrame frmRaffleTickets;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyingRaffleTickets window = new BuyingRaffleTickets(s);
					window.frmRaffleTickets.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BuyingRaffleTickets(Shoe s) {
		initialize(s.shoeName, s.totalRaffles, s.totalRaffles);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String shoeName, int totalTickets, int boughtTickets) {
		
		Logger.LOG.info("Opening Buying Raffle Tickets");
		
		frmRaffleTickets = new JFrame();
		frmRaffleTickets.getContentPane().setBackground(new Color(211, 211, 211));
		frmRaffleTickets.setIconImage(Toolkit.getDefaultToolkit().getImage(BuyingRaffleTickets.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/tickets.png")));
		frmRaffleTickets.setTitle("Raffle Tickets");
		frmRaffleTickets.setBounds(100, 100, 600, 430);
		frmRaffleTickets.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRaffleTickets.getContentPane().setLayout(null);
		frmRaffleTickets.setResizable(false);

		JLabel lblNewLabel = new JLabel("Buying Raffle Tickets");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 0, 574, 97);
		frmRaffleTickets.getContentPane().add(lblNewLabel);

		JLabel lblCurrentTotal = new JLabel("Current Total Raffle Tickets: " + totalTickets);
		lblCurrentTotal.setForeground(Color.WHITE);
		lblCurrentTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentTotal.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblCurrentTotal.setBounds(20, 137, 564, 39);
		frmRaffleTickets.getContentPane().add(lblCurrentTotal);

		JLabel lblRaffleTicketsYou = new JLabel("Raffle Tickets You Bought: " + boughtTickets);
		lblRaffleTicketsYou.setForeground(Color.WHITE);
		lblRaffleTicketsYou.setHorizontalAlignment(SwingConstants.LEFT);
		lblRaffleTicketsYou.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblRaffleTicketsYou.setBounds(20, 188, 564, 39);
		frmRaffleTickets.getContentPane().add(lblRaffleTicketsYou);

		try {

			JLabel lblPercentageOfWinning = new JLabel("Percentage of Winning: " + totalTickets / boughtTickets + "%");
			lblPercentageOfWinning.setForeground(Color.WHITE);
			lblPercentageOfWinning.setHorizontalAlignment(SwingConstants.LEFT);
			lblPercentageOfWinning.setFont(new Font("Segoe UI", Font.BOLD, 20));
			lblPercentageOfWinning.setBounds(20, 239, 564, 39);
			frmRaffleTickets.getContentPane().add(lblPercentageOfWinning);

		} catch (ArithmeticException e) {

			JLabel lblPercentageOfWinning = new JLabel("Percentage of Winning: " + "0%");
			lblPercentageOfWinning.setForeground(Color.WHITE);
			lblPercentageOfWinning.setHorizontalAlignment(SwingConstants.LEFT);
			lblPercentageOfWinning.setFont(new Font("Segoe UI", Font.BOLD, 20));
			lblPercentageOfWinning.setBounds(20, 239, 564, 39);
			frmRaffleTickets.getContentPane().add(lblPercentageOfWinning);
			
			Logger.LOG.info("Chance of Winning is 0, setting value to 0");

		}

		JButton btnNewButton = new JButton("Buy Tickets");
		btnNewButton.setForeground(Color.GRAY);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnNewButton.setBounds(384, 303, 200, 75);
		btnNewButton.setFocusPainted(false);
		frmRaffleTickets.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Logger.LOG.info("Closing Window");
				frmRaffleTickets.setVisible(false);
				frmRaffleTickets.dispose();
				
				Logger.LOG.info("Opening BuyingTickets");
				BuyingTickets.s = s;
				BuyingTickets.main(null);
			}
		});

		JLabel lblNewLabel_1 = new JLabel(shoeName);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 72, 574, 39);
		frmRaffleTickets.getContentPane().add(lblNewLabel_1);

		JButton btnClose = new JButton("Close");
		btnClose.setForeground(Color.GRAY);
		btnClose.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnClose.setBounds(20, 303, 200, 75);
		btnClose.setFocusPainted(false);
		frmRaffleTickets.getContentPane().add(btnClose);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 290, 574, 2);
		frmRaffleTickets.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 389, 574, 2);
		frmRaffleTickets.getContentPane().add(separator_1);

		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Logger.LOG.info("Closing Window");
				frmRaffleTickets.setVisible(false);
				frmRaffleTickets.dispose();

			}
		});

	}
}
