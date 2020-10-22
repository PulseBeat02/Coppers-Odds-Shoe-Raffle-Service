package com.pulsebeat02.shoeraffleservice.application;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

public class Shoe {

    public int shoePrice;
    public int shipping;
    public int tax;
    public int subtotal;
    public int totalRaffles;

    public double review;

    public String shoeName;
    public String style;
    public String typeOfShoes;
    public String brand;
    public String description;

    public String[] imageURLs;
    public String[] colors;
    public String[] sizes;

    public boolean isSold;

    public Shoe(int shoePrice, int shipping, int tax, int subtotal, double review, int totalRaffles, String shoeName,
	    String style, String typeOfShoes, String brand, String[] imageURLs, String description, String[] colors,
	    String[] sizes, boolean isSold) {

	this.shoePrice = shoePrice;
	this.shipping = shipping;
	this.tax = tax;
	this.subtotal = subtotal;
	this.review = review;
	this.totalRaffles = totalRaffles;
	this.sizes = sizes;
	this.shoeName = shoeName;
	this.style = style;
	this.typeOfShoes = typeOfShoes;
	this.description = description;
	this.colors = colors;
	this.isSold = isSold;
	this.brand = brand;

    }

    public Shoe(String shoePrice2, String shipping2, String tax2, String subTotal2, String review2,
	    String totalRaffles2, String shoeName2, String style2, String typeOfShoes2, String brand2,
	    List<String> images2, String description2, List<String> color, List<String> sizes2, String isSold2) {

	this.shoePrice = Integer.parseInt(shoePrice2);
	this.shipping = Integer.parseInt(shipping2);
	this.tax = Integer.parseInt(tax2);
	this.subtotal = Integer.parseInt(subTotal2);
	this.review = Double.parseDouble(review2);
	this.totalRaffles = Integer.parseInt(totalRaffles2);
	this.shoeName = shoeName2;
	this.style = style2;
	this.typeOfShoes = typeOfShoes2;
	this.brand = brand2;
	this.description = description2;
	this.colors = (String[]) color.toArray();
	this.sizes = (String[]) sizes2.toArray();
	this.isSold = Boolean.getBoolean(isSold2);

    }

    public static Image[] loadImage(String[] imageURLs) throws MalformedURLException, IOException {

	Image[] images = new Image[imageURLs.length];

	for (int i = 0; i < imageURLs.length; i++) {
	    images[i] = ImageIO.read(new URL(imageURLs[i]));
	}

	return images;

    }

}
