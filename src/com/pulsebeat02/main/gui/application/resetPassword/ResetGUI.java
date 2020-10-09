package com.pulsebeat02.main.gui.application.resetPassword;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pulsebeat02.main.gui.application.account.Account;
import com.pulsebeat02.main.gui.application.account.ManageAccounts;
import com.pulsebeat02.main.util.logging.Logger;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.util.Properties;

public class ResetGUI extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;

    public boolean isDisabled = false;

    public JButton okButton;
    public JPanel buttonPane;

    public ResetGUI() {

	int random = (int) (Math.random() * 999999999 + 1);

	this.setIconImage(Toolkit.getDefaultToolkit()
		.getImage(ResetGUI.class.getResource("/com/pulsebeat02/main/resources/images/password.png")));
	this.setTitle("Reset Password");
	this.setBounds(100, 100, 450, 225);
	this.getContentPane().setLayout(new BorderLayout());
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.getContentPane().add(contentPanel, BorderLayout.CENTER);
	this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.contentPanel.setLayout(null);

	JLabel lblNewLabel = new JLabel(
		"<html> Please enter your email to your account so we can find it and email you a code that will allow you to reset your pasword.");
	lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
	lblNewLabel.setBounds(10, 11, 400, 64);
	this.contentPanel.add(lblNewLabel);

	JSeparator separator = new JSeparator();
	separator.setBounds(10, 86, 400, 2);
	this.contentPanel.add(separator);

	JLabel lblEmail = new JLabel("Email:");
	lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblEmail.setBounds(20, 103, 59, 30);
	this.contentPanel.add(lblEmail);

	JLabel invalid = new JLabel("Email is Invalid");
	invalid.setVisible(false);
	invalid.setForeground(Color.RED);
	invalid.setBounds(10, 145, 182, 14);
	this.contentPanel.add(invalid);

	JLabel accountInvalid = new JLabel("Account Not Found");
	accountInvalid.setVisible(false);
	accountInvalid.setForeground(Color.RED);
	accountInvalid.setBounds(10, 171, 135, 14);
	this.contentPanel.add(accountInvalid);

	this.textField = new JTextField();
	this.textField.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyTyped(KeyEvent arg0) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

		if (!textField.getText().matches(regex)) {

		    invalid.setVisible(true);
		    disabled();

		} else {

		    okButton.setEnabled(true);
		    invalid.setVisible(false);
		    isDisabled = false;

		}

	    }
	});
	this.textField.setBounds(79, 110, 331, 20);
	this.contentPanel.add(textField);
	this.textField.setColumns(10);

	this.buttonPane = new JPanel();
	this.buttonPane.setBounds(204, 141, 220, 33);
	this.contentPanel.add(buttonPane);
	this.buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

	this.okButton = new JButton("Send Email");
	this.okButton.addMouseListener(new MouseAdapter() {

	    @Override
	    public void mouseClicked(MouseEvent e) {

		if (!isDisabled) {

		    System.out.println(textField.getText());

		    for (Account key : ManageAccounts.allAccounts.values()) {

			if (key.email.equals(textField.getText())) {

			    Logger.LOG.info("Sending Email to Reset Password");

			    Properties prop = new Properties();
			    prop.put("mail.smtp.starttls.enable", "true"); // TLS
			    prop.put("mail.smtp.host", "smtp.gmail.com");
			    prop.put("mail.smtp.user", "coppersodds@gmail.com");
			    prop.put("mail.smtp.password", "Chatimac1$");
			    prop.put("mail.smtp.port", "587"); // 465
			    prop.put("mail.smtp.auth", "true");

			    Session session = Session.getDefaultInstance(prop, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
				    return new PasswordAuthentication("coppersodds@gmail.com", "Chatimac1$");
				}
			    });

			    try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress("coppersodds@gmail.com"));
				message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(textField.getText()));
				message.setSubject("Resetting Your Coppers Odds Account Password");
				message.setText(
					"Hello! We are Coppers Odds. We have heard that you wanted to change your password. To do so, please, enter this numerical code: "
						+ random + "."
						+ "This code will change everytime you change your password, so please keep the change password window open! Thank You - Coppers Odds");
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("coppersodds@gmail.com"));

				Transport transport = session.getTransport("smtp");
				transport.connect("smtp.gmail.com", "coppersodds@gmail.com", "Chatimac1$");
				transport.sendMessage(message, message.getAllRecipients());

				System.out.println("message sent successfully");
			    } catch (MessagingException e1) {
				throw new RuntimeException(e1);
			    }

			    Logger.LOG.info("Code for Reset is " + BigInteger.valueOf(random).toByteArray());

			    break;

			}

			else {

			    Logger.LOG.error("Didn't Find Account Associated With Email");
			    accountInvalid.setVisible(true);

			}

		    }

		    Logger.LOG.info("Closing");

		    setVisible(false);
		    dispose();

		    EnterCode.code = random;
		    EnterCode.main(null);

		}

	    }

	});

	this.okButton.setBackground(Color.WHITE);
	this.okButton.setFocusable(false);
	this.okButton.setActionCommand("OK");
	this.buttonPane.add(okButton);
	getRootPane().setDefaultButton(okButton);

	JButton cancelButton = new JButton("Close");
	cancelButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent arg0) {
		Logger.LOG.info("Closing");
		setVisible(false);
		dispose();
	    }
	});
	cancelButton.setBackground(Color.WHITE);
	cancelButton.setFocusable(false);
	cancelButton.setActionCommand("Cancel");
	buttonPane.add(cancelButton);

	this.setVisible(true);
    }

    public void disabled() {
	this.okButton.setEnabled(false);
	this.isDisabled = true;
    }
}
