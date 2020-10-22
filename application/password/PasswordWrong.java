package com.pulsebeat02.shoeraffleservice.application.password;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;
import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

import javax.swing.JButton;

public class PasswordWrong implements WindowListener {

    JFrame frmPasswordIsWrong;

    public Timer timer;

    public int interval = (int) PasswordConfig.prop.get(0);

    public PasswordWrong() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    initialize();
		    frmPasswordIsWrong.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    private void initialize() {

	this.timer = new Timer();
	this.timer.scheduleAtFixedRate(new TimerTask() {

	    @Override
	    public void run() {

		setInterval();

		if (interval == 0) {

		    ShoeRaffleService.service.getInstanceManager().PASSWORD_WINDOW.isDisabled = false;
		    Logger.LOG.info("Password disabling off");

		}

	    }

	}, 1000, 600);

	frmPasswordIsWrong = new JFrame();
	frmPasswordIsWrong.setIconImage(Toolkit.getDefaultToolkit().getImage(
		PasswordWrong.class.getResource("/com/pulsebeat02/shoeraffleservice/resources/images/passwordWrongIcon.png")));
	frmPasswordIsWrong.setTitle("Password is Wrong");
	frmPasswordIsWrong.setBounds(100, 100, 300, 200);
	frmPasswordIsWrong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frmPasswordIsWrong.getContentPane().setLayout(null);
	frmPasswordIsWrong.setResizable(false);

	JLabel lblNewLabel = new JLabel("");
	lblNewLabel.setBounds(20, 15, 32, 50);
	lblNewLabel.setIcon(new ImageIcon(
		PasswordWrong.class.getResource("/com/pulsebeat02/shoeraffleservice/resources/images/passwordWrongIcon.png")));
	frmPasswordIsWrong.getContentPane().add(lblNewLabel);

	JLabel lblNewLabel_1 = new JLabel("Password Incorrect");
	lblNewLabel_1.setBounds(62, 22, 301, 32);
	lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
	frmPasswordIsWrong.getContentPane().add(lblNewLabel_1);

	JLabel lblNewLabel_2 = new JLabel("The password you entered is wrong.");
	lblNewLabel_2.setBounds(30, 79, 212, 14);
	frmPasswordIsWrong.getContentPane().add(lblNewLabel_2);

	JSeparator separator = new JSeparator();
	separator.setBounds(30, 65, 244, 2);
	frmPasswordIsWrong.getContentPane().add(separator);

	JLabel lblPleaseRetypeThe = new JLabel("Please retype the password again.");
	lblPleaseRetypeThe.setBounds(30, 96, 212, 14);
	frmPasswordIsWrong.getContentPane().add(lblPleaseRetypeThe);

	JButton btnNewButton = new JButton("Close");
	btnNewButton.setBounds(10, 133, 121, 23);
	frmPasswordIsWrong.getContentPane().add(btnNewButton);
	btnNewButton.addActionListener(new ActionListener() {

	    @Override

	    public void actionPerformed(ActionEvent e) {

		Logger.LOG.info("Closing Window");

		frmPasswordIsWrong.setVisible(false);
		frmPasswordIsWrong.dispose();

	    }

	});

	JButton btnMainMenu = new JButton("Main Menu");
	btnMainMenu.setBounds(153, 133, 121, 23);
	frmPasswordIsWrong.getContentPane().add(btnMainMenu);
	btnMainMenu.addActionListener(new ActionListener() {

	    @Override

	    public void actionPerformed(ActionEvent e) {

		frmPasswordIsWrong.setVisible(false);
		frmPasswordIsWrong.dispose();

		Logger.LOG.info("Main Menu");

	    }

	});
    }

    private final int setInterval() {

	if (interval == 1) {
	    timer.cancel();
	}

	return --interval;

    }

    @Override
    public void windowActivated(WindowEvent arg0) {
    }

    @Override
    public void windowClosed(WindowEvent arg0) {

	PasswordConfig.prop.replace(timer, interval);

    }

    @Override
    public void windowClosing(WindowEvent arg0) {

	PasswordConfig.prop.replace(timer, interval);

    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
    }
}
