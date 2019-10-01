package com.pulsebeat02.main.gui.windows.resetPassword;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

import com.pulsebeat02.main.gui.windows.account.Account;
import com.pulsebeat02.main.gui.windows.account.ManageAccounts;
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

	static boolean isDisabled = false;

	static JButton okButton;
	static JPanel buttonPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ResetGUI dialog = new ResetGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ResetGUI() {

		Logger.LOG.info("Opening Reset GUI");

		int random = (int) (Math.random() * 999999999 + 1);

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ResetGUI.class.getResource("/com/pulsebeat02/main/resources/images/password.png")));
		setTitle("Reset Password");
		setBounds(100, 100, 450, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel(
					"<html> Please enter your email to your account so we can find it and email you a code that will allow you to reset your pasword.");
			lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblNewLabel.setBounds(10, 11, 400, 64);
			contentPanel.add(lblNewLabel);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 86, 400, 2);
			contentPanel.add(separator);
		}
		{
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 15));
			lblEmail.setBounds(20, 103, 59, 30);
			contentPanel.add(lblEmail);
		}

		JLabel invalid = new JLabel("Email is Invalid");
		invalid.setVisible(false);
		invalid.setForeground(Color.RED);
		invalid.setBounds(10, 145, 182, 14);
		contentPanel.add(invalid);

		JLabel accountInvalid = new JLabel("Account Not Found");
		accountInvalid.setVisible(false);
		accountInvalid.setForeground(Color.RED);
		accountInvalid.setBounds(10, 171, 135, 14);
		contentPanel.add(accountInvalid);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
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
		textField.setBounds(79, 110, 331, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			buttonPane = new JPanel();
			buttonPane.setBounds(204, 141, 220, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				okButton = new JButton("Send Email");
				okButton.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {

						if (!isDisabled) {

							System.out.println(textField.getText());

							for (int i = 0; i < ManageAccounts.allAccounts.size(); i++) {

								System.out.println(i);
								
								Account key = (Account) ManageAccounts.allAccounts.keySet().toArray()[i];

								if (key.email.equals(textField.getText())) {

									Logger.LOG.info("Sending Email to Reset Password");

									Properties prop = new Properties();
									prop.put("mail.smtp.host", "smtp.gmail.com");
									prop.put("mail.smtp.port", "587");
									prop.put("mail.smtp.auth", "true");
									prop.put("mail.smtp.starttls.enable", "true"); // TLS
									
									// get Session
									Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
										protected PasswordAuthentication getPasswordAuthentication() {
											return new PasswordAuthentication("coppersodds@gmail.com", "Chatimac1$");
										}
									});
									// compose message
									try {
										MimeMessage message = new MimeMessage(session);
										message.addRecipient(Message.RecipientType.TO,
												new InternetAddress(textField.getText()));
										message.setSubject("Resetting Your Coppers Odds Account Password");
										message.setText(
												"Hello! We are Coppers Odds. We have heard that you wanted to change your password. To do so, please, enter this numerical code: "
														+ random + "."
														+ "This code will change everytime you change your password, so please keep the change password window open! Thank You - Coppers Odds");
										// send message
										Transport.send(message);
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

			}
			okButton.setBackground(Color.WHITE);
			okButton.setFocusable(false);
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
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
		}
	}

	public static void disabled() {

		okButton.setEnabled(false);
		isDisabled = true;

	}
}
