package com.pulsebeat02.main.gui.windows.login;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Loading extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Loading dialog = new Loading();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Loading() {
		setUndecorated(true);
		setTitle("Loading");
		setBounds(100, 100, 256, 256);
		getContentPane().setLayout(new BorderLayout());
		{
			JLabel label = new JLabel("");
			getContentPane().add(label, BorderLayout.NORTH);
			label.setIcon(new ImageIcon(Loading.class.getResource("/com/pulsebeat02/main/resources/images/login/loading.gif")));
		}
	}

}
