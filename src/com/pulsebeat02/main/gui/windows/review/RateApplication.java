package com.pulsebeat02.main.gui.windows.review;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class RateApplication extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private final JPanel contentPanel = new JPanel();

    public RateApplication() {
	this.setBounds(100, 100, 450, 300);
	this.getContentPane().setLayout(new BorderLayout());
	this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	this.getContentPane().add(contentPanel, BorderLayout.CENTER);
	this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.contentPanel.setLayout(null);

	JLabel lblNewLabel = new JLabel("Please Rate Our Application");
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setBounds(6, 6, 438, 55);
	this.contentPanel.add(lblNewLabel);

	JLabel label = new JLabel("");
	label.setIcon(new ImageIcon(ScaledImage(
		new ImageIcon(RateApplication.class.getResource("/com/pulsebeat02/main/resources/images/star.png"))
			.getImage(),
		label.getWidth(), label.getHeight())));
	label.setBounds(16, 73, 168, 160);
	this.contentPanel.add(label);

	JPanel buttonPane = new JPanel();
	buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	getContentPane().add(buttonPane, BorderLayout.SOUTH);

	JButton okButton = new JButton("OK");
	okButton.setActionCommand("OK");
	buttonPane.add(okButton);
	getRootPane().setDefaultButton(okButton);

	JButton cancelButton = new JButton("Cancel");
	cancelButton.setActionCommand("Cancel");
	buttonPane.add(cancelButton);

	this.setVisible(true);
    }

    public Image ScaledImage(Image img, int w, int h) {
	BufferedImage resizedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

	Graphics2D g2 = resizedImage.createGraphics();
	g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g2.drawImage(img, 0, 0, w, h, null);
	g2.dispose();
	return resizedImage;
    }

}
