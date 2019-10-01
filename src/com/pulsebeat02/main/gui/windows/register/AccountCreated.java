package com.pulsebeat02.main.gui.windows.register;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class AccountCreated extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	public static int maxThreads;
	public static int userThreadSize;
	

	/**
	 * Launch the application.
	 */
	public static void start() {
		try {
			AccountCreated dialog = new AccountCreated();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AccountCreated() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(AccountCreated.class.getResource("/com/pulsebeat02/main/resources/images/login/user.png")));
		setTitle("Account Successfully Created");
		setBounds(100, 100, 487, 184);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Account Created");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 451, 36);
		contentPanel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 451, 2);
		contentPanel.add(separator);

		JLabel lblNewLabel_1 = new JLabel(
				"<html> Your account has been successfully created. Please re-login to enter into the shoe raffle. Thank you for signing up!\r\n");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(30, 52, 414, 65);
		contentPanel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton Close = new JButton("Close");
				Close.setBackground(Color.WHITE);
				Close.setFocusable(false);
				Close.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {

						Logger.LOG.info("New Account Created, Closing Window");
						setVisible(false);
						dispose();
					}
				});
				Close.setActionCommand("OK");
				buttonPane.add(Close);
				getRootPane().setDefaultButton(Close);
			}
			{
				JButton Login = new JButton("Login");
				Login.setFocusable(false);
				Login.setBackground(Color.WHITE);
				Login.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Logger.LOG.info("New Account Created, Closing Window");
						Logger.LOG.info("Opening Login Panel");
						Component component = (Component) e.getSource();
						JDialog dialog = (JDialog) SwingUtilities.getRoot(component);
						dialog.setVisible(false);
						dialog.dispose();

						restartApplication();
					}
				});
				Login.setActionCommand("Cancel");
				buttonPane.add(Login);
			}
		}
	}

	public static void restartApplication() {
		LoginPanel.start(true);
	}
}
