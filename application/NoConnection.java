package com.pulsebeat02.shoeraffleservice.application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;
import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;

public class NoConnection extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JFrame frmNoConnection;

    public NoConnection() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    initialize();
		    frmNoConnection.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    private void initialize() {

	Logger.LOG.info("Opening No Connection");

	this.frmNoConnection = new JFrame();
	this.frmNoConnection.setTitle("No Connection");
	this.frmNoConnection.setIconImage(Toolkit.getDefaultToolkit()
		.getImage(NoConnection.class.getResource("/com/pulsebeat02/shoeraffleservice/resources/images/nowifi.png")));
	this.frmNoConnection.setBounds(100, 100, 500, 250);
	this.frmNoConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.frmNoConnection.getContentPane().setLayout(null);
	this.frmNoConnection.setResizable(false);

	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setIcon(
		new ImageIcon(NoConnection.class.getResource("/com/pulsebeat02/shoeraffleservice/resources/images/nowifi.png")));
	lblNewLabel.setBounds(25, 24, 32, 42);
	this.frmNoConnection.getContentPane().add(lblNewLabel);

	JLabel lblYouAreNot = new JLabel("You Are Not Connected to the Internet");
	lblYouAreNot.setFont(new Font("Tahoma", Font.BOLD, 20));
	lblYouAreNot.setBounds(67, 30, 430, 30);
	this.frmNoConnection.getContentPane().add(lblYouAreNot);

	JLabel lblNewLabel_1 = new JLabel(
		"<html> You won't be able to participate in the shoe raffle due to no <br> internet connection.<html>");
	lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	lblNewLabel_1.setBounds(35, 77, 430, 52);
	this.frmNoConnection.getContentPane().add(lblNewLabel_1);

	JSeparator separator = new JSeparator();
	separator.setBounds(35, 71, 419, 2);
	this.frmNoConnection.getContentPane().add(separator);

	JButton btnClose = new JButton("Close");
	btnClose.setFocusable(false);
	btnClose.setBounds(35, 160, 89, 23);
	btnClose.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		frmNoConnection.setVisible(false);
		frmNoConnection.dispose();
	    }
	});
	this.frmNoConnection.getContentPane().add(btnClose);

	JButton btnRetry = new JButton("Retry");
	btnRetry.setFocusable(false);
	btnRetry.setBounds(355, 160, 89, 23);
	btnRetry.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		frmNoConnection.setVisible(false);
		frmNoConnection.dispose();
		restartApplication();
	    }
	});
	frmNoConnection.getContentPane().add(btnRetry);

    }

    public static void restartApplication() {
	final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	File currentJar = null;
	try {
	    currentJar = new File(ShoeRaffleService.class.getProtectionDomain().getCodeSource().getLocation().toURI());
	} catch (URISyntaxException e) {
	    e.printStackTrace();
	}

	if (!currentJar.getName().endsWith(".jar"))
	    return;

	final ArrayList<String> command = new ArrayList<String>();
	command.add(javaBin);
	command.add("-jar");
	command.add(currentJar.getPath());
	command.add("null true");

	final ProcessBuilder builder = new ProcessBuilder(command);
	try {
	    builder.start();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.exit(0);
    }
}
