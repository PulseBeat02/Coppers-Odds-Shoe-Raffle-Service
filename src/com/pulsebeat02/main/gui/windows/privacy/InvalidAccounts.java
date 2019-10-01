package com.pulsebeat02.main.gui.windows.privacy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import com.pulsebeat02.main.gui.windows.session.ManageSessions;
import com.pulsebeat02.main.gui.windows.session.Session;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InvalidAccounts extends JDialog {

	public static Session[] s;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void start() {
		try {
			InvalidAccounts dialog = new InvalidAccounts();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InvalidAccounts() {
		
		Set<Session> set = new HashSet<>(Arrays.asList(s));
		ManageSessions.allSessions.values().removeAll(set);

		for (int i = 0; i < s.length; i++) {
			
			setBounds(100, 100, 450, 300);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);

			JLabel lblNewLabel = new JLabel(
					"<html> We have found another account has logged in with a different location. If you don't recognize this login, please change your password immediantely. For your safety, all logins with the account are logged out.");
			lblNewLabel.setBounds(18, 6, 415, 84);
			contentPanel.add(lblNewLabel);

			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBounds(59, 101, 330, 115);
			contentPanel.add(panel);
			panel.setLayout(null);

			JLabel lblTime = new JLabel("Time: " + s[i].time);
			lblTime.setBounds(6, 6, 318, 16);
			panel.add(lblTime);

			JLabel lblLocation = new JLabel("Location: " + s[i].locationFinal);
			lblLocation.setBounds(6, 27, 318, 16);
			panel.add(lblLocation);

			JLabel lblOperatingSystem = new JLabel("Operating System: " + s[i].os);
			lblOperatingSystem.setBounds(6, 48, 318, 16);
			panel.add(lblOperatingSystem);

			JLabel lblVersion = new JLabel("Version: " + s[i].osVersion);
			lblVersion.setBounds(6, 69, 318, 16);
			panel.add(lblVersion);

			JLabel lblArchitecture = new JLabel("Architecture: " + s[i].osArchitecture);
			lblArchitecture.setBounds(6, 90, 318, 16);
			panel.add(lblArchitecture);
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							System.exit(0);
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
			}
		}

	}
}
