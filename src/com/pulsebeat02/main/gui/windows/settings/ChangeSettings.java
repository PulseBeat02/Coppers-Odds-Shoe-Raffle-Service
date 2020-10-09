package com.pulsebeat02.main.gui.windows.settings;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.pulsebeat02.main.gui.windows.StartingWindow;
import com.pulsebeat02.main.gui.windows.account.Account;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChangeSettings {

	private JFrame frame;
	private JPanel main;
	
	public static TabSelection[] tabs = new TabSelection[6];
	public static int currentSelected = -1;
	
	public static Account a;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					
					Account ac = new Account("PulseBeat_02", "Brandon", "Li", "qwerty", "", null, null, 0, 0, null, false, null, null, 0);
					a = ac;
					
					ChangeSettings window = new ChangeSettings();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public ChangeSettings() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.white);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/account.png")));
		frame.setTitle("Settings");
		frame.setBounds(100, 100, 600, 435);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		main = new AccountSettings(a);
		main.setBounds(170, 11, 415, 385);
		frame.getContentPane().add(main);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 150, 385);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		TabSelection account = new TabSelection("Account", Toolkit.getDefaultToolkit().getImage(StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/account.png")));
		account.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				main = new AccountSettings(a);
				main.setBounds(170, 11, 415, 385);
				main.setVisible(true);
				main.repaint();
				frame.getContentPane().add(main);
				
			}
		});
		account.setBounds(5, 5, 140, 40);
		panel.add(account);
		tabs[0] = account;
		
		TabSelection info = new TabSelection("Computer Info", Toolkit.getDefaultToolkit().getImage(StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/wifi-connection-good.png")));
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setVisible(false);
				ComputerPanel computerPanel = null;
				try {
					computerPanel = new ComputerPanel();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				computerPanel.setBounds(170, 11, 415, 385);
				computerPanel.setVisible(true);
				frame.getContentPane().add(computerPanel);
				
			}
		});
		info.setBounds(5, 128, 140, 40);
		panel.add(info);
		tabs[1] = info;
		
		TabSelection location = new TabSelection("Location", Toolkit.getDefaultToolkit().getImage(StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/location.png")));
		location.setBounds(5, 87, 140, 40);
		panel.add(location);
		tabs[2] = location;
		
		TabSelection notifications = new TabSelection("Notifications", Toolkit.getDefaultToolkit().getImage(StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/bell.png")));
		notifications.setBounds(5, 46, 140, 40);
		panel.add(notifications);
		tabs[3] = notifications;
		
		TabSelection uninstall = new TabSelection("Uninstall", Toolkit.getDefaultToolkit().getImage(StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/uninstall.png")));
		uninstall.setBounds(5, 338, 140, 40);
		panel.add(uninstall);
		tabs[4] = uninstall;
		
		TabSelection support = new TabSelection("Support", Toolkit.getDefaultToolkit().getImage(StartingWindow.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/support.png")));
		support.setBounds(5, 169, 140, 40);
		panel.add(support);
		tabs[5] = support;
		
	}

}
