package com.pulsebeat02.shoeraffleservice.application.register;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MissingFields extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final JPanel contentPanel = new JPanel();

    public MissingFields() {

	this.setBounds(100, 100, 300, 150);
	this.getContentPane().setLayout(new BorderLayout());
	this.getContentPane().add(contentPanel, BorderLayout.CENTER);
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.contentPanel.setLayout(null);
	this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

	JLabel lblNewLabel = new JLabel(
		"<html> Please fill in the missing fields. If any of the fields are blank, you won't be able to create your new Coppers Odds Account.");
	lblNewLabel.setBounds(17, -12, 277, 102);
	this.contentPanel.add(lblNewLabel);

	JPanel buttonPane = new JPanel();
	buttonPane.setBounds(0, 89, 300, 39);
	buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	this.contentPanel.add(buttonPane);
	
	JButton okButton = new JButton("OK");
	okButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
		setVisible(false);
		dispose();
	    }
	});
	okButton.setActionCommand("OK");
	buttonPane.add(okButton);
	getRootPane().setDefaultButton(okButton);

	JButton cancelButton = new JButton("Cancel");
	cancelButton.setActionCommand("Cancel");
	buttonPane.add(cancelButton);
	cancelButton.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
		setVisible(false);
		dispose();
	    }
	});

	this.setVisible(true);
    }

}
