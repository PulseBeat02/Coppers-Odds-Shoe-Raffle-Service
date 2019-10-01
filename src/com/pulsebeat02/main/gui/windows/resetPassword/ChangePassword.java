package com.pulsebeat02.main.gui.windows.resetPassword;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pulsebeat02.main.gui.windows.Refresh;
import com.pulsebeat02.main.gui.windows.account.Account;
import com.pulsebeat02.main.util.logging.Logger;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChangePassword dialog = new ChangePassword(account);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChangePassword(Account account) {
		Logger.LOG.info("Opening Change Password");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ChangePassword.class.getResource("/com/pulsebeat02/main/resources/images/password.png")));
		setTitle("Change Password");
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewPassword = new JLabel("New Password:");
			lblNewPassword.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
			lblNewPassword.setBounds(24, 11, 140, 48);
			contentPanel.add(lblNewPassword);
		}
		{
			JLabel lblRetypeNewPassword = new JLabel("Retype New Password:");
			lblRetypeNewPassword.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
			lblRetypeNewPassword.setBounds(24, 70, 206, 48);
			contentPanel.add(lblRetypeNewPassword);
		}

		old = new JPasswordField();
		old.setBounds(234, 27, 190, 20);
		contentPanel.add(old);

		RetypeNewPass = new JPasswordField();
		RetypeNewPass.setBounds(234, 86, 190, 20);
		contentPanel.add(RetypeNewPass);

		JLabel invalid = new JLabel("Please check the text fields");
		invalid.setVisible(false);
		invalid.setForeground(Color.RED);
		invalid.setBounds(24, 129, 216, 14);
		contentPanel.add(invalid);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 154, 424, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("Save");
				okButton.setFocusable(false);
				okButton.setBackground(Color.WHITE);
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (new String(old.getPassword()).equals(new String(RetypeNewPass.getPassword()))) {

								account.password = new String(RetypeNewPass.getPassword());
								
								Refresh.start("account");

								Logger.LOG.info("Password Changed");
								
								setVisible(false);
								dispose();
								
								Logger.LOG.info("Closing Window");

							}
							
							else {
								
								invalid.setVisible(true);
								
								Logger.LOG.info("Passwords Don't Match");
								
							}

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
