package com.pulsebeat02.main.gui.application.builder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomJLabel extends JLabel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CustomJLabel(int x, int y, int width, int height, String text, JPanel panel, Object HorizAllignment,
	    Object foreground, Object font, ImageIcon icon) {

	this.setText(text);
	this.setBounds(x, y, width, height);

	if (foreground instanceof Color && foreground != null) {
	    this.setForeground((Color) foreground);
	}

	if (HorizAllignment != null) {
	    this.setHorizontalAlignment((int) HorizAllignment);
	}

	if (font instanceof Font && font != null) {
	    this.setFont((Font) font);
	}

	if (icon != null) {
	    this.setIcon(icon);
	}

	panel.add(this);

    }

}
