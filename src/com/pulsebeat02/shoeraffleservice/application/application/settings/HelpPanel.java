package com.pulsebeat02.main.gui.application.settings;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import com.pulsebeat02.main.util.text.MultiLineLabelUI;

public class HelpPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public HelpPanel() {

	this.setSize(415, 385);
	this.setPreferredSize(new Dimension(415, 385));
	this.setLayout(null);

	JSeparator separator = new JSeparator();
	separator.setBounds(10, 11, 395, 10);
	this.add(separator);

	JSeparator separator_1 = new JSeparator();
	separator_1.setBounds(10, 375, 395, 10);
	this.add(separator_1);

	JLabel lblNewLabel = new JLabel("How To Use the Computer Panel");
	lblNewLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setBounds(10, 24, 395, 47);
	this.add(lblNewLabel);

	JLabel lblNewLabel_1 = new JLabel("Computer Tabs");
	lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
	lblNewLabel_1.setBounds(10, 82, 384, 33);
	this.add(lblNewLabel_1);

	JLabel lblNewLabel_2 = new JLabel(
		"You can use the tabs, processor, memory, or disk to look at how well your computer is performing in general. Although Coppers Odds is a small application, if you computer is weak, old, or under-performing, it may experience serious lag issue while navigating through different tabs in the app. Consider looking at this page if your computer is lagging, and shut off any other applications to free up resources.");
	lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	lblNewLabel_2.setBounds(10, 110, 395, 102);
	lblNewLabel_2.setUI(MultiLineLabelUI.labelUI);
	this.add(lblNewLabel_2);

	JLabel lblNeedHelp = new JLabel("Need Help?");
	lblNeedHelp.setFont(new Font("Segoe UI", Font.BOLD, 12));
	lblNeedHelp.setBounds(10, 223, 384, 39);
	this.add(lblNeedHelp);

	JLabel lblConsiderContactingUs = new JLabel("Consider contacting us.");
	lblConsiderContactingUs.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	lblConsiderContactingUs.setBounds(10, 246, 395, 33);
	this.add(lblConsiderContactingUs);

    }
}
