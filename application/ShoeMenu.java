package com.pulsebeat02.shoeraffleservice.application;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;
import com.pulsebeat02.shoeraffleservice.application.account.Account;
import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

public class ShoeMenu {

    private JFrame frmShoeName;

    int currentShoe;

    public ShoeMenu(Account a, Shoe s) {

	Image[] imagesFinal = new Image[s.imageURLs.length];

	for (int i = 0; i < imagesFinal.length; i++) {

	    try {
		Image image = ImageIO.read(new URL(s.imageURLs[i]));
		imagesFinal[i] = image;
	    } catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}

	initialize(a, s, s.shoeName, imagesFinal, s.colors, s.review, s.sizes, s.description, s.style, s.brand, s.typeOfShoes,
		s.isSold);

	this.frmShoeName.setVisible(true);
    }

    private void initialize(Account a, Shoe s, String shoeName, Image[] images, String[] colors, double review, String[] sizes,
	    String description, String style, String brand, String typeofshoes, boolean isSold) {

	Logger.LOG.info("Opening Shoe GUI");

	ShoeRaffleService.service.getInstanceManager().BUYING_TICKETS.currentShoe = currentShoe;

	this.frmShoeName = new JFrame();
	this.frmShoeName.getContentPane().setBackground(new Color(220, 220, 220));
	this.frmShoeName.setIconImage(Toolkit.getDefaultToolkit()
		.getImage(ShoeMenu.class.getResource("/com/pulsebeat02/shoeraffleservice/resources/images/mainmenu/shoe.png")));
	this.frmShoeName.setTitle(shoeName);
	this.frmShoeName.setBounds(100, 100, 800, 600);
	this.frmShoeName.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.frmShoeName.getContentPane().setLayout(null);
	this.frmShoeName.setResizable(false);

	JLabel lblNewLabel = new JLabel("Shoe");
	lblNewLabel.setForeground(Color.WHITE);
	lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setBounds(448, 0, 69, 77);
	this.frmShoeName.getContentPane().add(lblNewLabel);

	JPanel panel = new JPanel();
	panel.setBounds(10, 11, 200, 200);
	this.frmShoeName.getContentPane().add(panel);

	try {

	    Image img1Pre = images[0].getScaledInstance(200, 200, Image.SCALE_DEFAULT);
	    ImageIcon img1 = new ImageIcon(img1Pre);
	    JLabel lblImage = new JLabel("");
	    lblImage.setIcon(img1);
	    panel.add(lblImage);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JPanel panel_1 = new JPanel();
	panel_1.setBounds(220, 11, 200, 200);
	this.frmShoeName.getContentPane().add(panel_1);

	try {

	    Image img2Pre = images[1].getScaledInstance(200, 200, Image.SCALE_DEFAULT);
	    ImageIcon img2 = new ImageIcon(img2Pre);
	    JLabel lblImage_1 = new JLabel("");
	    lblImage_1.setIcon(img2);
	    panel_1.add(lblImage_1);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JPanel panel_2 = new JPanel();
	panel_2.setBounds(10, 222, 200, 200);
	frmShoeName.getContentPane().add(panel_2);

	try {

	    Image img3Pre = images[2].getScaledInstance(200, 200, Image.SCALE_DEFAULT);
	    ImageIcon img3 = new ImageIcon(img3Pre);
	    JLabel lblImage_2 = new JLabel("");
	    lblImage_2.setIcon(img3);
	    panel_2.add(lblImage_2);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JPanel panel_3 = new JPanel();
	panel_3.setBounds(220, 222, 200, 200);
	frmShoeName.getContentPane().add(panel_3);

	try {

	    Image img4Pre = images[3].getScaledInstance(200, 200, Image.SCALE_DEFAULT);
	    ImageIcon img4 = new ImageIcon(img4Pre);
	    JLabel lblImage_3 = new JLabel("");
	    lblImage_3.setIcon(img4);
	    panel_3.add(lblImage_3);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JLabel lblshoeName = new JLabel(shoeName);
	lblshoeName.setForeground(Color.WHITE);
	lblshoeName.setFont(new Font("Segoe UI", Font.BOLD, 25));
	lblshoeName.setBounds(458, 37, 282, 77);
	this.frmShoeName.getContentPane().add(lblshoeName);

	JPanel panel_4 = new JPanel();
	panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_4.setBackground(new Color(180, 180, 180));
	panel_4.setBounds(460, 157, 50, 50);
	this.frmShoeName.getContentPane().add(panel_4);

	try {

	    JLabel lblNewLabel_1 = new JLabel(colors[0]);
	    lblNewLabel_1.setForeground(Color.WHITE);
	    panel_4.add(lblNewLabel_1);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JPanel panel_5 = new JPanel();
	panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_5.setBackground(new Color(180, 180, 180));
	panel_5.setBounds(520, 157, 50, 50);
	this.frmShoeName.getContentPane().add(panel_5);

	try {

	    JLabel lblNewLabel_2 = new JLabel(colors[1]);
	    lblNewLabel_2.setForeground(Color.WHITE);
	    panel_5.add(lblNewLabel_2);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JPanel panel_6 = new JPanel();
	panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_6.setBackground(new Color(180, 180, 180));
	panel_6.setBounds(580, 157, 50, 50);
	this.frmShoeName.getContentPane().add(panel_6);

	try {

	    JLabel lblNewLabel_3 = new JLabel(colors[2]);
	    lblNewLabel_3.setForeground(Color.WHITE);
	    panel_6.add(lblNewLabel_3);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JPanel panel_7 = new JPanel();
	panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_7.setBackground(new Color(180, 180, 180));
	panel_7.setBounds(640, 157, 50, 50);
	this.frmShoeName.getContentPane().add(panel_7);

	try {

	    JLabel lblNewLabel_4 = new JLabel(colors[3]);
	    lblNewLabel_4.setForeground(Color.WHITE);
	    panel_7.add(lblNewLabel_4);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JPanel panel_8 = new JPanel();
	panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_8.setBackground(new Color(180, 180, 180));
	panel_8.setBounds(700, 157, 50, 50);
	this.frmShoeName.getContentPane().add(panel_8);

	JLabel lblNewLabel_5 = new JLabel(colors[4]);
	lblNewLabel_5.setForeground(Color.WHITE);
	panel_8.add(lblNewLabel_5);

	JLabel lblNewLabel_6 = new JLabel("Available Colors:");
	lblNewLabel_6.setForeground(Color.WHITE);
	lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblNewLabel_6.setBounds(460, 111, 132, 35);
	this.frmShoeName.getContentPane().add(lblNewLabel_6);

	JLabel lblAvailableSizes = new JLabel("Available Sizes:");
	lblAvailableSizes.setForeground(Color.WHITE);
	lblAvailableSizes.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblAvailableSizes.setBounds(460, 230, 132, 35);
	this.frmShoeName.getContentPane().add(lblAvailableSizes);

	JPanel panel_9 = new JPanel();
	panel_9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_9.setBackground(new Color(180, 180, 180));
	panel_9.setBounds(460, 276, 95, 50);
	this.frmShoeName.getContentPane().add(panel_9);

	try {

	    JLabel lblNewLabel_9 = new JLabel(sizes[0]);
	    lblNewLabel_9.setForeground(Color.WHITE);
	    panel_9.add(lblNewLabel_9);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JPanel panel_10 = new JPanel();
	panel_10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_10.setBackground(new Color(180, 180, 180));
	panel_10.setBounds(565, 276, 95, 50);
	this.frmShoeName.getContentPane().add(panel_10);

	try {

	    JLabel lblNewLabel_10 = new JLabel(sizes[1]);
	    lblNewLabel_10.setForeground(Color.WHITE);
	    panel_10.add(lblNewLabel_10);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JPanel panel_11 = new JPanel();
	panel_11.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	panel_11.setBackground(new Color(180, 180, 180));
	panel_11.setBounds(668, 276, 95, 50);
	this.frmShoeName.getContentPane().add(panel_11);

	try {

	    JLabel lblNewLabel_11 = new JLabel(sizes[2]);
	    lblNewLabel_11.setForeground(Color.WHITE);
	    panel_11.add(lblNewLabel_11);

	} catch (ArrayIndexOutOfBoundsException e) {
	}

	JSeparator separator = new JSeparator();
	separator.setBackground(SystemColor.windowText);
	separator.setBounds(460, 222, 310, 11);
	this.frmShoeName.getContentPane().add(separator);

	JLabel lblNewLabel_7 = new JLabel("Review: " + review + "/5");
	lblNewLabel_7.setForeground(Color.WHITE);
	lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblNewLabel_7.setBounds(20, 434, 178, 14);
	this.frmShoeName.getContentPane().add(lblNewLabel_7);

	JLabel lblNewLabel_8 = new JLabel("<html>" + description);
	lblNewLabel_8.setForeground(Color.WHITE);
	lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 15));
	lblNewLabel_8.setBounds(460, 351, 299, 199);
	this.frmShoeName.getContentPane().add(lblNewLabel_8);

	JLabel lblStyle = new JLabel("Style: " + style);
	lblStyle.setForeground(Color.WHITE);
	lblStyle.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblStyle.setBounds(20, 458, 200, 19);
	this.frmShoeName.getContentPane().add(lblStyle);

	JLabel lblBrand = new JLabel("Brand: " + brand);
	lblBrand.setForeground(Color.WHITE);
	lblBrand.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblBrand.setBounds(20, 488, 200, 14);
	this.frmShoeName.getContentPane().add(lblBrand);

	JLabel lblTypeOfShoes = new JLabel("<html> Type of Shoes: " + typeofshoes);
	lblTypeOfShoes.setForeground(Color.WHITE);
	lblTypeOfShoes.setFont(new Font("Segoe UI", Font.BOLD, 15));
	lblTypeOfShoes.setBounds(20, 513, 200, 59);
	this.frmShoeName.getContentPane().add(lblTypeOfShoes);

	JButton btnNewButton = new JButton("Buy Raffle Tickets Here");
	btnNewButton.setBackground(new Color(180, 180, 180));
	btnNewButton.setForeground(Color.GRAY);
	btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
	btnNewButton.setBounds(220, 432, 200, 74);
	btnNewButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		ShoeRaffleService.service.getInstanceManager().BUYING_RAFFLE_TICKETS = new BuyingRaffleTickets(a, s);
	    }
	});

	this.frmShoeName.getContentPane().add(btnNewButton);

    }
}
