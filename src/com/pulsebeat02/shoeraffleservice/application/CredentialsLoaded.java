package com.pulsebeat02.shoeraffleservice.application;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CredentialsLoaded extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final JPanel contentPanel = new JPanel();

    public CredentialsLoaded() {
	this.setTitle("Login Credentials");
	this.setIconImage(Toolkit.getDefaultToolkit().getImage(
		CredentialsLoaded.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/account.png")));
	this.setBounds(100, 100, 450, 248);
	this.getContentPane().setLayout(new BorderLayout());
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.getContentPane().add(contentPanel, BorderLayout.CENTER);
	this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.contentPanel.setLayout(null);

	JLabel lblNewLabel = new JLabel("Login Credentials Saved");
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
	lblNewLabel.setBounds(10, 11, 414, 36);
	this.contentPanel.add(lblNewLabel);

	JSeparator separator = new JSeparator();
	separator.setBounds(10, 58, 414, 2);
	this.contentPanel.add(separator);

	JLabel lblNewLabel_1 = new JLabel(
		"<html> Your login credentials have been saved. Please make sure that when you relogin, you use the correct login details. Press any of the buttons to close this window.");
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
	lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
	lblNewLabel_1.setBounds(10, 71, 414, 72);
	this.contentPanel.add(lblNewLabel_1);

	JPanel buttonPane = new JPanel();
	buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	buttonPane.setBounds(0, 173, 434, 36);
	buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	this.contentPanel.add(buttonPane);
	
	JButton okButton = new JButton("OK");
	okButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		setVisible(false);
		dispose();
	    }
	});
	okButton.setActionCommand("OK");
	buttonPane.add(okButton);
	this.getRootPane().setDefaultButton(okButton);

	JButton cancelButton = new JButton("Close");
	cancelButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		setVisible(false);
		dispose();
	    }
	});
	cancelButton.setActionCommand("Cancel");
	buttonPane.add(cancelButton);
	
	this.setVisible(true);

    }
}
