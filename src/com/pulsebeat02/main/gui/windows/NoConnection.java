package com.pulsebeat02.main.gui.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;

import com.pulsebeat02.main.Coppers_Odds;
import com.pulsebeat02.main.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;

public class NoConnection extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmNoConnection;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoConnection window = new NoConnection();
					window.frmNoConnection.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NoConnection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Logger.LOG.info("Opening No Connection");
		
		frmNoConnection = new JFrame();
		frmNoConnection.setTitle("No Connection");
		frmNoConnection.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(NoConnection.class.getResource("/com/pulsebeat02/main/resources/images/nowifi.png")));
		frmNoConnection.setBounds(100, 100, 500, 250);
		frmNoConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNoConnection.getContentPane().setLayout(null);
		frmNoConnection.setResizable(false);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(NoConnection.class.getResource("/com/pulsebeat02/main/resources/images/nowifi.png")));
		lblNewLabel.setBounds(25, 24, 32, 42);
		frmNoConnection.getContentPane().add(lblNewLabel);

		JLabel lblYouAreNot = new JLabel("You Are Not Connected to the Internet");
		lblYouAreNot.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblYouAreNot.setBounds(67, 30, 430, 30);
		frmNoConnection.getContentPane().add(lblYouAreNot);

		JLabel lblNewLabel_1 = new JLabel(
				"<html> You won't be able to participate in the shoe raffle due to no <br> internet connection.<html>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(35, 77, 430, 52);
		frmNoConnection.getContentPane().add(lblNewLabel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(35, 71, 419, 2);
		frmNoConnection.getContentPane().add(separator);

		JButton btnClose = new JButton("Close");
		btnClose.setFocusable(false);
		btnClose.setBounds(35, 160, 89, 23);
		frmNoConnection.getContentPane().add(btnClose);
		btnClose.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				
				Logger.LOG.info("Closing Window");
				
				frmNoConnection.setVisible(false);
				frmNoConnection.dispose();

			}

		});

		JButton btnRetry = new JButton("Retry");
		btnRetry.setFocusable(false);
		btnRetry.setBounds(355, 160, 89, 23);
		frmNoConnection.getContentPane().add(btnRetry);
		btnRetry.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {
				
				Logger.LOG.info("Restarting Program because of No Connction");
				
				Logger.LOG.info("Closing Window");
				
				frmNoConnection.setVisible(false);
				frmNoConnection.dispose();
				
				restartApplication();

			}

		});

	}
	
	public static void restartApplication() {
		final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
		File currentJar = null;
		try {
			currentJar = new File(Coppers_Odds.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* is it a jar file? */
		if (!currentJar.getName().endsWith(".jar"))
			return;

		/* Build command: java -jar application.jar */
		final ArrayList<String> command = new ArrayList<String>();
		command.add(javaBin);
		command.add("-jar");
		command.add(currentJar.getPath());
		command.add("null true");

		final ProcessBuilder builder = new ProcessBuilder(command);
		try {
			builder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
}
