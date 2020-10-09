package com.pulsebeat02.shoeraffleservice.application.password;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PasswordWindow {

    private JFrame frmEnterPassword;
    private JPasswordField passwordField;
    private JButton btnNewButton;
    private JButton btnClose;

    int passwordChances = 10;

    boolean isDisabled;
    private JLabel lblNewLabel;

    public PasswordWindow() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    initialize();
		    frmEnterPassword.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
	initialize();
    }
    
    public PasswordWindow getInstance() {
	return this;
    }

    private void initialize() {

	Logger.LOG.info("Loading Admin Window");

	this.frmEnterPassword = new JFrame();
	frmEnterPassword.setIconImage(Toolkit.getDefaultToolkit()
		.getImage(PasswordWindow.class.getResource("/com/pulsebeat02/main/resources/images/password.png")));
	frmEnterPassword.setTitle("Enter Password");
	frmEnterPassword.setBounds(100, 100, 265, 178);
	frmEnterPassword.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frmEnterPassword.getContentPane().setLayout(null);
	frmEnterPassword.setResizable(false);

	JLabel lblEnterPassword = new JLabel("Enter Password:");
	lblEnterPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblEnterPassword.setBounds(49, 11, 160, 38);
	lblEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
	frmEnterPassword.getContentPane().add(lblEnterPassword);

	passwordField = new JPasswordField();
	passwordField.setBounds(31, 68, 214, 20);
	frmEnterPassword.getContentPane().add(passwordField);

	btnNewButton = new JButton("Enter");
	btnNewButton.setBounds(10, 115, 89, 23);
	frmEnterPassword.getContentPane().add(btnNewButton);

	btnClose = new JButton("Close");
	btnClose.setBounds(156, 115, 89, 23);
	frmEnterPassword.getContentPane().add(btnClose);

	lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(
		new ImageIcon(PasswordWindow.class.getResource("/com/pulsebeat02/main/resources/images/password.png")));
	lblNewLabel.setBounds(21, 11, 46, 45);
	frmEnterPassword.getContentPane().add(lblNewLabel);
	btnClose.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		frmEnterPassword.setVisible(false);
		frmEnterPassword.dispose();
	    }
	});

	btnNewButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		Logger.LOG.info("Opened Pane");
		if (new String(passwordField.getPassword()).equals("j3fwVY") && !isDisabled) {
		    Logger.LOG.info("Password Correct");
		    frmEnterPassword.setVisible(false);
		    frmEnterPassword.dispose();
		    new OpenFiles();
		} else if (!(new String(passwordField.getPassword()).equals("j3fwVY")) && !isDisabled) {
		    passwordChances--;
		    Logger.LOG.info("One Less Password Chance");
		    PasswordConfig.start();
		    PasswordWrong window = new PasswordWrong();
		    window.frmPasswordIsWrong.setVisible(true);
		    if (passwordChances == 0) {
			isDisabled = true;
			Logger.LOG.info("Password Disabled");
		    }
		}
		if (isDisabled) {
		    TooManyPasswordChances.start();
		    Logger.LOG.info("Opening TooManyPasswordChances");
		}

	    }
	});

    }

}