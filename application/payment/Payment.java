package com.pulsebeat02.shoeraffleservice.application.payment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import com.pulsebeat02.shoeraffleservice.application.account.Account;

public class Payment {

    public String paymentName;
    public String paymentDate;
    public String description;
    public String otherNotes;

    double cost;

    public String id;

    boolean isVerified;

    public Account account;

    public Payment(String name, String date, String description, String notes, double price, UUID id,
	    boolean isVerified, Account account) {

	this.paymentName = name;
	this.paymentDate = date;
	this.description = description;
	this.otherNotes = notes;
	this.cost = price;
	this.id = (id == null ? UUID.randomUUID().toString() : id.toString());
	this.isVerified = isVerified;
	this.account = account;

    }

    public static String toStringText(Payment payment) {

	StringBuilder sb = new StringBuilder();
	sb.append(payment.paymentName);
	sb.append(",");
	sb.append(payment.paymentDate);
	sb.append(",");
	sb.append(payment.description);
	sb.append(",");
	sb.append(payment.cost);
	sb.append(",");
	sb.append(payment.isVerified);
	sb.append(",");
	sb.append(payment.id);
	sb.append(",");
	sb.append(payment.otherNotes);
	sb.append(",");
	sb.append(payment.account.accountID);

	return sb.toString();

    }

    public static void printToFile(String payment) throws IOException {

	BufferedWriter writer = new BufferedWriter(
		new FileWriter(System.getProperty("user.dir") + "/allPayments", true));
	writer.newLine();
	writer.write(payment);
	writer.close();

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

	Payment newPayment = new Payment(name, date, description, otherNotes, cost, UUID.fromString(paymentID),
		isVerified, account);

	return newPayment;

    }

}
