package com.test;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

import com.pulsebeat02.main.util.logging.Logger;

import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;

public class Test_StartingWindow {

	// Starting Window

	static JPanel dashboard;
	static JPanel shoes;
	static JPanel myAccount;
	static JPanel paymentHistory;
	static JPanel decorativeBorder;

	static JLayeredPane tips;
	static JLayeredPane mainPane;

	static JLabel Shoe1ChanceofWinning;
	static JLabel Shoe2ChanceofWinning;
	static JLabel Shoe3ChanceofWinning;

	public static JFrame frmShoeRafflePrize;
	static Border emptyBorder = BorderFactory.createEmptyBorder();

	/*
	 * Launch the application.
	 */

	static Image[] temp1 = new Image[1];

	static private JPasswordField passwordField;
	static private JPasswordField passwordField_1;
	static private JPasswordField passwordField_2;

	static Image[] imagesFinal;

	String cwd = System.getProperty("user.dir");

	public static void main(String[] args) {

		start();

	}

	public static void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Test_StartingWindow();
				Test_StartingWindow.frmShoeRafflePrize.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test_StartingWindow() {

		initialize(imagesFinal);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(Image[] imagesFinal) {

		Logger.LOG.info("Opening Main Window");

		frmShoeRafflePrize = new JFrame();
		frmShoeRafflePrize.getContentPane().setBackground(Color.WHITE);
		frmShoeRafflePrize.setIconImage(Toolkit.getDefaultToolkit().getImage(Test_StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/infoButton/infoicon.png")));
		frmShoeRafflePrize.setTitle("Shoe Raffle Prize");
		frmShoeRafflePrize.setBounds(100, 100, 800, 580);
		frmShoeRafflePrize.setPreferredSize(new Dimension(800, 600));
		frmShoeRafflePrize.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmShoeRafflePrize.setResizable(false);
		frmShoeRafflePrize.getContentPane().setLayout(null);
		// frmShoeRafflePrize.setVisible(true);

		mainPane = new JLayeredPane();
		mainPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPane.setBounds(220, 61, 564, 478);
		frmShoeRafflePrize.getContentPane().add(mainPane);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(325, 186, 200, 57);
		panel_5.setBackground(new Color(144, 238, 144));
		panel_5.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("Good");
		lblNewLabel_14.setBounds(10, 28, 76, 29);
		panel_5.add(lblNewLabel_14);

		dashboard = new JPanel();
		dashboard.setBounds(10, 11, 544, 268);
		mainPane.add(dashboard);
		dashboard.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		dashboard.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(20, 11, 45, 50);
		dashboard.add(lblNewLabel_6);
		lblNewLabel_6.setIcon(new ImageIcon(Test_StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/refresh-button.png")));

		JLabel lblRefresh = new JLabel("Refresh");
		lblRefresh.setBounds(20, 55, 64, 24);
		dashboard.add(lblRefresh);
		lblRefresh.setForeground(Color.GRAY);
		lblRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Logger.LOG.info("Closing Window");

				frmShoeRafflePrize.setVisible(false);
				frmShoeRafflePrize.dispose();

				PrintWriter writer = null;
				try {
					writer = new PrintWriter(cwd + "/lastLogin");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				writer.print("0");
				writer.close();

			}
		});
		lblNewLabel_7.setIcon(new ImageIcon(
				Test_StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/logout.png")));
		lblNewLabel_7.setBounds(20, 90, 45, 50);
		dashboard.add(lblNewLabel_7);

		JLabel lblLogout = new JLabel("Logout");
		lblLogout.setForeground(Color.GRAY);
		lblLogout.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblLogout.setBounds(20, 135, 45, 24);
		dashboard.add(lblLogout);

		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				paymentHistory.setVisible(true);
				tips.setVisible(false);
				myAccount.setVisible(false);
				mainPane.setBounds(220, 61, 564, 478);
				decorativeBorder.setBounds(0, 0, 564, 458);
				shoes.setBounds(10, 11, 544, 268);
				tips.setVisible(false);
				dashboard.setVisible(false);
				shoes.setVisible(false);

				Logger.LOG.info("Opening Payment History");
			}
		});
		label.setIcon(new ImageIcon(Test_StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/dollarbill.png")));
		label.setBounds(20, 175, 45, 50);
		dashboard.add(label);

		JLabel lblViewPurchase = new JLabel("<html>View Purchases");
		lblViewPurchase.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewPurchase.setForeground(Color.GRAY);
		lblViewPurchase.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblViewPurchase.setBounds(20, 213, 64, 37);
		dashboard.add(lblViewPurchase);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 191, 255));
		panel_1.setBounds(96, 22, 200, 57);
		dashboard.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblRaffleTicketPrize = new JLabel("<html> Raffle Ticket Prize          Count");
		lblRaffleTicketPrize.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaffleTicketPrize.setForeground(new Color(255, 255, 255));
		lblRaffleTicketPrize.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblRaffleTicketPrize.setBounds(35, 11, 154, 28);
		panel_1.add(lblRaffleTicketPrize);

		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon(Test_StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/shopping-cart.png")));
		lblNewLabel_11.setBounds(10, 11, 58, 33);
		panel_1.add(lblNewLabel_11);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 182, 193));
		panel_6.setBounds(96, 102, 200, 57);
		dashboard.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblTotalRaffleTickets = new JLabel("Total Raffle Tickets Bought");
		lblTotalRaffleTickets.setForeground(Color.WHITE);
		lblTotalRaffleTickets.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblTotalRaffleTickets.setBounds(39, 11, 161, 14);
		panel_6.add(lblTotalRaffleTickets);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(
				Test_StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/tickets.png")));
		label_2.setBounds(10, 13, 46, 33);
		panel_6.add(label_2);

		JPanel panel_7 = new JPanel();
		panel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shoes.setVisible(true);
				mainPane.setBounds(220, 61, 564, 478);
				decorativeBorder.setBounds(0, 0, 564, 458);
				shoes.setBounds(10, 11, 544, 458);
				tips.setVisible(false);
				dashboard.setVisible(false);
				myAccount.setVisible(false);
				paymentHistory.setVisible(false);

				Logger.LOG.info("Opening Shoes");
			}
		});
		panel_7.setBackground(SystemColor.scrollbar);
		panel_7.setBounds(96, 186, 200, 57);
		dashboard.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblTrendingShoes = new JLabel("Trending Shoes");
		lblTrendingShoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrendingShoes.setForeground(Color.WHITE);
		lblTrendingShoes.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblTrendingShoes.setBounds(43, 11, 147, 14);
		panel_7.add(lblTrendingShoes);

		JLabel lblCheckOutShoes = new JLabel("Check out trending shoes");
		lblCheckOutShoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckOutShoes.setForeground(Color.WHITE);
		lblCheckOutShoes.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblCheckOutShoes.setBounds(43, 35, 152, 14);
		panel_7.add(lblCheckOutShoes);

		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setIcon(new ImageIcon(
				Test_StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/running.png")));
		lblNewLabel_12.setBounds(0, 12, 46, 37);
		panel_7.add(lblNewLabel_12);

		JPanel panel_8 = new JPanel();
		panel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				paymentHistory.setVisible(false);
				tips.setVisible(false);
				mainPane.setBounds(220, 61, 564, 478);
				shoes.setBounds(10, 11, 544, 268);
				dashboard.setVisible(false);
				shoes.setVisible(false);
				myAccount.setVisible(true);

				Logger.LOG.info("Opening My Account");
			}
		});
		panel_8.setBackground(new Color(176, 196, 222));
		panel_8.setBounds(325, 102, 200, 57);
		dashboard.add(panel_8);
		panel_8.setLayout(null);

		JLabel lblMyAccount_1 = new JLabel("My Account");
		lblMyAccount_1.setForeground(Color.WHITE);
		lblMyAccount_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblMyAccount_1.setBounds(10, 11, 135, 14);
		panel_8.add(lblMyAccount_1);

		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setIcon(new ImageIcon(
				Test_StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/account.png")));
		lblNewLabel_15.setBounds(99, 12, 91, 34);
		panel_8.add(lblNewLabel_15);

		JLabel lblChangeSettings = new JLabel("Change Settings");
		lblChangeSettings.setForeground(Color.WHITE);
		lblChangeSettings.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblChangeSettings.setBounds(10, 32, 135, 14);
		panel_8.add(lblChangeSettings);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 230, 140));
		panel_9.setBounds(325, 22, 200, 57);
		dashboard.add(panel_9);
		panel_9.setLayout(null);

		JLabel lblPercentageOfWinning = new JLabel("Percentage of Winning");
		lblPercentageOfWinning.setHorizontalAlignment(SwingConstants.CENTER);
		lblPercentageOfWinning.setForeground(Color.WHITE);
		lblPercentageOfWinning.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblPercentageOfWinning.setBounds(10, 11, 180, 14);
		panel_9.add(lblPercentageOfWinning);

		JLabel lblInternetStatus = new JLabel("Internet Status:");
		lblInternetStatus.setForeground(Color.WHITE);
		lblInternetStatus.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblInternetStatus.setBounds(10, 11, 135, 14);
		panel_5.add(lblInternetStatus);

		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setIcon(new ImageIcon(Test_StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/wifi-connection-good.png")));
		lblNewLabel_13.setBounds(98, 11, 92, 35);
		panel_5.add(lblNewLabel_13);

		dashboard.add(panel_5);

		shoes = new JPanel();
		shoes.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		shoes.setBounds(10, 11, 544, 268);
		mainPane.add(shoes);
		mainPane.setLayer(shoes, 0);
		shoes.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(203, 11, 135, 135);
		panel_3.setBackground(Color.LIGHT_GRAY);
		shoes.add(panel_3);
		panel_3.setLayout(null);

		try {

			JLabel lblImage = new JLabel("");
			Image img1Pre = imagesFinal[1].getScaledInstance(200, 200, Image.SCALE_DEFAULT);
			ImageIcon img1 = new ImageIcon(img1Pre);
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(0, 0, 135, 135);
			lblImage.setIcon(img1);
			lblImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					lblImage.setIcon(BlackAndWhite(img1));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					lblImage.setIcon(img1);
				}
			});

			panel_3.add(lblImage);

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		JPanel panel_10 = new JPanel();
		panel_10.setBounds(25, 11, 135, 135);
		shoes.add(panel_10);
		panel_10.setBackground(Color.LIGHT_GRAY);
		panel_10.setLayout(null);

		JLabel lblImage_1 = new JLabel("");
		Image img0Pre = imagesFinal[0].getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		ImageIcon img0 = new ImageIcon(img0Pre);
		lblImage_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage_1.setBounds(0, 0, 135, 135);
		lblImage_1.setIcon(img0);
		lblImage_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblImage_1.setIcon(BlackAndWhite(img0));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblImage_1.setIcon(img0);
			}
		});
		panel_10.add(lblImage_1);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(374, 11, 135, 135);
		shoes.add(panel_1_1);
		panel_1_1.setBackground(Color.LIGHT_GRAY);
		panel_1_1.setLayout(null);

		try {

			JLabel lblNewLabel_17 = new JLabel("");
			Image img3Pre = imagesFinal[2].getScaledInstance(200, 200, Image.SCALE_DEFAULT);
			ImageIcon img3 = new ImageIcon(img3Pre);
			lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_17.setBounds(0, 0, 135, 135);
			lblNewLabel_17.setIcon(img3);
			lblNewLabel_17.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					lblNewLabel_17.setIcon(BlackAndWhite(img3));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					lblNewLabel_17.setIcon(img3);
				}
			});
			panel_1_1.add(lblNewLabel_17);

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		paymentHistory = new JPanel();
		paymentHistory.setBounds(10, 11, 544, 455);
		mainPane.add(paymentHistory);
		paymentHistory.setLayout(null);
		paymentHistory.setVisible(false);

		JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_4.setBounds(20, 140, 505, 300);
		paymentHistory.add(panel_4);
		panel_4.setBackground(new Color(180, 180, 180));
		panel_4.setLayout(null);
		JScrollPane pane = new JScrollPane();
		panel_4.add(pane);

		JButton btnNewButton_1 = new JButton("Open Purchases Securely in a New Window");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton_1.setBounds(120, 11, 268, 23);
		panel_4.add(btnNewButton_1);

		JButton btnHelp = new JButton("Help");
		btnHelp.setBackground(Color.LIGHT_GRAY);
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File htmlFile = new File(cwd + "/src/com/pulsebeat02/main/resources/help.html");
				try {
					Desktop.getDesktop().browse(htmlFile.toURI());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnHelp.setBounds(406, 11, 89, 23);
		panel_4.add(btnHelp);

		JLabel lblNewLabel_18 = new JLabel(
				"<html> In Coppers Odds, it is our goal to make sure you get your award which is a shoe fairly. If you have won the shoe raffle and forgot to come to get your shoe at a specific location, we will gladly hold onto your shoe for at most a week. However, if the shoe has not yet recieved by the time the week ends, the shoe will be used for other raffles. Additionally, we also care about the purchases you make and making sure that they are correct. If one of your purchases are incorrect, we will gladly accept any emails or feedback on our website. Thank you for reading, and I hope you follow our Terms of Service.");
		lblNewLabel_18.setForeground(Color.WHITE);
		lblNewLabel_18.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_18.setBounds(10, 98, 485, 202);
		panel_4.add(lblNewLabel_18);

		JLabel lblNewLabel_19 = new JLabel(
				"<html> Click on \"Feedback\" to email us. Click on \"Open Purchases Securely in a New Window\" to open up all transactions. Click \"Help\" if you would like to recieve some help.");
		lblNewLabel_19.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_19.setForeground(Color.WHITE);
		lblNewLabel_19.setBounds(10, 48, 485, 64);
		panel_4.add(lblNewLabel_19);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 117, 505, 14);
		panel_4.add(separator_3);

		JLabel lblNewLabel_4 = new JLabel("Payment History");
		lblNewLabel_4.setBounds(10, 11, 524, 27);
		paymentHistory.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 20));

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(18, 49, 508, 2);
		paymentHistory.add(separator_1);

		JLabel lblNewLabel_16 = new JLabel(
				"<html> Listed are recent payments you have made with raffle tickets and other purchases. Please email us if there are any problems right now with payments or other things.");
		lblNewLabel_16.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_16.setBounds(18, 55, 514, 70);
		paymentHistory.add(lblNewLabel_16);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.DARK_GRAY);
		separator_2.setBackground(Color.DARK_GRAY);
		separator_2.setBounds(18, 123, 508, 2);
		paymentHistory.add(separator_2);

		myAccount = new JPanel();
		myAccount.setBounds(10, 11, 544, 455);
		mainPane.add(myAccount);
		myAccount.setLayout(null);
		myAccount.setVisible(false);

		JLabel lblEditAccount = new JLabel("Edit Account");
		lblEditAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditAccount.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblEditAccount.setBounds(10, 11, 524, 39);
		myAccount.add(lblEditAccount);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 50, 524, 2);
		myAccount.add(separator_4);

		JLabel lblNewUsername = new JLabel("New Username:");
		lblNewUsername.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewUsername.setBounds(110, 61, 110, 27);
		myAccount.add(lblNewUsername);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblFirstName.setBounds(110, 99, 110, 27);
		myAccount.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblLastName.setBounds(110, 137, 110, 27);
		myAccount.add(lblLastName);

		JLabel lblChangePassword = new JLabel("Change Password:");
		lblChangePassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePassword.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblChangePassword.setBounds(10, 175, 524, 27);
		myAccount.add(lblChangePassword);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 213, 524, 2);
		myAccount.add(separator_5);

		JLabel lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblOldPassword.setBounds(10, 226, 110, 27);
		myAccount.add(lblOldPassword);

		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewPassword.setBounds(10, 265, 110, 27);
		myAccount.add(lblNewPassword);

		JLabel lblRetypeNewPassword = new JLabel("Retype New Password to Confirm:");
		lblRetypeNewPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblRetypeNewPassword.setBounds(10, 303, 237, 27);
		myAccount.add(lblRetypeNewPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(126, 232, 300, 20);
		myAccount.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(126, 271, 300, 20);
		myAccount.add(passwordField_1);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(244, 309, 290, 20);
		myAccount.add(passwordField_2);

		JLabel lblBiography = new JLabel("Biography (One Sentence):");
		lblBiography.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblBiography.setBounds(10, 340, 524, 27);
		myAccount.add(lblBiography);

		JButton btnNewButton_3 = new JButton("Save");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Logger.LOG.info("New Account Credentials Saved");

			}
		});
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnNewButton_3.setBounds(10, 421, 89, 23);
		myAccount.add(btnNewButton_3);

		JButton btnClose = new JButton("Close");
		btnClose.setFocusable(false);
		btnClose.setBackground(Color.LIGHT_GRAY);
		btnClose.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnClose.setBounds(445, 421, 89, 23);
		myAccount.add(btnClose);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setFocusable(false);
		btnMainMenu.setBackground(Color.LIGHT_GRAY);
		btnMainMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnMainMenu.setBounds(217, 421, 120, 23);
		myAccount.add(btnMainMenu);

//		JButton btnStart = new JButton("");
//		btnStart.setIcon(new ImageIcon(StartingWindow.class
//				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/startButton/button_Default.png")));
//		btnStart.setBounds(352, 448, 280, 57);
//		frmShoeRafflePrize.getContentPane().add(btnStart);
//		btnStart.setFocusPainted(false);
//		btnStart.setBorder(emptyBorder);
//
//		btnStart.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent e) {
//
//				Image[] images = new Image[5];
//
//				try {
//
//					images[0] = ImageIO // /com/pulsebeat02/main/resources/images/shoesRaffle/shoe1.png
//							.read(StartingWindow.class
//									.getResource("/com/pulsebeat02/main/resources/images/shoesRaffle/shoe1.png"));
//
//				} catch (IOException e1) {
//
//					try {
//						images[0] = ImageIO // /com/pulsebeat02/main/resources/images/imageNotFound.png
//								.read(StartingWindow.class
//										.getResource("/com/pulsebeat02/main/resources/images/imageNotFound.png"));
//					} catch (IOException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//
//				}
//
//				try {
//					images[1] = ImageIO.read(StartingWindow.class
//							.getResource("/com/pulsebeat02/main/resources/images/shoesRaffle/shoe2.png"));
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					try {
//						images[1] = ImageIO.read(StartingWindow.class
//								.getResource("/com/pulsebeat02/main/resources/images/imageNotFound.png"));
//					} catch (IOException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//					e1.printStackTrace();
//				}
//				try {
//					images[2] = ImageIO.read(StartingWindow.class
//							.getResource("/com/pulsebeat02/main/resources/images/shoesRaffle/shoe3.png"));
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					try {
//						images[2] = ImageIO.read(StartingWindow.class
//								.getResource("/com/pulsebeat02/main/resources/images/imageNotFound.png"));
//					} catch (IOException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//					e1.printStackTrace();
//				}
//
//				String[] args = null;
//				ChooseShoe.main(args);
//
//				frmShoeRafflePrize.setVisible(false);
//				frmShoeRafflePrize.dispose();
//
//			}
//
//		});
//
//		btnStart.addMouseListener(new MouseListener() {
//			@Override
//			public void mouseReleased(MouseEvent e) {
//
//				btnStart.setIcon(new ImageIcon(StartingWindow.class.getResource(
//						"/com/pulsebeat02/main/resources/images/mainmenu/startButton/button_Default.png")));
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				btnStart.setIcon(new ImageIcon(StartingWindow.class.getResource(
//						"/com/pulsebeat02/main/resources/images/mainmenu/startButton/button_Pressed.png")));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				btnStart.setIcon(new ImageIcon(StartingWindow.class.getResource(
//						"/com/pulsebeat02/main/resources/images/mainmenu/startButton/button_Default.png")));
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				btnStart.setIcon(new ImageIcon(StartingWindow.class.getResource(
//						"/com/pulsebeat02/main/resources/images/mainmenu/startButton/button_Hovered.png")));
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				btnStart.setIcon(new ImageIcon(StartingWindow.class.getResource(
//						"/com/pulsebeat02/main/resources/images/mainmenu/startButton/button_Pressed.png")));
//			}
//		});

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 794, 50);
		frmShoeRafflePrize.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Coppers Odds Shoe Selling Service. Buy Raffles, get Shoes.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		lblNewLabel_2.setBounds(66, 11, 718, 28);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Test_StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/copporsOdds.png")));
		lblNewLabel_1.setBounds(10, 5, 46, 39);
		panel.add(lblNewLabel_1);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
		ImageIcon image = new ImageIcon(
				Test_StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/music.jpg"));

		Image image1 = image.getImage(); // transform it
		Image newimg = image1.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		image = new ImageIcon(newimg); // transform it back

		btnNewButton_4.setIcon(image);
		btnNewButton_4.setToolTipText("Change Music");
		btnNewButton_4.setBounds(750, 8, 35, 35);
		panel.add(btnNewButton_4);

		JLayeredPane welcome = new JLayeredPane();
		welcome.setBounds(10, 61, 200, 478);
		welcome.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		welcome.setForeground(Color.LIGHT_GRAY);
		welcome.setBackground(Color.BLACK);
		frmShoeRafflePrize.getContentPane().add(welcome);
		welcome.setLayout(null);

		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(new Color(169, 169, 169));
		welcomePanel.setBounds(10, 11, 180, 456);
		welcome.add(welcomePanel);
		welcomePanel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Welcome");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(10, 11, 160, 21);
		welcomePanel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 20));

		JLabel lblDashBoard = new JLabel(" Dashboard");
		lblDashBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				dashboard.setVisible(true);
				tips.setVisible(true);
				shoes.setVisible(false);
				myAccount.setVisible(false);
				paymentHistory.setVisible(false);
				dashboard.setBounds(10, 11, 543, 268);
				mainPane.setBounds(220, 61, 564, 290);
				shoes.setBounds(10, 11, 544, 268);

				Logger.LOG.info("Opening Dashboard");

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblDashBoard.setText("<html><span bgcolor=\"black\">" + " " + "Dashboard</span></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblDashBoard.setText(" Dashboard");
			}
		});

		lblDashBoard.setHorizontalAlignment(SwingConstants.CENTER);
		lblDashBoard.setIcon(new ImageIcon(
				Test_StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/home.png")));
		lblDashBoard.setForeground(Color.WHITE);
		lblDashBoard.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblDashBoard.setBounds(10, 67, 160, 38);
		welcomePanel.add(lblDashBoard);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 43, 160, 2);
		welcomePanel.add(separator);

		JLabel lblShoes = new JLabel(" Shoes");
		lblShoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				shoes.setVisible(true);
				mainPane.setBounds(220, 61, 564, 478);
				decorativeBorder.setBounds(0, 0, 564, 458);
				shoes.setBounds(10, 11, 544, 458);
				tips.setVisible(false);
				dashboard.setVisible(false);
				myAccount.setVisible(false);
				paymentHistory.setVisible(false);

				Logger.LOG.info("Opening Shoes");

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblShoes.setText("<html><span bgcolor=\"black\"> Shoes</span></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblShoes.setText(" Shoes");
			}
		});
		lblShoes.setIcon(new ImageIcon(
				Test_StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/shoe.png")));
		lblShoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblShoes.setForeground(Color.WHITE);
		lblShoes.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblShoes.setBounds(10, 115, 160, 38);
		welcomePanel.add(lblShoes);

		JLabel lblPaymentHistory = new JLabel(" Payments");
		lblPaymentHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				paymentHistory.setVisible(true);
				tips.setVisible(false);
				myAccount.setVisible(false);
				mainPane.setBounds(220, 61, 564, 478);
				decorativeBorder.setBounds(0, 0, 564, 458);
				shoes.setBounds(10, 11, 544, 268);
				tips.setVisible(false);
				dashboard.setVisible(false);
				shoes.setVisible(false);

				Logger.LOG.info("Opening Payment History");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblPaymentHistory.setText("<html><span bgcolor=\"black\"> Payments</span></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblPaymentHistory.setText(" Payments");
			}
		});
		lblPaymentHistory.setIcon(new ImageIcon(
				Test_StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/money.png")));
		lblPaymentHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymentHistory.setForeground(Color.WHITE);
		lblPaymentHistory.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPaymentHistory.setBounds(10, 164, 160, 38);
		welcomePanel.add(lblPaymentHistory);

		JLabel lblMyAccount = new JLabel(" My Account");
		lblMyAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				paymentHistory.setVisible(false);
				tips.setVisible(false);
				mainPane.setBounds(220, 61, 564, 478);
				shoes.setBounds(10, 11, 544, 268);
				dashboard.setVisible(false);
				shoes.setVisible(false);
				myAccount.setVisible(true);

				Logger.LOG.info("Opening My Account");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblMyAccount.setText("<html><span bgcolor=\"black\"> My Account</span></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblMyAccount.setText(" My Account");
			}

		});
		lblMyAccount.setIcon(new ImageIcon(
				Test_StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/account.png")));
		lblMyAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyAccount.setForeground(Color.WHITE);
		lblMyAccount.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblMyAccount.setBounds(10, 213, 160, 38);
		welcomePanel.add(lblMyAccount);

		JLabel lblNewLabel_5 = new JLabel(
				"<html> Credits go to Brandon Li, the producer of this program and Nate, the hoster of the entire shoe fund. Version 1.0.0");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(8, 397, 165, 51);
		welcomePanel.add(lblNewLabel_5);

		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Logger.LOG.info("Closing Window");

				frmShoeRafflePrize.setVisible(false);
				frmShoeRafflePrize.dispose();

				PrintWriter writer = null;
				try {
					writer = new PrintWriter(cwd + "/lastLogin");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				writer.print("0");
				writer.close();

			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(47, 360, 89, 23);
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		welcomePanel.add(btnNewButton);
		btnNewButton.setFocusPainted(false);

		JLabel lblAboutUs = new JLabel(" About Us");
		lblAboutUs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Logger.LOG.info("Opening About Us");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblAboutUs.setText("<html><span bgcolor=\"black\"> About Us</span></html>");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblAboutUs.setText(" About Us");
			}
		});
		lblAboutUs.setIcon(new ImageIcon(Test_StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/infoButton/infoicon.png")));
		label.setSize(label.getPreferredSize());
		lblAboutUs.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutUs.setForeground(Color.WHITE);
		lblAboutUs.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblAboutUs.setBounds(0, 262, 180, 38);
		welcomePanel.add(lblAboutUs);

		JLayeredPane button_discription_panel = new JLayeredPane();
		button_discription_panel.setBounds(0, 0, 1, 1);
		frmShoeRafflePrize.getContentPane().add(button_discription_panel);

		decorativeBorder = new JPanel();
		decorativeBorder.setBounds(0, 0, 564, 478);
		button_discription_panel.add(decorativeBorder);
		decorativeBorder.setBorder(new LineBorder(new Color(0, 0, 0)));
		decorativeBorder.setBackground(Color.WHITE);
		decorativeBorder.setLayout(null);

		tips = new JLayeredPane();
		tips.setBounds(220, 362, 564, 177);
		frmShoeRafflePrize.getContentPane().add(tips);
		tips.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(0, 0, 564, 25);
		tips.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tips");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(6, 5, 538, 15);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(Test_StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/refresh-button.png")));
		lblNewLabel_8.setBounds(24, 53, 46, 37);
		tips.add(lblNewLabel_8);

		JLabel lblclickOnThe = new JLabel("<html>Click on the \"Refresh\" to refresh everything.");
		lblclickOnThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblclickOnThe.setForeground(Color.GRAY);
		lblclickOnThe.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblclickOnThe.setBounds(80, 36, 158, 62);
		tips.add(lblclickOnThe);

		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(
				Test_StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/logout.png")));
		lblNewLabel_9.setBounds(24, 107, 46, 47);
		tips.add(lblNewLabel_9);

		JLabel lblclickOnThe_1 = new JLabel("<html>Click on the \"Log Out\" to log out of your account.");
		lblclickOnThe_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblclickOnThe_1.setForeground(Color.GRAY);
		lblclickOnThe_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblclickOnThe_1.setBounds(80, 92, 158, 62);
		tips.add(lblclickOnThe_1);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(Test_StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/dollarbill.png")));
		lblNewLabel_10.setBounds(317, 53, 46, 37);
		tips.add(lblNewLabel_10);

		JLabel lblclickOnThe_2 = new JLabel("<html>Click on the \"View Purchases\" to view all orders.");
		lblclickOnThe_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblclickOnThe_2.setForeground(Color.GRAY);
		lblclickOnThe_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblclickOnThe_2.setBounds(363, 38, 158, 62);
		tips.add(lblclickOnThe_2);

	}

	public static void fontSelector(JLabel label) {

		Font labelFont = label.getFont();
		String labelText = label.getText();

		int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = label.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double) componentWidth / (double) stringWidth;

		int newFontSize = (int) (labelFont.getSize() * widthRatio);
		int componentHeight = label.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.

		label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));

	}

	public static ImageIcon BlackAndWhite(ImageIcon icon) {

		BufferedImage image = convertToBufferedImage(icon);

		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

		Graphics2D graphic = result.createGraphics();
		graphic.drawImage(image, 0, 0, Color.WHITE, null);
		graphic.dispose();

		return new ImageIcon(result);

	}

	public static BufferedImage convertToBufferedImage(ImageIcon image) {
		Image image1 = image.getImage();
		BufferedImage newImage = new BufferedImage(image1.getWidth(null), image1.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(image1, 0, 0, null);
		g.dispose();
		return newImage;
	}
}
