package com.pulsebeat02.main.gui.windows.password;

import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.pulsebeat02.main.util.logging.Logger;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class OpenFiles extends JDialog {

	String cwd = System.getProperty("user.dir");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void start() {
		Logger.LOG.warn("Array has null element");
		try {
			OpenFiles dialog = new OpenFiles();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OpenFiles() {
		setTitle("Open Files");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(OpenFiles.class.getResource("/com/pulsebeat02/main/resources/images/login/lock.png")));
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnOpenLastlogin = new JButton("Open lastLogin");
		btnOpenLastlogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				File file = new File(cwd + "/lastLogin");

				try {

					if (System.getProperty("os.name").toLowerCase().contains("windows")) {

						String cmd1 = "rundll32 url.dll,FileProtocolHandler " + file.getCanonicalPath();
						Runtime.getRuntime().exec(cmd1);

					}

					else {

						Desktop.getDesktop().edit(file);

					}

					java.awt.Desktop.getDesktop().edit(file);

				} catch (IOException e) {

					e.printStackTrace();

				}
			}
		});
		btnOpenLastlogin.setBounds(10, 79, 175, 23);
		contentPanel.add(btnOpenLastlogin);

		JButton btnOpenLatestlog = new JButton("Open latest.log");
		btnOpenLatestlog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = new File(cwd + "/latest.log");

				try {

					if (System.getProperty("os.name").toLowerCase().contains("windows")) {

						String cmd1 = "rundll32 url.dll,FileProtocolHandler " + file.getCanonicalPath();
						Runtime.getRuntime().exec(cmd1);

					}

					else {

						Desktop.getDesktop().edit(file);

					}

					java.awt.Desktop.getDesktop().edit(file);

				} catch (IOException e1) {

					e1.printStackTrace();

				}
			}
		});
		btnOpenLatestlog.setBounds(249, 79, 175, 23);
		contentPanel.add(btnOpenLatestlog);

		JButton btnOpenShoesjson = new JButton("Open shoes.json");
		btnOpenShoesjson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = new File(cwd + "/shoes.json");

				try {

					if (System.getProperty("os.name").toLowerCase().contains("windows")) {

						String cmd1 = "rundll32 url.dll,FileProtocolHandler " + file.getCanonicalPath();
						Runtime.getRuntime().exec(cmd1);

					}

					else {

						Desktop.getDesktop().edit(file);

					}

					java.awt.Desktop.getDesktop().edit(file);

				} catch (IOException e2) {

					e2.printStackTrace();

				}
			}
		});
		btnOpenShoesjson.setBounds(10, 45, 175, 23);
		contentPanel.add(btnOpenShoesjson);

		JButton btnOpenTotalpaymenttxt = new JButton("Open totalpayment.txt");
		btnOpenTotalpaymenttxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = new File(cwd + "/totalpayment.txt");

				try {

					if (System.getProperty("os.name").toLowerCase().contains("windows")) {

						String cmd1 = "rundll32 url.dll,FileProtocolHandler " + file.getCanonicalPath();
						Runtime.getRuntime().exec(cmd1);

					}

					else {

						Desktop.getDesktop().edit(file);

					}

					java.awt.Desktop.getDesktop().edit(file);

				} catch (IOException e3) {

					e3.printStackTrace();

				}
			}
		});
		btnOpenTotalpaymenttxt.setBounds(10, 113, 175, 23);
		contentPanel.add(btnOpenTotalpaymenttxt);

		JButton btnOpenAllpayments = new JButton("Open allPayments");
		btnOpenAllpayments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = new File(cwd + "/allPayments");

				try {

					if (System.getProperty("os.name").toLowerCase().contains("windows")) {

						String cmd1 = "rundll32 url.dll,FileProtocolHandler " + file.getCanonicalPath();
						Runtime.getRuntime().exec(cmd1);

					}

					else {

						Desktop.getDesktop().edit(file);

					}

					java.awt.Desktop.getDesktop().edit(file);

				} catch (IOException e4) {

					e4.printStackTrace();

				}
			}
		});
		btnOpenAllpayments.setBounds(249, 45, 175, 23);
		contentPanel.add(btnOpenAllpayments);

		JButton btnOpenLoginusers = new JButton("Open loginUsers");
		btnOpenLoginusers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = new File(cwd + "/loginUsers");

				try {

					if (System.getProperty("os.name").toLowerCase().contains("windows")) {

						String cmd1 = "rundll32 url.dll,FileProtocolHandler " + file.getCanonicalPath();
						Runtime.getRuntime().exec(cmd1);

					}

					else {

						Desktop.getDesktop().edit(file);

					}

					java.awt.Desktop.getDesktop().edit(file);

				} catch (IOException e5) {

					e5.printStackTrace();

				}
			}
		});
		btnOpenLoginusers.setBounds(10, 11, 175, 23);
		contentPanel.add(btnOpenLoginusers);

		JButton btnOpenPaymentjson = new JButton("Open payment.json");
		btnOpenPaymentjson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = new File(cwd + "/payment.json");

				try {

					if (System.getProperty("os.name").toLowerCase().contains("windows")) {

						String cmd1 = "rundll32 url.dll,FileProtocolHandler " + file.getCanonicalPath();
						Runtime.getRuntime().exec(cmd1);

					}

					else {

						Desktop.getDesktop().edit(file);

					}

					java.awt.Desktop.getDesktop().edit(file);

				} catch (IOException e6) {

					e6.printStackTrace();

				}
			}
		});
		btnOpenPaymentjson.setBounds(249, 11, 175, 23);
		contentPanel.add(btnOpenPaymentjson);

		JButton btnOpenUninstallexe = new JButton("Open uninstall.exe");
		btnOpenUninstallexe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Runtime.getRuntime().exec(cwd + "/uninstall.exe", null, new File(cwd));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOpenUninstallexe.setBounds(249, 113, 175, 23);
		contentPanel.add(btnOpenUninstallexe);

		JButton btnOpenFailedpayments = new JButton("Open failedPayments");
		btnOpenFailedpayments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file = new File(cwd + "/failedPayments");

				try {

					if (System.getProperty("os.name").toLowerCase().contains("windows")) {

						String cmd1 = "rundll32 url.dll,FileProtocolHandler " + file.getCanonicalPath();
						Runtime.getRuntime().exec(cmd1);

					}

					else {

						Desktop.getDesktop().edit(file);

					}

					java.awt.Desktop.getDesktop().edit(file);

				} catch (IOException e6) {

					e6.printStackTrace();

				}
			}
		});
		btnOpenFailedpayments.setBounds(10, 148, 175, 23);
		contentPanel.add(btnOpenFailedpayments);
	}
}
