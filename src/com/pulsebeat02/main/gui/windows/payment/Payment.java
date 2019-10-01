package com.pulsebeat02.main.gui.windows.payment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import com.pulsebeat02.main.gui.windows.account.Account;

public class Payment {

	static String cwd = System.getProperty("user.dir");

	public String paymentName;
	public String paymentDate;
	public String description;
	public String otherNotes;

	double cost;

	public String id;

	boolean isVerified;

	public Account account;

	public Payment(String name, String date, String description, String notes, double price, UUID id, boolean isVerified,
			Account account) {

		this.paymentName = name;
		this.paymentDate = date;
		this.description = description;
		this.otherNotes = notes;
		this.cost = price;

		if (id != null)
			this.id = id.toString();
		else {
			this.id = UUID.randomUUID().toString();
		}

		this.isVerified = isVerified;
		this.account = account;

	}

	public static String toStringText(Payment payment) {

		String text = payment.paymentName + "," + payment.paymentDate + "," + payment.description + "," + payment.cost
				+ "," + payment.isVerified + "," + payment.id + "," + payment.otherNotes + ","
				+ payment.account.accountID;

		return text;

	}

	public static void printToFile(String payment) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(cwd + "/allPayments", true));
			writer.newLine();
			writer.write(payment);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Deprecated
	public static Payment getPaymentFromStringArrayTable(String[] payment, Account account) {
		
		String name = payment[0];
		String date = payment[1];
		String description = payment[2];
		
		int cost = Integer.parseInt(payment[3]);
		
		boolean isVerified = Boolean.getBoolean(payment[4]);
		
		String paymentID = payment[5];
		String otherNotes = payment[6];
		
		Payment newPayment = new Payment(name, date, description, otherNotes, cost, UUID.fromString(paymentID), isVerified, account);
		
		return newPayment;
		
	}

}
