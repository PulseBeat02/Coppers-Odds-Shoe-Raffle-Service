package com.pulsebeat02.main.gui.application.resetPassword;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pulsebeat02.main.gui.application.Refresh;
import com.pulsebeat02.main.gui.application.account.Account;
import com.pulsebeat02.main.util.logging.Logger;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JPasswordField;
import java.awt.Color;

public class ChangePassword extends JDialog {

    static Account account;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final JPanel contentPanel = new JPanel();

    private JPasswordField old;
    private JPasswordField RetypeNewPass;

    public ChangePassword(Account account) {
	this.setIconImage(Toolkit.getDefaultToolkit()
		.getImage(ChangePassword.class.getResource("/com/pulsebeat02/main/resources/images/password.png")));
	this.setTitle("Change Password");
	this.setBounds(100, 100, 450, 230);
	this.getContentPane().setLayout(new BorderLayout());
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.getContentPane().add(contentPanel, BorderLayout.CENTER);
	this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.contentPanel.setLayout(null);

	JLabel lblNewPassword = new JLabel("New Password:");
	lblNewPassword.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
	lblNewPassword.setBounds(24, 11, 140, 48);
	this.contentPanel.add(lblNewPassword);

	JLabel lblRetypeNewPassword = new JLabel("Retype New Password:");
	lblRetypeNewPassword.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
	lblRetypeNewPassword.setBounds(24, 70, 206, 48);
	this.contentPanel.add(lblRetypeNewPassword);

	this.old = new JPasswordField();
	this.old.setBounds(234, 27, 190, 20);
	this.contentPanel.add(old);

	this.RetypeNewPass = new JPasswordField();
	this.RetypeNewPass.setBounds(234, 86, 190, 20);
	this.contentPanel.add(RetypeNewPass);

	JLabel invalid = new JLabel("Please check the text fields");
	invalid.setVisible(false);
	invalid.setForeground(Color.RED);
	invalid.setBounds(24, 129, 216, 14);
	this.contentPanel.add(invalid);

	JPanel buttonPane = new JPanel();
	buttonPane.setBounds(0, 154, 424, 33);
	this.contentPanel.add(buttonPane);
	buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

	JButton okButton = new JButton("Save");
	okButton.setFocusable(false);
	okButton.setBackground(Color.WHITE);
	okButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (new String(old.getPassword()).equals(new String(RetypeNewPass.getPassword()))) {
		    account.password = new String(RetypeNewPass.getPassword());
		    try {
			Refresh.start("account");
		    } catch (IOException e1) {
			e1.printStackTrace();
		    }

		    Logger.LOG.info("Password Changed");
		    setVisible(false);
		    dispose();
		} else {
		    invalid.setVisible(true);
		    Logger.LOG.info("Passwords Don't Match");
		}

	    }
	});
	okButton.setActionCommand("OK");
	buttonPane.add(okButton);
	getRootPane().setDefaultButton(okButton);

	JButton cancelButton = new JButton("Close");
	cancelButton.setFocusable(false);
	cancelButton.setBackground(Color.WHITE);
	cancelButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		Logger.LOG.info("Closing");
		setVisible(false);
		dispose();
	    }
	});
	cancelButton.setActionCommand("Cancel");
	buttonPane.add(cancelButton);

	this.setVisible(true);

    }
}
