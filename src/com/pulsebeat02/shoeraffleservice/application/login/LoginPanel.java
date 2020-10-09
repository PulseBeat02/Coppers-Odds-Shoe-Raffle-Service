package com.pulsebeat02.shoeraffleservice.application.login;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;
import com.pulsebeat02.shoeraffleservice.application.application.account.Account;
import com.pulsebeat02.shoeraffleservice.application.application.account.ManageAccounts;
import com.pulsebeat02.shoeraffleservice.application.application.password.PasswordWindow;
import com.pulsebeat02.shoeraffleservice.application.application.privacy.Location;
import com.pulsebeat02.shoeraffleservice.application.application.privacy.SuspicousLogin;
import com.pulsebeat02.shoeraffleservice.application.application.register.AccountCreated;
import com.pulsebeat02.shoeraffleservice.application.application.register.RegisterPanel;
import com.pulsebeat02.shoeraffleservice.application.application.resetPassword.ResetGUI;
import com.pulsebeat02.shoeraffleservice.application.application.session.ManageSessions;
import com.pulsebeat02.shoeraffleservice.application.application.session.Session;
import com.pulsebeat02.shoeraffleservice.application.application.thread.ManageThreads;
import com.pulsebeat02.shoeraffleservice.application.application.thread.MusicThread;
import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPanel {

    public static JLabel incorrect;

    public static JFrame frmLogin;
    
    public static JTextField usernameField;
    public static JPasswordField passwordField;

    public static Account loggedInAccount;

    public static boolean keepLogin;

    public static Loading loadingFrame = new Loading();

    public void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException,
	    IllegalAccessException, UnsupportedLookAndFeelException {
	start(true);
    }

    public void start(boolean makeNew) throws IOException, ClassNotFoundException, InstantiationException,
	    IllegalAccessException, UnsupportedLookAndFeelException {

	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	if (!makeNew) {
	    initialize();
	} else if (makeNew) {
	    LoginPanel frame = new LoginPanel();
	    frame.getFrame().setVisible(true);
	}

    }

    public LoginPanel() throws IOException {
	initialize();
    }
    
    public JFrame getFrame() {
	return LoginPanel.frmLogin;
    }

    private static void initialize() throws IOException {

	ManageAccounts.start();

	frmLogin = new JFrame();
	frmLogin.setBounds(100, 100, 585, 380);
	frmLogin.setResizable(false);
	frmLogin.getContentPane().setBackground(new Color(180, 180, 180));
	frmLogin.getContentPane().setForeground(Color.WHITE);
	frmLogin.setTitle("Login");
	frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(
		AccountCreated.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/copporsOdds.png")));
	frmLogin.setUndecorated(true);
	frmLogin.getContentPane().setLayout(null);

	FrameDragListener frameDragListener = new FrameDragListener(frmLogin);
	frmLogin.addMouseListener(frameDragListener);
	frmLogin.addMouseMotionListener(frameDragListener);

	JLabel lblNewLabel = new JLabel("Coppers Odds");
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setForeground(Color.WHITE);
	lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
	lblNewLabel.setBounds(252, 11, 309, 73);
	frmLogin.getContentPane().add(lblNewLabel);

	JPanel panel = new JPanel();
	panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel.setForeground(Color.WHITE);
	panel.setBackground(new Color(180, 180, 180));
	panel.setBounds(252, 88, 309, 265);
	frmLogin.getContentPane().add(panel);
	panel.setLayout(null);

	JLabel lblUserLogin = new JLabel("User Login");
	lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
	lblUserLogin.setBounds(6, 11, 297, 32);
	lblUserLogin.setIcon(new ImageIcon(
		LoginPanel.class.getResource("/com/pulsebeat02/main/resources/images/login/userLogin.png")));
	panel.add(lblUserLogin);
	lblUserLogin.setForeground(Color.WHITE);
	lblUserLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));

	JLabel lblNewLabel_2 = new JLabel("");
	lblNewLabel_2.setForeground(Color.WHITE);
	lblNewLabel_2.setIcon(
		new ImageIcon(LoginPanel.class.getResource("/com/pulsebeat02/main/resources/images/login/user.png")));
	lblNewLabel_2.setBounds(10, 57, 37, 32);
	panel.add(lblNewLabel_2);

	usernameField = new JTextField();
	usernameField.setToolTipText("Enter Username Here");
	usernameField.setForeground(Color.BLACK);
	usernameField.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {
		incorrect.setVisible(false);
	    }
	});
	usernameField.setBounds(57, 63, 242, 20);
	panel.add(usernameField);
	usernameField.setColumns(10);

	JLabel label = new JLabel("");
	label.setIcon(
		new ImageIcon(LoginPanel.class.getResource("/com/pulsebeat02/main/resources/images/login/lock.png")));
	label.setForeground(Color.WHITE);
	label.setBounds(10, 113, 37, 32);
	panel.add(label);

	incorrect = new JLabel("Username or Password Incorrect");
	incorrect.setVisible(false);
	incorrect.setFont(new Font("Dialog", Font.BOLD, 10));
	incorrect.setForeground(Color.RED);
	incorrect.setBackground(Color.RED);
	incorrect.setBounds(115, 198, 192, 14);
	panel.add(incorrect);

	passwordField = new JPasswordField();
	passwordField.setForeground(Color.BLACK);
	passwordField.setToolTipText("Enter Password Here");
	passwordField.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
		}
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {
		incorrect.setVisible(false);
	    }
	});
	passwordField.setBounds(57, 120, 242, 20);
	panel.add(passwordField);

	JButton Login = new JButton("Login");
	Login.addMouseListener(new MouseAdapter() {

	    String accountLine = "";

	    @Override
	    public void mouseClicked(MouseEvent arg0) {

		loadingFrame.setVisible(true);

		frmLogin.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		frmLogin.setEnabled(false);

		boolean isLogin = false;

		List<String> lines = null;

		String cwd = System.getProperty("user.dir");

		try {
		    lines = Files.readAllLines(Paths.get(cwd + "/loginUsers"), StandardCharsets.UTF_8);
		} catch (IOException e2) {
		    e2.printStackTrace();
		}

		for (int i = 0; i < lines.size(); i++) {

		    String[] currentLine = lines.get(i).split(",");

		    if (currentLine[0].equals(usernameField.getText())
			    && currentLine[3].equals(new String(passwordField.getPassword()))) {

			isLogin = true;

			Logger.LOG.info("Login Successful");

			accountLine = lines.get(i);

			try {
			    ManageAccounts.read();
			} catch (IOException e1) {
			    e1.printStackTrace();
			}

			Logger.LOG.info("Logged In");

			try {
			    Account.findAccount(accountLine);
			} catch (IOException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}

			Thread loginThread = new Thread(() -> {
			    Session session = new Session(new Location(), loggedInAccount);
			    ManageSessions.allSessions.put(session.location.IP, session);
			});

			loginThread.start();

			try {
			    ManageThreads.addThread(loginThread, ShoeRaffleService.getInstance().cpuThreads);
			} catch (Exception e) {
			    e.printStackTrace();
			    System.exit(0);
			}

			SuspicousLogin login = new SuspicousLogin();
			login.start();

			break;

		    }

		    else {

			isLogin = false;
			Logger.LOG.info("Didn't Find Login... Trying Again");

		    }

		}

		if (!isLogin) {

		    incorrect.setVisible(true);
		    loadingFrame.setVisible(false);

		    isLogin = false;

		    Logger.LOG.info("Login Not Found");

		}

		frmLogin.setCursor(Cursor.getDefaultCursor());

	    }

	});
	Login.setFont(new Font("Segoe UI", Font.BOLD, 15));
	Login.setForeground(Color.GRAY);
	Login.setBackground(Color.LIGHT_GRAY);
	Login.setBounds(10, 218, 76, 32);
	panel.add(Login);

	JCheckBox keepMeLoggedIn = new JCheckBox("Keep Me Logged In", false);
	keepMeLoggedIn.setOpaque(false);
	keepMeLoggedIn.setToolTipText("Disabled due to Deprecation");
	keepMeLoggedIn.setBackground(Color.LIGHT_GRAY);
	keepMeLoggedIn.setForeground(SystemColor.window);
	keepMeLoggedIn.setBounds(10, 163, 289, 23);
	keepMeLoggedIn.setEnabled(false);
	panel.add(keepMeLoggedIn);
	keepMeLoggedIn.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent e) {

		if (e.getStateChange() == ItemEvent.SELECTED) {

		    keepLogin = true;
		    Logger.LOG.info("User Keeping Login");

		} else {

		    keepLogin = false;
		    Logger.LOG.info("User Not Keeping Login");

		    String cwd = System.getProperty("user.dir");
		    try {
			PrintWriter writer = new PrintWriter(cwd + "/lastLogin");
			writer.write("");
			writer.write("0");
			writer.close();
		    } catch (IOException e1) {
			e1.printStackTrace();
		    }

		}

	    }

	});

	JButton Close = new JButton("Close");
	Close.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		Logger.LOG.info("Closing Window");
		frmLogin.setVisible(false); // you can't see me!
		frmLogin.dispose(); // Destroy the JFrame object
	    }
	});
	Close.setForeground(Color.GRAY);
	Close.setFont(new Font("Segoe UI", Font.BOLD, 15));
	Close.setBackground(Color.LIGHT_GRAY);
	Close.setBounds(223, 218, 76, 32);
	panel.add(Close);

	JLabel lblWelcome = new JLabel("Welcome!");
	lblWelcome.setVisible(false);
	lblWelcome.setForeground(SystemColor.window);
	lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblWelcome.setBounds(13, 193, 279, 14);
	panel.add(lblWelcome);

	JButton btnRegister = new JButton("Register");
	btnRegister.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		frmLogin.setVisible(false);
		frmLogin.dispose();
		RegisterPanel.main(null);
	    }
	});
	btnRegister.setForeground(Color.GRAY);
	btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 15));
	btnRegister.setBackground(new Color(180, 180, 180));
	btnRegister.setBounds(107, 218, 95, 32);
	panel.add(btnRegister);

	JPanel panel_1 = new JPanel();
	panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_1.setForeground(SystemColor.window);
	panel_1.setBackground(new Color(180, 180, 180));
	panel_1.setBounds(8, 11, 567, 358);
	frmLogin.getContentPane().add(panel_1);
	panel_1.setLayout(null);

	JLabel LoginImage = new JLabel("");
	LoginImage.setBounds(10, 114, 184, 173);
	panel_1.add(LoginImage);
	LoginImage.setHorizontalAlignment(SwingConstants.CENTER);

	ImageIcon imageIcon = new ImageIcon(
		LoginPanel.class.getResource("/com/pulsebeat02/main/resources/images/login/login.png"));
	Image image = imageIcon.getImage();
	Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
	imageIcon = new ImageIcon(newimg);
	LoginImage.setIcon(imageIcon);

	JLabel lblUserPortal = new JLabel("User Portal");
	lblUserPortal.setBounds(24, 71, 189, 52);
	panel_1.add(lblUserPortal);
	lblUserPortal.setForeground(SystemColor.window);
	lblUserPortal.setHorizontalAlignment(SwingConstants.CENTER);
	lblUserPortal.setFont(new Font("Segoe UI", Font.BOLD, 30));

	JLabel lblNewLabel_1 = new JLabel("Reset Password");
	lblNewLabel_1.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseEntered(MouseEvent arg0) {
		lblNewLabel_1.setText("<HTML><U>Reset Password</U></HTML>");
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
		lblNewLabel_1.setText("Reset Password");
	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {
		ResetGUI.main(null);
	    }
	});
	lblNewLabel_1.setForeground(Color.WHITE);
	lblNewLabel_1.setBounds(15, 333, 203, 14);
	panel_1.add(lblNewLabel_1);

	BackgroundMenuBar menuBar = new BackgroundMenuBar();
	menuBar.setBounds(10, 11, 180, 15);
	panel_1.add(menuBar);
	menuBar.setBackground(new Color(180, 180, 180));
	menuBar.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
	menuBar.setBorderPainted(false);
	menuBar.setForeground(Color.DARK_GRAY);
	menuBar.setColor(new Color(180, 180, 180));

	JMenu admin = new JMenu("Administrator");
	admin.setForeground(new Color(180, 180, 180));
	admin.setBackground(new Color(180, 180, 180));
	JMenu window = new JMenu("Window");
	window.setBackground(new Color(180, 180, 180));
	window.setForeground(new Color(180, 180, 180));

	menuBar.add(admin);
	menuBar.add(window);

	JMenuItem menuItem1 = new JMenuItem("Enter Password", KeyEvent.VK_T);
	menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
	admin.add(menuItem1);
	menuItem1.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		new PasswordWindow();
	    }
	});

	JMenuItem menuItem2 = new JMenuItem("Quit", KeyEvent.VK_T);
	menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
	window.add(menuItem2);
	menuItem2.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		Logger.LOG.info("Quitting");
		frmLogin.setVisible(false);
		frmLogin.dispose();
		Logger.LOG.info("Closing Window");
	    }
	});

	frmLogin.setVisible(true);
	ManageThreads.musicThread = new MusicThread();

    }

    public static class FrameDragListener extends MouseAdapter {

	private final JFrame frame;
	private Point mouseDownCompCoords = null;

	public FrameDragListener(JFrame frame) {
	    this.frame = frame;
	}

	public void mouseReleased(MouseEvent e) {
	    mouseDownCompCoords = null;
	}

	public void mousePressed(MouseEvent e) {
	    mouseDownCompCoords = e.getPoint();
	}

	public void mouseDragged(MouseEvent e) {
	    Point currCoords = e.getLocationOnScreen();
	    frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
	}

    }

    public static class BackgroundMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Color bgColor = Color.WHITE;

	public void setColor(Color color) {
	    bgColor = color;
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setColor(bgColor);
	    g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

	}
    }

    public static String motherBoardID() throws IOException {
	return Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/lastLogin")).get(0);
    }
}
