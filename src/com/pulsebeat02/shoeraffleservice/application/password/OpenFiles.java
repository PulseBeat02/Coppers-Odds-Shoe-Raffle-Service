package com.pulsebeat02.shoeraffleservice.application.password;

import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class OpenFiles extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel contentPanel = new JPanel();

    public OpenFiles() {

	String cwd = System.getProperty("user.dir");

	this.setTitle("Open Files");
	this.setIconImage(Toolkit.getDefaultToolkit()
		.getImage(OpenFiles.class.getResource("/com/pulsebeat02/main/resources/images/login/lock.png")));
	this.setBounds(100, 100, 450, 250);
	this.getContentPane().setLayout(new BorderLayout());
	this.getContentPane().add(contentPanel, BorderLayout.CENTER);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.contentPanel.setLayout(null);
	this.contentPanel.add(new OpenFile("lastLogin", 10, 79));
	this.contentPanel.add(new OpenFile("latest.log", 249, 79));
	this.contentPanel.add(new OpenFile("totalpayment.txt", 10, 113));
	this.contentPanel.add(new OpenFile("allPayments", 249, 45));
	this.contentPanel.add(new OpenFile("loginUsers", 10, 11));
	this.contentPanel.add(new OpenFile("payment.json", 249, 11));
	this.contentPanel.add(new OpenFile("failedPayments", 10, 148));
	this.contentPanel.add(new OpenFile("shoes.json", 10, 45));

	JButton btnOpenUninstallexe = new JButton("Open uninstall.exe");
	btnOpenUninstallexe.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		try {
		    Runtime.getRuntime().exec(cwd + "/uninstall.exe", null, new File(cwd));
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
	    }
	});
	btnOpenUninstallexe.setBounds(249, 113, 175, 23);
	contentPanel.add(btnOpenUninstallexe);
	
	this.setVisible(true);

    }

    public class OpenFile extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpenFile(String file, int x, int y) {
	    
	    this.setText("Open: " + file);
	    this.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    try {
			openFile(new File(System.getProperty("user.dir") + "/failedPayments"));
		    } catch (IOException e1) {
			e1.printStackTrace();
		    }
		}
	    });
	    this.setBounds(x, y, 175, 23);
	}

	public void openFile(File file) throws IOException {

	    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file.getCanonicalPath());
	    }

	    Desktop.getDesktop().edit(file);

	}

    }

}
