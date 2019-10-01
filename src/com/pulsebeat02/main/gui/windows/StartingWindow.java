package com.pulsebeat02.main.gui.windows;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import com.pulsebeat02.main.gui.Shoe;
import com.pulsebeat02.main.gui.windows.account.Account;
import com.pulsebeat02.main.gui.windows.ads.SetupVideo;
import com.pulsebeat02.main.gui.windows.builder.CustomJLabel;
import com.pulsebeat02.main.gui.windows.login.LoginPanel;
import com.pulsebeat02.main.gui.windows.payment.PaymentTable;
import com.pulsebeat02.main.util.logging.Logger;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;
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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import javax.swing.border.EtchedBorder;

public class StartingWindow extends SwingWorker<Integer, Void> {

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

	public static Shoe[] shoesInGui = new Shoe[2];
	public static Account account;

	public static JFrame frmShoeRafflePrize;
	static Border emptyBorder = BorderFactory.createEmptyBorder();

	/*
	 * Launch the application.
	 */

	static Image[] temp1 = new Image[1];

	static private JTextField textField;
	static private JTextField textField_1;
	static private JTextField textField_2;
	static private JPasswordField passwordField;
	static private JPasswordField passwordField_1;
	static private JPasswordField passwordField_2;

	public static int maxThreads;
	public static int userThreadSize;

	static Image[] imagesFinal;

	String cwd = System.getProperty("user.dir");

	static int horizCenter = SwingConstants.CENTER;
	static int horizLeft = SwingConstants.LEFT;

	static Color white = Color.WHITE;
	static Color gray = Color.GRAY;

	/**
	 * Create the application.
	 */
	public StartingWindow(Account account) {

		String cwd = System.getProperty("user.dir");

		imagesFinal = new Image[shoesInGui.length];

		for (int i = 0; i < imagesFinal.length; i++) {

			try {
				imagesFinal[i] = ImageIO.read(new URL(shoesInGui[i].imageURLs[0]));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					imagesFinal[i] = ImageIO.read(new File(cwd + "/src/resources.images/imageNotFound"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

		initialize(account, imagesFinal);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(Account account, Image[] imagesFinal) {

		Logger.LOG.info("Opening Main Window");

		frmShoeRafflePrize = new JFrame();
		frmShoeRafflePrize.getContentPane().setBackground(white);
		frmShoeRafflePrize.setIconImage(Toolkit.getDefaultToolkit().getImage(StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/infoButton/infoicon.png")));
		frmShoeRafflePrize.setTitle("Shoe Raffle Service");
		frmShoeRafflePrize.setBounds(100, 100, 800, 580);
		frmShoeRafflePrize.setPreferredSize(new Dimension(800, 600));
		frmShoeRafflePrize.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmShoeRafflePrize.setResizable(false);
		frmShoeRafflePrize.getContentPane().setLayout(null);

		mainPane = new JLayeredPane();
		mainPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPane.setBounds(220, 61, 564, 478);
		frmShoeRafflePrize.getContentPane().add(mainPane);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(325, 186, 200, 57);
		panel_5.setBackground(new Color(144, 238, 144));
		panel_5.setLayout(null);

		CustomJLabel lblNewLabel_14 = new CustomJLabel(10, 28, 76, 29, "Good", panel_5, null, null, null, null);
		lblNewLabel_14.setVisible(true);

		dashboard = new JPanel();
		dashboard.setBounds(10, 11, 544, 268);
		mainPane.add(dashboard);
		dashboard.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		dashboard.setLayout(null);

		CustomJLabel lblNewLabel_6 = new CustomJLabel(20, 11, 45, 50, "", dashboard, null, null, null,
				new ImageIcon(StartingWindow.class
						.getResource("/com/pulsebeat02/main/resources/images/mainmenu/refresh-button.png")));
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				restartApplication();
			}
		});

		CustomJLabel lblRefresh = new CustomJLabel(20, 55, 64, 24, "Refresh", dashboard, null, gray,
				new Font("Segoe UI", Font.PLAIN, 12), null);
		lblRefresh.setVisible(true);

		CustomJLabel lblNewLabel_7 = new CustomJLabel(20, 90, 45, 50, "", dashboard, null, null, null, new ImageIcon(
				StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/logout.png")));
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

				LoginPanel.start(true);
				Logger.LOG.info("Logging Out");
			}
		});

		CustomJLabel lblLogout = new CustomJLabel(20, 135, 45, 24, "Logout", dashboard, null, gray,
				new Font("Segoe UI", Font.PLAIN, 12), null);
		lblLogout.setVisible(true);

		CustomJLabel label = new CustomJLabel(20, 175, 45, 50, "", dashboard, null, null, null, new ImageIcon(
				StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/dollarbill.png")));
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

		CustomJLabel lblViewPurchase = new CustomJLabel(20, 213, 64, 37, "<html>View Purchases", dashboard, horizCenter,
				gray, new Font("Segoe UI", Font.PLAIN, 12), null);
		lblViewPurchase.setVisible(true);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 191, 255));
		panel_1.setBounds(96, 22, 200, 57);
		dashboard.add(panel_1);
		panel_1.setLayout(null);

		CustomJLabel lblRaffleTicketPrize = new CustomJLabel(35, 11, 154, 28, "<html> Raffle Ticket Prize Count",
				panel_1, horizCenter, new Color(255, 255, 255), new Font("Segoe UI", Font.BOLD, 11), null);
		lblRaffleTicketPrize.setVisible(true);

		CustomJLabel lblNewLabel_11 = new CustomJLabel(10, 11, 58, 33, "", panel_1, null, null, null, new ImageIcon(
				StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/shopping-cart.png")));
		lblNewLabel_11.setVisible(true);

		CustomJLabel lblraffletickets = new CustomJLabel(40, 37, 135, 14, String.valueOf(account.shoesWon), panel_1,
				horizCenter, white, new Font("Segoe UI", Font.BOLD, 11), null);
		lblraffletickets.setVisible(true);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 182, 193));
		panel_6.setBounds(96, 102, 200, 57);
		dashboard.add(panel_6);
		panel_6.setLayout(null);

		CustomJLabel lblTotalRaffleTickets = new CustomJLabel(39, 11, 161, 14, "Total Raffle Tickets Bought", panel_6,
				null, white, new Font("Segoe UI", Font.BOLD, 11), null);
		lblTotalRaffleTickets.setVisible(true);

		CustomJLabel label_1 = new CustomJLabel(40, 32, 135, 14, String.valueOf(account.raffleTicketsBought), panel_6,
				horizCenter, white, new Font("Segoe UI", Font.BOLD, 11), null);
		label_1.setVisible(true);

		CustomJLabel label_2 = new CustomJLabel(10, 13, 46, 33, "", panel_6, null, null, null, new ImageIcon(
				StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/tickets.png")));
		label_2.setVisible(true);

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

		CustomJLabel lblTrendingShoes = new CustomJLabel(43, 11, 147, 14, "Trending Shoes", panel_7, horizCenter, white,
				new Font("Segoe UI", Font.BOLD, 11), null);
		lblTrendingShoes.setVisible(true);

		/* Start Here */

		CustomJLabel lblCheckOutShoes = new CustomJLabel(43, 35, 152, 14, "Check out trending shoes", panel_7,
				horizCenter, white, new Font("Segoe UI", Font.BOLD, 11), null);
		lblCheckOutShoes.setVisible(true);

		CustomJLabel lblNewLabel_12 = new CustomJLabel(0, 12, 46, 37, "", panel_7, horizCenter, null, null,
				new ImageIcon(StartingWindow.class
						.getResource("/com/pulsebeat02/main/resources/images/mainmenu/running.png")));
		lblNewLabel_12.setVisible(true);

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

		CustomJLabel lblMyAccount_1 = new CustomJLabel(10, 11, 135, 14, "My Account", panel_8, null, white,
				new Font("Segoe UI", Font.BOLD, 11), null);
		lblMyAccount_1.setVisible(true);

		CustomJLabel lblNewLabel_15 = new CustomJLabel(99, 12, 91, 34, "", panel_8, horizCenter, null, null,
				new ImageIcon(StartingWindow.class
						.getResource("/com/pulsebeat02/main/resources/images/mainmenu/account.png")));
		lblNewLabel_15.setVisible(true);

		CustomJLabel lblChangeSettings = new CustomJLabel(10, 32, 135, 14, "Change Settings", panel_8, null, white,
				new Font("Segoe UI", Font.BOLD, 11), null);
		lblChangeSettings.setVisible(true);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(240, 230, 140));
		panel_9.setBounds(325, 22, 200, 57);
		dashboard.add(panel_9);
		panel_9.setLayout(null);

		CustomJLabel lblPercentageOfWinning = new CustomJLabel(10, 11, 180, 14, "Percentage of Winning", panel_9,
				horizCenter, white, new Font("Segoe UI", Font.BOLD, 11), null);
		lblPercentageOfWinning.setVisible(true);

		int sum = 0;

		try {

			for (int i = 0; i < account.rafflesBought.length; i++) {

				sum += account.rafflesBought[i];

			}

			CustomJLabel label_3 = new CustomJLabel(10, 32, 180, 14,
					String.valueOf(((int) sum / account.rafflesBought.length * 100)) + "%", panel_9, horizCenter, white,
					new Font("Segoe UI", Font.BOLD, 11), null);
			label_3.setVisible(true);

		} catch (ArithmeticException e) {

			CustomJLabel label_3 = new CustomJLabel(10, 32, 180, 14, "0%", panel_9, horizCenter, white,
					new Font("Segoe UI", Font.BOLD, 11), null);
			label_3.setVisible(true);

		}

		CustomJLabel lblInternetStatus = new CustomJLabel(10, 11, 135, 14, "Internet Status:", panel_5, null, white,
				new Font("Segoe UI", Font.BOLD, 11), null);
		lblInternetStatus.setVisible(true);

		CustomJLabel lblNewLabel_13 = new CustomJLabel(98, 11, 92, 35, "", panel_5, horizCenter, null, null,
				new ImageIcon(StartingWindow.class
						.getResource("/com/pulsebeat02/main/resources/images/mainmenu/wifi-connection-good.png")));
		lblNewLabel_13.setVisible(true);

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

			Image img1Pre = imagesFinal[1].getScaledInstance(200, 200, Image.SCALE_DEFAULT);
			ImageIcon img1 = new ImageIcon(img1Pre);

			CustomJLabel lblImage = new CustomJLabel(0, 0, 135, 135, "", panel_3, horizCenter, null, null, img1);
			lblImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ShoeGUI.s = shoesInGui[1];
					ShoeGUI.currentShoe = 1;
					ShoeGUI.start();
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

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {

			CustomJLabel Shoe2TicketsBought = new CustomJLabel(203, 242, 160, 20,
					"Tickets Bought: " + account.rafflesBought[1], shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe2TicketsBought.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {

			CustomJLabel Shoe3TicketsBought = new CustomJLabel(374, 242, 160, 20,
					"Tickets Bought: " + account.rafflesBought[2], shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe3TicketsBought.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {

			Shoe3ChanceofWinning = new CustomJLabel(374, 278, 170, 20,
					"Chance of Winning: " + (int) 100 * shoesInGui[2].totalRaffles / account.rafflesBought[2] + "%",
					shoes, null, null, new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe3ChanceofWinning.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
		} catch (ArithmeticException e) {

			Shoe3ChanceofWinning = new CustomJLabel(374, 278, 160, 20, "Chance of Winning: 0%", shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe3ChanceofWinning.setVisible(true);

		}

		try {

			Shoe1ChanceofWinning = new CustomJLabel(203, 278, 170, 20,
					"Chance of Winning: " + (int) 100 * shoesInGui[0].totalRaffles / account.rafflesBought[0] + "%",
					shoes, null, null, new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe1ChanceofWinning.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
		} catch (ArithmeticException e) {
			Shoe1ChanceofWinning = new CustomJLabel(203, 278, 160, 20, "Chance of Winning: 0%", shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe1ChanceofWinning.setVisible(true);
		}

		JPanel panel_10 = new JPanel();
		panel_10.setBounds(25, 11, 135, 135);
		shoes.add(panel_10);
		panel_10.setBackground(Color.LIGHT_GRAY);
		panel_10.setLayout(null);

		Image img0Pre = imagesFinal[0].getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		ImageIcon img0 = new ImageIcon(img0Pre);
		CustomJLabel lblImage_1 = new CustomJLabel(0, 0, 135, 135, "", panel_10, horizCenter, null, null, img0);
		lblImage_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ShoeGUI.s = shoesInGui[0];
				ShoeGUI.start();
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
		lblImage_1.setVisible(true);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(374, 11, 135, 135);
		shoes.add(panel_1_1);
		panel_1_1.setBackground(Color.LIGHT_GRAY);
		panel_1_1.setLayout(null);

		try {

			Image img3Pre = imagesFinal[2].getScaledInstance(200, 200, Image.SCALE_DEFAULT);
			ImageIcon img3 = new ImageIcon(img3Pre);

			CustomJLabel lblNewLabel_17 = new CustomJLabel(0, 0, 135, 135, "", panel_1_1, horizCenter, null, null,
					img3);
			lblNewLabel_17.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ShoeGUI.s = shoesInGui[2];
					ShoeGUI.start();
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
			lblNewLabel_17.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		try {

			CustomJLabel Shoe1Name = new CustomJLabel(25, 168, 135, 30, shoesInGui[0].shoeName, shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			fontSelector(Shoe1Name);
			Shoe1Name.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 1 null");
		}

		try {

			CustomJLabel Shoe2TicketsSold = new CustomJLabel(203, 201, 120, 30,
					"Tickets Sold: " + shoesInGui[1].totalRaffles, shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe2TicketsSold.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 2 null");
		}

		try {

			CustomJLabel Shoe1Description = new CustomJLabel(25, 309, 160, 140, "<html>" + shoesInGui[0].description,
					shoes, null, null, new Font("Segoe UI", Font.PLAIN, 10), null);
			Shoe1Description.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 1 null");
		}

		try {

			CustomJLabel Shoe2Description = new CustomJLabel(203, 309, 160, 140, "<html>" + shoesInGui[1].description,
					shoes, null, null, new Font("Segoe UI", Font.PLAIN, 10), null);
			Shoe2Description.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 2 null");
		}

		try {

			CustomJLabel Shoe3Description = new CustomJLabel(374, 309, 149, 140, "<html>" + shoesInGui[0].description,
					shoes, null, null, new Font("Segoe UI", Font.PLAIN, 10), null);
			Shoe3Description.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 1 null");
		}

		try {

			CustomJLabel Shoe2Name = new CustomJLabel(203, 168, 135, 30, shoesInGui[1].shoeName, shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe2Name.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 2 null");
		}

		try {

			CustomJLabel Shoe3Name = new CustomJLabel(374, 168, 135, 30, shoesInGui[2].shoeName, shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe3Name.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 3 null");
		}

		try {

			CustomJLabel Shoe1TicketsBought = new CustomJLabel(25, 237, 165, 30,
					"Tickets Bought: " + account.rafflesBought[0], shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe1TicketsBought.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 1 null");
		}

		try {

			CustomJLabel Shoe1TicketsSold = new CustomJLabel(25, 201, 120, 30,
					"Tickets Sold: " + shoesInGui[0].totalRaffles, shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe1TicketsSold.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 1 null");
		}

		try {

			CustomJLabel Shoe3TicketsSold = new CustomJLabel(374, 201, 120, 30,
					"Tickets Sold: " + shoesInGui[2].totalRaffles, shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe3TicketsSold.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 3 null");
		}

		try {

			Shoe2ChanceofWinning = new CustomJLabel(25, 278, 170, 20,
					"Chance of Winning: " + (int) 100 * shoesInGui[1].totalRaffles / account.rafflesBought[1] + "%",
					shoes, null, null, new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe2ChanceofWinning.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {
			Logger.LOG.error("Shoe 2 null");
		} catch (ArithmeticException e) {
			Shoe2ChanceofWinning = new CustomJLabel(25, 278, 160, 20, "Chance of Winning: 0%", shoes, null, null,
					new Font("Segoe UI", Font.PLAIN, 15), null);
			Shoe2ChanceofWinning.setVisible(true);

			Logger.LOG.info("Replace Chance Of Winning with 0%");
		}

		paymentHistory = new JPanel();
		paymentHistory.setBounds(10, 11, 544, 455);
		mainPane.add(paymentHistory);
		paymentHistory.setLayout(null);
		paymentHistory.setVisible(false);

		JPanel panel_4 = new JPanel();
		panel_4.setForeground(white);
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
				PaymentTable.account = account;
				PaymentTable.start();
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton_1.setBounds(120, 11, 268, 23);
		panel_4.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Feedback");
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Desktop desktop;
				if (Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
					URI mailto = null;
					try {
						mailto = new URI("mailto:coppersodds@gmail.com?subject=Feedback%20" + account.accountID);
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						desktop.mail(mailto);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// TODO fallback to some Runtime.exec(..) voodoo?
					throw new RuntimeException("Desktop doesn't support mailto; mail is dead anyway ;)");
				}

			}
		});
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnNewButton_2.setBounds(10, 11, 89, 23);
		panel_4.add(btnNewButton_2);

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

		CustomJLabel lblNewLabel_18 = new CustomJLabel(10, 98, 485, 202,
				"<html> In Coppers Odds, it is our goal to make sure you get your award which is a shoe fairly. If you have won the shoe raffle and forgot to come to get your shoe at a specific location, we will gladly hold onto your shoe for at most a week. However, if the shoe has not yet recieved by the time the week ends, the shoe will be used for other raffles. Additionally, we also care about the purchases you make and making sure that they are correct. If one of your purchases are incorrect, we will gladly accept any emails or feedback on our website. Thank you for reading, and I hope you follow our Terms of Service.",
				panel_4, horizLeft, white, new Font("Segoe UI", Font.PLAIN, 13), null);
		lblNewLabel_18.setVisible(true);

		CustomJLabel lblNewLabel_19 = new CustomJLabel(10, 48, 485, 64,
				"<html> Click on \"Feedback\" to email us. Click on \"Open Purchases Securely in a New Window\" to open up all transactions. Click \"Help\" if you would like to recieve some help.",
				panel_4, null, white, new Font("Segoe UI", Font.PLAIN, 13), null);
		lblNewLabel_19.setVisible(true);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 117, 505, 14);
		panel_4.add(separator_3);

		CustomJLabel lblNewLabel_4 = new CustomJLabel(10, 11, 524, 27, "Payment History", paymentHistory, horizCenter,
				null, new Font("Segoe UI", Font.BOLD, 20), null);
		lblNewLabel_4.setVisible(true);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(18, 49, 508, 2);
		paymentHistory.add(separator_1);

		CustomJLabel lblNewLabel_16 = new CustomJLabel(18, 55, 514, 70,
				"<html> Listed are recent payments you have made with raffle tickets and other purchases. Please email us if there are any problems right now with payments or other things.",
				paymentHistory, null, null, new Font("Segoe UI", Font.PLAIN, 15), null);
		lblNewLabel_16.setVisible(true);

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

		CustomJLabel lblEditAccount = new CustomJLabel(10, 11, 524, 39, "Edit Account", myAccount, horizCenter, null,
				new Font("Segoe UI", Font.BOLD, 20), null);
		lblEditAccount.setVisible(true);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 50, 524, 2);
		myAccount.add(separator_4);

		CustomJLabel lblNewUsername = new CustomJLabel(110, 61, 110, 27, "New Username:", myAccount, null, null,
				new Font("Segoe UI", Font.PLAIN, 15), null);
		lblNewUsername.setVisible(true);

		CustomJLabel lblFirstName = new CustomJLabel(110, 99, 110, 27, "First Name:", myAccount, null, null,
				new Font("Segoe UI", Font.PLAIN, 15), null);
		lblFirstName.setVisible(true);

		CustomJLabel lblLastName = new CustomJLabel(110, 137, 110, 27, "Last Name:", myAccount, null, null,
				new Font("Segoe UI", Font.PLAIN, 15), null);
		lblLastName.setVisible(true);

		CustomJLabel lblChangePassword = new CustomJLabel(10, 175, 524, 27, "Change Password:", myAccount, horizCenter,
				null, new Font("Segoe UI", Font.BOLD, 15), null);
		lblChangePassword.setVisible(true);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 213, 524, 2);
		myAccount.add(separator_5);

		CustomJLabel lblOldPassword = new CustomJLabel(10, 226, 110, 27, "Old Password:", myAccount, null, null,
				new Font("Segoe UI", Font.PLAIN, 15), null);
		lblOldPassword.setVisible(true);

		CustomJLabel lblNewPassword = new CustomJLabel(10, 265, 110, 27, "New Password:", myAccount, null, null,
				new Font("Segoe UI", Font.PLAIN, 15), null);
		lblNewPassword.setVisible(true);

		textField = new JTextField();
		textField.setBounds(230, 65, 230, 20);
		textField.setText(account.username);
		myAccount.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText(account.firstName);
		textField_1.setColumns(10);
		textField_1.setBounds(230, 105, 230, 20);
		myAccount.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setText(account.lastName);
		textField_2.setColumns(10);
		textField_2.setBounds(230, 142, 230, 20);
		myAccount.add(textField_2);

		CustomJLabel lblRetypeNewPassword = new CustomJLabel(10, 303, 237, 27, "Retype New Password to Confirm:",
				myAccount, null, null, new Font("Segoe UI", Font.PLAIN, 15), null);
		lblRetypeNewPassword.setVisible(true);

		passwordField = new JPasswordField();
		passwordField.setBounds(126, 232, 300, 20);
		myAccount.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(126, 271, 300, 20);
		myAccount.add(passwordField_1);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(244, 309, 290, 20);
		myAccount.add(passwordField_2);

		JEditorPane dtrpnTypeAboutYour = new JEditorPane();
		dtrpnTypeAboutYour.setText(account.biography);
		dtrpnTypeAboutYour.setBounds(10, 375, 524, 35);
		myAccount.add(dtrpnTypeAboutYour);

		CustomJLabel lblBiography = new CustomJLabel(10, 340, 524, 27, "Biography (One Sentence):", myAccount, null,
				null, new Font("Segoe UI", Font.PLAIN, 15), null);
		lblBiography.setVisible(true);

		JButton btnNewButton_3 = new JButton("Save");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!lblNewUsername.getText().equals("") && lblNewUsername.getText() != null) {
					account.username = textField.getText();
				}

				if (!lblFirstName.getText().equals("") && lblFirstName.getText() != null) {
					account.firstName = textField_1.getText();
				}

				if (!lblLastName.getText().equals("") && lblLastName.getText() != null) {
					account.lastName = textField_2.getText();
				}

				if (new String(passwordField.getPassword()).equals(account.password)) {

					if (new String(passwordField_1.getPassword()).equals(new String(passwordField_2.getPassword()))) {

						account.password = new String(passwordField_2.getPassword());

					}

				}

				Refresh.start("account");

				CredentialsLoaded.start();

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

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 794, 50);
		frmShoeRafflePrize.getContentPane().add(panel);
		panel.setLayout(null);

		CustomJLabel lblNewLabel_2 = new CustomJLabel(66, 11, 718, 28,
				"Coppers Odds Shoe Selling Service. Buy Raffles, get Shoes.", panel, horizCenter, Color.LIGHT_GRAY,
				new Font("Segoe UI Black", Font.BOLD, 20), null);
		lblNewLabel_2.setVisible(true);

		CustomJLabel lblNewLabel_1 = new CustomJLabel(10, 5, 46, 39, "", panel, null, null, null, new ImageIcon(
				StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/copporsOdds.png")));
		lblNewLabel_1.setVisible(true);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ChooseMusic.account = account;
				ChooseMusic.start();
			}
		});
		ImageIcon image = new ImageIcon(
				StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/music.jpg"));

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

		CustomJLabel lblNewLabel_3 = new CustomJLabel(10, 11, 160, 21, "Welcome", welcomePanel, horizCenter, white,
				new Font("Segoe UI", Font.BOLD, 20), null);
		lblNewLabel_3.setVisible(true);

		CustomJLabel lblDashBoard = new CustomJLabel(10, 67, 160, 38, " Dashboard", welcomePanel, horizCenter, white,
				new Font("Segoe UI", Font.BOLD, 15), new ImageIcon(
						StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/home.png")));
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
		lblDashBoard.setVisible(true);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 43, 160, 2);
		welcomePanel.add(separator);

		CustomJLabel lblShoes = new CustomJLabel(10, 115, 160, 38, " Shoes", welcomePanel, horizCenter, white,
				new Font("Segoe UI", Font.BOLD, 15), new ImageIcon(
						StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/shoe.png")));
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
		lblShoes.setVisible(true);

		CustomJLabel lblPaymentHistory = new CustomJLabel(10, 164, 160, 38, " Payments", welcomePanel, horizCenter,
				white, new Font("Dialog", Font.BOLD, 15), new ImageIcon(
						StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/money.png")));
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
		lblPaymentHistory.setVisible(true);

		CustomJLabel lblMyAccount = new CustomJLabel(10, 213, 160, 38, " My Account", welcomePanel, horizCenter, white,
				new Font("Segoe UI", Font.BOLD, 15), new ImageIcon(StartingWindow.class
						.getResource("/com/pulsebeat02/main/resources/images/mainmenu/account.png")));
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
		lblMyAccount.setVisible(true);

		CustomJLabel lblNewLabel_5 = new CustomJLabel(8, 397, 165, 51,
				"<html> Credits go to Brandon Li, the producer of this program and Nate, the hoster of the entire shoe fund. Version 1.0.0",
				welcomePanel, null, white, new Font("Segoe UI", Font.PLAIN, 10), null);
		lblNewLabel_5.setVisible(true);

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

				LoginPanel.start(true);
				Logger.LOG.info("Logging Out");
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(47, 360, 89, 23);
		btnNewButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		welcomePanel.add(btnNewButton);
		btnNewButton.setFocusPainted(false);

		CustomJLabel lblAboutUs = new CustomJLabel(0, 262, 180, 38, " About Us", welcomePanel, horizCenter, white,
				new Font("Segoe UI", Font.BOLD, 15), new ImageIcon(StartingWindow.class
						.getResource("/com/pulsebeat02/main/resources/images/mainmenu/infoButton/infoicon.png")));
		lblAboutUs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AboutUs.start();
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
		lblAboutUs.setVisible(true);

		JLayeredPane button_discription_panel = new JLayeredPane();
		button_discription_panel.setBounds(0, 0, 1, 1);
		frmShoeRafflePrize.getContentPane().add(button_discription_panel);

		if (!isConnected()) {

			lblNewLabel_14.setText("Bad");
			lblNewLabel_13.setIcon(new ImageIcon(
					StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/no-wifi.png")));
			Logger.LOG.info("Connection is Bad");

		}

		System.out.println("Connection is Tested");

		decorativeBorder = new JPanel();
		decorativeBorder.setBounds(0, 0, 564, 478);
		button_discription_panel.add(decorativeBorder);
		decorativeBorder.setBorder(new LineBorder(new Color(0, 0, 0)));
		decorativeBorder.setBackground(white);
		decorativeBorder.setLayout(null);

		tips = new JLayeredPane();
		tips.setBounds(220, 362, 564, 177);
		frmShoeRafflePrize.getContentPane().add(tips);
		tips.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBackground(white);
		panel_2.setBounds(0, 0, 564, 25);
		tips.add(panel_2);
		panel_2.setLayout(null);

		CustomJLabel lblNewLabel = new CustomJLabel(6, 5, 538, 15, "Tips", panel_2, horizLeft, gray,
				new Font("Segoe UI", Font.BOLD, 11), null);
		lblNewLabel.setVisible(true);

		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/refresh-button.png")));
		lblNewLabel_8.setBounds(24, 53, 46, 37);
		tips.add(lblNewLabel_8);

		JLabel lblclickOnThe = new JLabel("<html>Click on the \"Refresh\" to refresh everything.");
		lblclickOnThe.setHorizontalAlignment(horizCenter);
		lblclickOnThe.setForeground(gray);
		lblclickOnThe.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblclickOnThe.setBounds(80, 36, 158, 62);
		tips.add(lblclickOnThe);

		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(
				StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/logout.png")));
		lblNewLabel_9.setBounds(24, 107, 46, 47);
		tips.add(lblNewLabel_9);

		JLabel lblclickOnThe_1 = new JLabel("<html>Click on the \"Log Out\" to log out of your account.");
		lblclickOnThe_1.setHorizontalAlignment(horizCenter);
		lblclickOnThe_1.setForeground(gray);
		lblclickOnThe_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblclickOnThe_1.setBounds(80, 92, 158, 62);
		tips.add(lblclickOnThe_1);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(
				StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/dollarbill.png")));
		lblNewLabel_10.setBounds(317, 53, 46, 37);
		tips.add(lblNewLabel_10);

		JLabel lblclickOnThe_2 = new JLabel("<html>Click on the \"View Purchases\" to view all orders.");
		lblclickOnThe_2.setHorizontalAlignment(horizCenter);
		lblclickOnThe_2.setForeground(gray);
		lblclickOnThe_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblclickOnThe_2.setBounds(363, 38, 158, 62);
		tips.add(lblclickOnThe_2);

		System.out.println("Window Loaded In");
		
		BuyingTickets.account = account;

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
		graphic.drawImage(image, 0, 0, white, null);
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

	public static boolean isConnected() {

		try {
			final URL url = new URL("http://www.bing.com");
			final URLConnection conn = url.openConnection();
			conn.connect();
			conn.getInputStream().close();
			return true;
		} catch (MalformedURLException e) {
			Logger.LOG.error("URL has problems");
			throw new RuntimeException(e);
		} catch (IOException e) {
			return false;
		}

	}

	public static void restartApplication() {

		frmShoeRafflePrize.repaint();
		frmShoeRafflePrize.revalidate();

	}

	@Override
	protected Integer doInBackground() throws Exception {

		Random rand = new Random();

		boolean random = rand.nextBoolean();

		System.out.println("Enable Ads?: " + random);

		if (random) {

			SetupVideo.start();

		}

		return -1;
	}
}
