package com.pulsebeat02.main.gui.windows.resetPassword;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pulsebeat02.main.gui.windows.login.LoginPanel;
import com.pulsebeat02.main.util.logging.Logger;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class PasswordChanged extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PasswordChanged dialog = new PasswordChanged();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PasswordChanged() {
		setTitle("Password Changed");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PasswordChanged.class.getResource("/com/pulsebeat02/main/resources/images/password.png")));
		setBounds(100, 100, 450, 173);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("<html> Your password has been changed. Please remember to use your new login credentials after you changed the old one.  This password change may take up to 1 hour to work!");
			lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			lblNewLabel.setBounds(10, 11, 414, 79);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(213, 90, 211, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("Login");
				okButton.setFocusable(false);
				okButton.setBackground(Color.WHITE);
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						LoginPanel.start(true);
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
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
			}
		}
	}

}
