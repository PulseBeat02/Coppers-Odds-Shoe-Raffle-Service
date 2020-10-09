package com.pulsebeat02.shoeraffleservice.application.settings;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.pulsebeat02.shoeraffleservice.application.CredentialsLoaded;
import com.pulsebeat02.shoeraffleservice.application.Refresh;
import com.pulsebeat02.shoeraffleservice.application.application.account.Account;
import com.pulsebeat02.shoeraffleservice.application.application.builder.CustomJLabel;
import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

public class AccountSettings extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {

	Account ac = new Account("PulseBeat_02", "Brandon", "Li", "qwerty", "", null, null, 0, 0, null, false, null,
		null, 0);
	JFrame myFrame = new JFrame("Test");

	AccountSettings p = new AccountSettings(ac);

	myFrame.getContentPane().add(p);
	myFrame.pack();
	myFrame.setVisible(true);

    }

    public AccountSettings(Account account) {

	int horizCenter = SwingConstants.CENTER;

	this.setBounds(10, 11, 415, 385);
	this.setLayout(null);
	this.setVisible(false);

	CustomJLabel lblEditAccount = new CustomJLabel(10, 11, 524, 39, "Edit Account", this, horizCenter, null,
		new Font("Segoe UI", Font.BOLD, 20), null);
	lblEditAccount.setBounds(10, 11, 395, 39);
	lblEditAccount.setVisible(true);

	JSeparator separator_4 = new JSeparator();
	separator_4.setBounds(10, 50, 395, 2);
	this.add(separator_4);

	CustomJLabel lblNewUsername = new CustomJLabel(110, 61, 110, 27, "New Username:", this, null, null,
		new Font("Segoe UI", Font.PLAIN, 15), null);
	lblNewUsername.setSize(110, 25);
	lblNewUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	lblNewUsername.setLocation(10, 60);
	lblNewUsername.setVisible(true);

	CustomJLabel lblFirstName = new CustomJLabel(110, 99, 110, 27, "First Name:", this, null, null,
		new Font("Segoe UI", Font.PLAIN, 15), null);
	lblFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	lblFirstName.setLocation(10, 85);
	lblFirstName.setVisible(true);

	CustomJLabel lblLastName = new CustomJLabel(110, 137, 110, 27, "Last Name:", this, null, null,
		new Font("Segoe UI", Font.PLAIN, 15), null);
	lblLastName.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	lblLastName.setLocation(10, 110);
	lblLastName.setVisible(true);

	CustomJLabel lblChangePassword = new CustomJLabel(10, 175, 524, 27, "Change Password:", this, horizCenter, null,
		new Font("Segoe UI", Font.BOLD, 15), null);
	lblChangePassword.setBounds(10, 148, 395, 27);
	lblChangePassword.setVisible(true);

	JSeparator separator_5 = new JSeparator();
	separator_5.setBounds(10, 186, 395, 2);
	this.add(separator_5);

	CustomJLabel lblOldPassword = new CustomJLabel(10, 226, 110, 27, "Old Password:", this, null, null,
		new Font("Segoe UI", Font.PLAIN, 15), null);
	lblOldPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	lblOldPassword.setLocation(10, 200);
	lblOldPassword.setVisible(true);

	CustomJLabel lblNewPassword = new CustomJLabel(10, 265, 110, 27, "New Password:", this, null, null,
		new Font("Segoe UI", Font.PLAIN, 15), null);
	lblNewPassword.setLocation(10, 225);
	lblNewPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	lblNewPassword.setVisible(true);

	JTextField textField = new JTextField();
	textField.setBounds(126, 62, 280, 20);
	textField.setText(account.username);
	this.add(textField);
	textField.setColumns(10);

	JTextField textField_1 = new JTextField();
	textField_1.setText(account.firstName);
	textField_1.setColumns(10);
	textField_1.setBounds(126, 87, 280, 20);
	this.add(textField_1);

	JTextField textField_2 = new JTextField();
	textField_2.setText(account.lastName);
	textField_2.setColumns(10);
	textField_2.setBounds(126, 112, 280, 20);
	this.add(textField_2);

	CustomJLabel lblRetypeNewPassword = new CustomJLabel(10, 303, 237, 27, "Retype New Password to Confirm:", this,
		null, null, new Font("Segoe UI", Font.PLAIN, 15), null);
	lblRetypeNewPassword.setLocation(10, 250);
	lblRetypeNewPassword.setText("Confirm Password:");
	lblRetypeNewPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	lblRetypeNewPassword.setVisible(true);

	JPasswordField passwordField = new JPasswordField();
	passwordField.setBounds(126, 202, 280, 20);
	this.add(passwordField);

	JPasswordField passwordField_1 = new JPasswordField();
	passwordField_1.setBounds(126, 227, 280, 20);
	this.add(passwordField_1);

	JPasswordField passwordField_2 = new JPasswordField();
	passwordField_2.setBounds(126, 252, 279, 20);
	this.add(passwordField_2);

	JEditorPane dtrpnTypeAboutYour = new JEditorPane();
	dtrpnTypeAboutYour.setText(account.biography);
	dtrpnTypeAboutYour.setBounds(10, 313, 395, 61);
	this.add(dtrpnTypeAboutYour);

	CustomJLabel lblBiography = new CustomJLabel(10, 340, 524, 27, "Biography (One Sentence):", this, null, null,
		new Font("Segoe UI", Font.PLAIN, 15), null);
	lblBiography.setSize(395, 27);
	lblBiography.setText("Biography:");
	lblBiography.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	lblBiography.setLocation(10, 288);
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

		try {
		    Refresh.start("account");
		} catch (IOException e1) {
		    e1.printStackTrace();
		}

		CredentialsLoaded.start();

		Logger.LOG.info("New Account Credentials Saved");

	    }
	});
	btnNewButton_3.setFocusable(false);
	btnNewButton_3.setBackground(Color.LIGHT_GRAY);
	btnNewButton_3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	btnNewButton_3.setBounds(10, 421, 89, 23);
	this.add(btnNewButton_3);

	JButton btnClose = new JButton("Close");
	btnClose.setFocusable(false);
	btnClose.setBackground(Color.LIGHT_GRAY);
	btnClose.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	btnClose.setBounds(445, 421, 89, 23);
	this.add(btnClose);

	JButton btnMainMenu = new JButton("Main Menu");
	btnMainMenu.setFocusable(false);
	btnMainMenu.setBackground(Color.LIGHT_GRAY);
	btnMainMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	btnMainMenu.setBounds(217, 421, 120, 23);
	this.add(btnMainMenu);

    }

}
