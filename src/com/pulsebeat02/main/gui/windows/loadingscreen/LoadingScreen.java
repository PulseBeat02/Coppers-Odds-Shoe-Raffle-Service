package com.pulsebeat02.main.gui.windows.loadingscreen;

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

	public static JFrame frmCoppersOddsIs;
	
	static boolean isDone = false;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					LoadingScreen window = new LoadingScreen();
					window.frmCoppersOddsIs.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoadingScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void initialize() {
		
		frmCoppersOddsIs = new JFrame();
		frmCoppersOddsIs.setTitle("Coppers Odds is Loading");
		frmCoppersOddsIs.setIconImage(Toolkit.getDefaultToolkit().getImage(LoadingScreen.class.getResource("/com/pulsebeat02/main/resources/loadingscreen/co1.PNG")));
		frmCoppersOddsIs.setBounds(100, 100, 450, 255);
		frmCoppersOddsIs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCoppersOddsIs.getContentPane().setLayout(null);
		frmCoppersOddsIs.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		if (isDone) {
			
			frmCoppersOddsIs.setVisible(false);
			frmCoppersOddsIs.dispose();
			
		}
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setForeground(Color.GRAY);
		progressBar.setBounds(10, 175, 414, 30);
		frmCoppersOddsIs.getContentPane().add(progressBar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(22, 11, 64, 64);
		lblNewLabel.setIcon(new ImageIcon(LoadingScreen.class.getResource("/com/pulsebeat02/main/resources/images/loadingscreen.gif")));
		frmCoppersOddsIs.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Coppers Odds is Loading");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lblNewLabel_1.setBounds(84, 11, 325, 64);
		frmCoppersOddsIs.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<htmL> Please wait, as Coppers Odds is loading the program for you.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblNewLabel_2.setBounds(21, 95, 388, 53);
		frmCoppersOddsIs.getContentPane().add(lblNewLabel_2);
		
		while (true) {
			
			if (!SplashScreenLoading.isLoaded) {
				
				frmCoppersOddsIs.setVisible(true);
				
			}
			
			else {
				
				frmCoppersOddsIs.setVisible(false);
				
			}
			
		}
	}
}
