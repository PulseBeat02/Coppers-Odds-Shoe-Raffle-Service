package com.pulsebeat02.shoeraffleservice.application.resetPassword;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class EnterCode extends JDialog {

    static int code;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final JPanel contentPanel = new JPanel();
    private JTextField textField;

    public EnterCode() {

	this.setIconImage(Toolkit.getDefaultToolkit()
		.getImage(EnterCode.class.getResource("/com/pulsebeat02/main/resources/images/password.png")));
	this.setTitle("Enter Code");
	this.setBounds(100, 100, 450, 236);
	this.getContentPane().setLayout(new BorderLayout());
	this.getContentPane().add(contentPanel, BorderLayout.CENTER);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.contentPanel.setLayout(null);
	this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	JLabel lblNewLabel = new JLabel(
		"<html> Please enter your numbered code that was emailed to you. If you typed the code correct, you will be able to change your password.");
	lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
	lblNewLabel.setBounds(10, 11, 414, 63);
	this.contentPanel.add(lblNewLabel);

	JLabel lblNewLabel_1 = new JLabel("Enter Code Here:");
	lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
	lblNewLabel_1.setBounds(20, 101, 145, 42);
	this.contentPanel.add(lblNewLabel_1);

	JLabel invalid = new JLabel("<html> Please Retype the Code and Try Again");
	invalid.setVisible(false);
	invalid.setForeground(Color.RED);
	invalid.setBounds(10, 155, 228, 36);
	this.contentPanel.add(invalid);

	this.textField = new JTextField();
	this.textField.setBounds(165, 114, 259, 20);
	this.contentPanel.add(textField);
	this.textField.setColumns(10);

	JPanel buttonPane = new JPanel();
	buttonPane.setBounds(250, 154, 174, 33);
	this.contentPanel.add(buttonPane);
	buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

	JButton okButton = new JButton("OK");
	okButton.setFocusable(false);
	okButton.setBackground(Color.WHITE);
	okButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		if (textField.getText().equals(String.valueOf(code))) {

		    ChangePassword.main(null);

		    Logger.LOG.info("Closing");
		    setVisible(false);
		    dispose();

		} else {

		    Logger.LOG.info("Code Invalid");
		    invalid.setVisible(true);

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
