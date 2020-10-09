package com.pulsebeat02.main.gui.application.register;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;

import com.pulsebeat02.main.gui.application.account.Account;
import com.pulsebeat02.main.gui.application.account.ManageAccounts;
import com.pulsebeat02.main.gui.application.login.LoginPanel;
import com.pulsebeat02.main.gui.application.payment.ManagePayments;
import com.pulsebeat02.main.gui.application.payment.Payment;
import com.pulsebeat02.main.util.logging.Logger;

import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterPanel {

    public BufferedWriter bw;
    public PrintWriter out;

    public JFrame frmLogin;
    public JTextField email;
    public JPasswordField passwordField;
    public JTextField username;

    public Account account;

    public boolean noSignUp = false;

    boolean[] allFilledIn = new boolean[3];

    public RegisterPanel() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    initialize();
		    frmLogin.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

	this.frmLogin = new JFrame();
	this.frmLogin.getContentPane().setBackground(new Color(180, 180, 180));
	this.frmLogin.getContentPane().setForeground(Color.WHITE);
	this.frmLogin.setTitle("Login");
	this.frmLogin.setResizable(false);
	this.frmLogin.setBounds(100, 100, 500, 472);
	this.frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.frmLogin.setUndecorated(true);
	this.frmLogin.getContentPane().setLayout(null);

	FrameDragListener frameDragListener = new FrameDragListener(frmLogin);
	this.frmLogin.addMouseListener(frameDragListener);
	this.frmLogin.addMouseMotionListener(frameDragListener);

	JPanel panel = new JPanel();
	panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel.setBackground(new Color(180, 180, 180));
	panel.setBounds(10, 11, 480, 450);
	panel.setLayout(null);
	this.frmLogin.getContentPane().add(panel);

	JLabel lblNewLabel = new JLabel(" Registration Form for Coppers Odds");
	lblNewLabel.setIcon(new ImageIcon(
		RegisterPanel.class.getResource("/com/pulsebeat02/main/resources/images/register/register.png")));
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setForeground(Color.WHITE);
	lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
	lblNewLabel.setBounds(10, 11, 460, 77);
	panel.add(lblNewLabel);

	JLabel lblWelcomeThankYou = new JLabel(
		"<html> Welcome! Thank you for deciding to choose Coppers Odds. Start by filling in the info to create an account, and start buying raffles! Thank you for reading.");
	lblWelcomeThankYou.setHorizontalAlignment(SwingConstants.CENTER);
	lblWelcomeThankYou.setForeground(Color.WHITE);
	lblWelcomeThankYou.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblWelcomeThankYou.setBounds(23, 102, 437, 63);
	panel.add(lblWelcomeThankYou);

	JSeparator separator = new JSeparator();
	separator.setForeground(Color.WHITE);
	separator.setBounds(23, 85, 437, 10);
	panel.add(separator);

	JLabel lblEmail = new JLabel(" Email:");
	lblEmail.setIcon(new ImageIcon(
		RegisterPanel.class.getResource("/com/pulsebeat02/main/resources/images/register/email.png")));
	lblEmail.setForeground(Color.WHITE);
	lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblEmail.setBounds(20, 245, 118, 63);
	panel.add(lblEmail);

	JLabel lblPassword = new JLabel(" Password:");
	lblPassword.setIcon(new ImageIcon(
		RegisterPanel.class.getResource("/com/pulsebeat02/main/resources/images/register/password.png")));
	lblPassword.setForeground(Color.WHITE);
	lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblPassword.setBounds(20, 300, 128, 63);
	panel.add(lblPassword);

	JLabel invalid = new JLabel("Invalid Email");
	invalid.setVisible(false);
	invalid.setForeground(Color.RED);
	invalid.setBounds(162, 248, 295, 14);
	panel.add(invalid);

	JSeparator separator_1 = new JSeparator();
	separator_1.setBackground(Color.WHITE);
	separator_1.setForeground(Color.WHITE);
	separator_1.setBounds(23, 170, 437, 10);
	panel.add(separator_1);

	int[] nothing = { 0, 0, 0 };

	JLabel lblNewLabel_1 = new JLabel(
		"<html> The username or email you have chosen is either already taken or used. Please login or type a new username.");
	lblNewLabel_1.setForeground(Color.RED);
	lblNewLabel_1.setBounds(25, 360, 427, 35);
	lblNewLabel_1.setVisible(false);
	panel.add(lblNewLabel_1);

	JButton btnSignUp = new JButton("Sign Up");
	btnSignUp.setBackground(Color.LIGHT_GRAY);
	btnSignUp.setForeground(Color.DARK_GRAY);
	btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 15));
	btnSignUp.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (!checkValues()) {
		    btnSignUp.setEnabled(false);
		    MissingFields.start();
		} else {
		    btnSignUp.setEnabled(true);
		}

		if (Account.checkAccountDuplicate(username.getText(), email.getText())) {
		    lblNewLabel_1.setVisible(true);
		} else {
		    if (new String(passwordField.getPassword()).equals("") || username.getText().equals("")
			    || email.getText().equals("")) {
			invalid.setVisible(true);
			btnSignUp.setEnabled(false);
			noSignUp = true;
		    }
		    if (!noSignUp) {
			LocalDate localDate = LocalDate.now();
			account = new Account(username.getText(), "firstName", "lastName",
				new String(passwordField.getPassword()),
				DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate), email.getText(),
				"Type About Your Self", 0, 0, nothing, false, Account.getMotherboardSN(), null, 3);
			Logger.LOG.info("New Account Made: " + account);
			ManageAccounts.allAccounts.put(account.accountID, account);
			String accountString = Account.toStringText(account);
			Account.printToFile(accountString);
			Payment payment = new Payment("Example Payment",
				DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate),
				"Made Free Account, No Charges", "Don't worry about this!", 0.00, null, true, account);
			ManagePayments.allPayments.put(payment.id, payment);
			AccountCreated.start();
			frmLogin.setVisible(false);
			frmLogin.dispose();
		    } else {
			Logger.LOG.info("Unable to Sign Up");
		    }

		}

	    }

	});

	btnSignUp.setBounds(23, 402, 89, 37);
	panel.add(btnSignUp);

	JLabel shortUsername = new JLabel("Username Must Be Less Than 15 Characters");
	shortUsername.setVisible(false);
	shortUsername.setForeground(Color.RED);
	shortUsername.setBounds(162, 195, 295, 14);
	panel.add(shortUsername);

	JLabel lblNewLabel_2 = new JLabel(
		"<html> You must have at least 8 characters in your passcode, one digit, one lowercase, one uppercase, one special character, and no white-spaces.");
	lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
	lblNewLabel_2.setForeground(Color.RED);
	lblNewLabel_2.setBounds(162, 292, 292, 35);
	lblNewLabel_2.setVisible(false);
	panel.add(lblNewLabel_2);

	this.email = new JTextField();
	this.email.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

		if (!(email.getText()).matches(regex)) {

		    invalid.setVisible(true);
		    btnSignUp.setEnabled(false);
		    noSignUp = true;

		    Logger.LOG.info("Email Invalid");

		}

		else {

		    allFilledIn[1] = true;
		    invalid.setVisible(false);
		    btnSignUp.setEnabled(true);
		    noSignUp = false;

		    Logger.LOG.info("Email Valid");

		}
	    }
	});
	this.email.setBounds(160, 269, 295, 20);
	this.email.setColumns(10);
	panel.add(email);

	this.passwordField = new JPasswordField();
	this.passwordField.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {

		String regex = "\"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}$\"";

		if (!new String(passwordField.getPassword()).matches(regex)) {

		    lblNewLabel_2.setVisible(true);

		}

		else {

		    lblNewLabel_2.setVisible(false);

		}

		allFilledIn[2] = true;
	    }
	});
	this.passwordField.setBounds(160, 324, 295, 20);
	panel.add(passwordField);

	JLabel lblUsername = new JLabel(" Username:");
	lblUsername.setIcon(new ImageIcon(
		RegisterPanel.class.getResource("/com/pulsebeat02/main/resources/images/register/avatar.png")));
	lblUsername.setForeground(Color.WHITE);
	lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblUsername.setBounds(20, 190, 167, 63);
	panel.add(lblUsername);
	this.allFilledIn[0] = false;
	this.username = new JTextField();
	this.username.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent e) {
		if (username.getText().length() > 15) {

		    shortUsername.setVisible(true);
		    username.setText(username.getText().substring(0, 15));
		    btnSignUp.setEnabled(false);
		    noSignUp = true;

		    Logger.LOG.info("Username Invalid");

		} else {

		    allFilledIn[0] = true;
		    shortUsername.setVisible(false);
		    btnSignUp.setEnabled(true);
		    noSignUp = false;

		    Logger.LOG.info("Username Valid");

		}
	    }
	});
	this.username.setColumns(10);
	this.username.setBounds(160, 215, 295, 20);
	panel.add(username);

	JButton btnQuit = new JButton("Quit");
	btnQuit.setBackground(Color.LIGHT_GRAY);
	btnQuit.setForeground(Color.DARK_GRAY);
	btnQuit.setFont(new Font("Segoe UI", Font.BOLD, 15));
	btnQuit.addMouseListener(new MouseAdapter() {

	    @Override
	    public void mouseClicked(MouseEvent e) {
		frmLogin.setVisible(false);
		frmLogin.dispose();
	    }
	});
	btnQuit.setBounds(370, 402, 89, 37);
	panel.add(btnQuit);

	JButton btnLogin = new JButton("Login");
	btnLogin.setBackground(Color.LIGHT_GRAY);
	btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
	btnLogin.setForeground(Color.DARK_GRAY);
	btnLogin.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		Logger.LOG.info("Closing Window");
		frmLogin.setVisible(false);
		frmLogin.dispose();

		Logger.LOG.info("Opening Login Window");
		try {
		    LoginPanel.start(true);
		} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException
			| UnsupportedLookAndFeelException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	});
	btnLogin.setBounds(200, 402, 89, 37);
	panel.add(btnLogin);

	JSeparator separator_2 = new JSeparator();
	separator_2.setBounds(23, 379, 437, 2);
	panel.add(separator_2);

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

    public boolean checkValues() {

	if (allFilledIn[0] && allFilledIn[1] && allFilledIn[2]) {

	    return true;

	} else {

	    return false;

	}

    }
}
