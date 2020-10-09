package com.pulsebeat02.shoeraffleservice.application;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.pulsebeat02.shoeraffleservice.application.application.account.Account;
import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ChooseShoe {

    Shoe[] s;

    Account account;

    JPanel image_1;
    JPanel image_2;
    JPanel image_3;
    JLabel lblSorryThereAre;

    private JFrame frmBuyingShoes;

    public void start() {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    ChooseShoe window = new ChooseShoe(s, account);
		    window.frmBuyingShoes.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public ChooseShoe(Shoe[] s, Account account) {

	Image[] imagesFinal = new Image[s.length];
	for (int i = 0; i < imagesFinal.length; i++) {

	    try {
		Image image = ImageIO.read(new URL(s[i].imageURLs[0]));
		imagesFinal[i] = image;
	    } catch (MalformedURLException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	}

	initialize(s, account, imagesFinal);
    }

    private void initialize(Shoe[] s, Account account, Image[] imagesFinal) {

	this.frmBuyingShoes = new JFrame();
	this.frmBuyingShoes.setTitle("Buying Shoes");
	this.frmBuyingShoes.setIconImage(Toolkit.getDefaultToolkit().getImage(ChooseShoe.class
		.getResource("/com/pulsebeat02/main/resources/images/mainmenu/infoButton/infoicon.png")));
	this.frmBuyingShoes.setBounds(100, 100, 800, 600);
	this.frmBuyingShoes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.frmBuyingShoes.getContentPane().setLayout(null);
	this.frmBuyingShoes.setResizable(false);

	JLabel Title = new JLabel("Current Shoes to Buy:");
	Title.setFont(new Font("Tahoma", Font.BOLD, 40));
	Title.setHorizontalAlignment(SwingConstants.CENTER);
	Title.setBounds(0, 11, 784, 78);
	this.frmBuyingShoes.getContentPane().add(Title);

	this.image_1 = new JPanel();
	this.image_1.setBackground(new Color(230, 235, 237));
	this.image_1.setBounds(48, 130, 200, 200);
	this.frmBuyingShoes.getContentPane().add(image_1);

	switch (s.length) {
	
	case 0:
	    lblSorryThereAre.setVisible(true);
	    image_1.setVisible(false);
	    image_2.setVisible(false);
	    image_3.setVisible(false);
	    break;
	    
	case 1:
	    image_2.setVisible(false);
	    image_3.setVisible(false);
	    break;
	
	case 2:
	    image_3.setVisible(false);
	    Logger.LOG.info("One Shoe");
	    break;
	
	
	}

	ImageIcon img1 = new ImageIcon(imagesFinal[0]);
	JLabel lblNewLabel_2 = new JLabel("Image 1");
	lblNewLabel_2.setIcon(img1);
	this.image_1.add(lblNewLabel_2);


	JLabel SubTitle = new JLabel("Each shoe varies in Size, Brand, etc, so make sure to Look Carefully!");
	SubTitle.setHorizontalAlignment(SwingConstants.CENTER);
	SubTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
	SubTitle.setBounds(0, 83, 784, 25);
	this.frmBuyingShoes.getContentPane().add(SubTitle);

	this.image_2 = new JPanel();
	this.image_2.setBackground(new Color(230, 235, 237));
	this.image_2.setBounds(297, 130, 200, 200);
	this.frmBuyingShoes.getContentPane().add(image_2);

	try {

	    ImageIcon img2 = new ImageIcon(imagesFinal[1]);
	    JLabel lblNewLabel_3 = new JLabel("Image 2");
	    lblNewLabel_3.setIcon(img2);
	    this.image_2.add(lblNewLabel_3);

	    Logger.LOG.info("Getting Image");

	} catch (ArrayIndexOutOfBoundsException e) {

	    Logger.LOG.error("Image Not Found");
	}

	try {

	    this.image_3 = new JPanel();
	    this.image_3.setBackground(new Color(230, 235, 237));
	    this.image_3.setBounds(545, 130, 200, 200);
	    this.frmBuyingShoes.getContentPane().add(image_3);

	    ImageIcon img3 = new ImageIcon(imagesFinal[2]);
	    JLabel lblNewLabel_4 = new JLabel("Image 3");
	    lblNewLabel_4.setIcon(img3);
	    this.image_3.add(lblNewLabel_4);

	    Logger.LOG.info("Getting Image");

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 3 null");
	}

	try {

	    JLabel Shoe1Name = new JLabel(s[0].shoeName);
	    Shoe1Name.setBounds(48, 356, 113, 14);
	    this.frmBuyingShoes.getContentPane().add(Shoe1Name);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 1 null");
	}

	try {

	    JLabel Shoe2Name = new JLabel(s[1].shoeName);
	    Shoe2Name.setBounds(281, 356, 113, 14);
	    this.frmBuyingShoes.getContentPane().add(Shoe2Name);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 2 null");
	}

	try {

	    JLabel Shoe3Name = new JLabel(s[2].shoeName);
	    Shoe3Name.setBounds(522, 356, 113, 14);
	    this.frmBuyingShoes.getContentPane().add(Shoe3Name);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 3 null");
	}

	try {

	    JLabel Description1 = new JLabel(s[0].description);
	    Description1.setBounds(48, 462, 200, 98);
	    this.frmBuyingShoes.getContentPane().add(Description1);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 1 null");
	}

	try {

	    JLabel Description2 = new JLabel(s[1].description);
	    Description2.setBounds(281, 462, 200, 98);
	    this.frmBuyingShoes.getContentPane().add(Description2);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 2 null");
	}

	try {

	    JLabel Description3 = new JLabel(s[2].description);
	    Description3.setBounds(522, 462, 200, 98);
	    this.frmBuyingShoes.getContentPane().add(Description3);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 3 null");
	}

	try {

	    JLabel TotalRaffles1 = new JLabel(String.valueOf(s[0].totalRaffles));
	    TotalRaffles1.setBounds(48, 381, 85, 14);
	    this.frmBuyingShoes.getContentPane().add(TotalRaffles1);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 1 null");
	}

	try {

	    JLabel TotalRaffles2 = new JLabel(String.valueOf(s[1].totalRaffles));
	    TotalRaffles2.setBounds(281, 381, 85, 14);
	    this.frmBuyingShoes.getContentPane().add(TotalRaffles2);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 2 null");
	}

	try {

	    JLabel TotalRaffles3 = new JLabel(String.valueOf(s[2].totalRaffles));
	    TotalRaffles3.setBounds(520, 381, 85, 14);
	    this.frmBuyingShoes.getContentPane().add(TotalRaffles3);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 3 null");
	}

	try {

	    JLabel RafflesBought1 = new JLabel(String.valueOf(String.valueOf(account.rafflesBought[0])));
	    RafflesBought1.setBounds(48, 406, 140, 14);
	    this.frmBuyingShoes.getContentPane().add(RafflesBought1);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 1 null");
	}

	try {

	    JLabel RafflesBought2 = new JLabel(String.valueOf(String.valueOf(account.rafflesBought[1])));
	    RafflesBought2.setBounds(281, 406, 140, 14);
	    this.frmBuyingShoes.getContentPane().add(RafflesBought2);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 2 null");
	}

	try {

	    JLabel RafflesBought3 = new JLabel(String.valueOf(String.valueOf(account.rafflesBought[2])));
	    RafflesBought3.setBounds(522, 406, 140, 14);
	    this.frmBuyingShoes.getContentPane().add(RafflesBought3);

	} catch (ArrayIndexOutOfBoundsException e) {
	    Logger.LOG.error("Shoe 3 null");
	}

	this.lblSorryThereAre = new JLabel("Sorry, there are no shoes right now");
	this.lblSorryThereAre.setVisible(false);
	this.lblSorryThereAre.setFont(new Font("Tahoma", Font.BOLD, 25));
	this.lblSorryThereAre.setHorizontalAlignment(SwingConstants.CENTER);
	this.lblSorryThereAre.setBounds(10, 218, 774, 37);
	this.frmBuyingShoes.getContentPane().add(lblSorryThereAre);

    }
}
