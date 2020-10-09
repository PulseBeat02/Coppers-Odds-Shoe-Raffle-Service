package com.pulsebeat02.shoeraffleservice.application.settings;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.pulsebeat02.shoeraffleservice.application.StartingWindow;
import com.pulsebeat02.shoeraffleservice.application.application.account.Account;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangeSettings {

    private JFrame frame;
    private JPanel main;

    public TabSelection[] tabs = new TabSelection[6];
    public int currentSelected = -1;

    public Account a;

    public ChangeSettings() throws IOException {
	initialize();
	frame.setVisible(true);
    }

    private void initialize() throws IOException {
	this.frame = new JFrame();
	this.frame.getContentPane().setBackground(Color.white);
	this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
		StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/account.png")));
	this.frame.setTitle("Settings");
	this.frame.setBounds(100, 100, 600, 435);
	this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	this.frame.setResizable(false);
	this.frame.getContentPane().setLayout(null);

	this.main = new AccountSettings(a);
	this.main.setBounds(170, 11, 415, 385);
	this.frame.getContentPane().add(main);

	JPanel panel = new JPanel();
	panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel.setBackground(Color.WHITE);
	panel.setBounds(10, 11, 150, 385);
	panel.setLayout(null);
	this.frame.getContentPane().add(panel);

	TabSelection account = new TabSelection("Account", Toolkit.getDefaultToolkit().getImage(
		StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/account.png")));
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
	this.tabs[0] = account;

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
	this.tabs[1] = info;

	TabSelection location = new TabSelection("Location", Toolkit.getDefaultToolkit().getImage(
		StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/location.png")));
	location.setBounds(5, 87, 140, 40);
	panel.add(location);
	this.tabs[2] = location;

	TabSelection notifications = new TabSelection("Notifications", Toolkit.getDefaultToolkit().getImage(
		StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/bell.png")));
	notifications.setBounds(5, 46, 140, 40);
	panel.add(notifications);
	this.tabs[3] = notifications;

	TabSelection uninstall = new TabSelection("Uninstall", Toolkit.getDefaultToolkit().getImage(
		StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/uninstall.png")));
	uninstall.setBounds(5, 338, 140, 40);
	panel.add(uninstall);
	this.tabs[4] = uninstall;

	TabSelection support = new TabSelection("Support", Toolkit.getDefaultToolkit().getImage(
		StartingWindow.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/support.png")));
	support.setBounds(5, 169, 140, 40);
	panel.add(support);
	this.tabs[5] = support;

    }

}
