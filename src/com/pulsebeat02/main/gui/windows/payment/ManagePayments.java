package com.pulsebeat02.main.gui.windows.payment;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.pulsebeat02.main.gui.windows.account.Account;
import com.pulsebeat02.main.gui.windows.account.ManageAccounts;
import com.pulsebeat02.main.util.logging.Logger;

public class ManagePayments {

	static String cwd = System.getProperty("user.dir");
	public static ConcurrentHashMap<String, Payment> allPayments = new ConcurrentHashMap<String, Payment>();
//	public static ArrayList<Payment> allPayments = new ArrayList<Payment>();
//	public static ArrayList<String> allPaymentIDs = new ArrayList<String>();

	public static void main(String[] args) {
		read();
	}

	public static void read() {

		Logger.LOG.info("Reading File");
		
		ManageAccounts.start();
		
		//Payment payment = new Payment("test", "test", "test", "test", 0, null, false, ManageAccounts.allAccounts.get(0));
		
		//allPayments.add(payment);
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(cwd + "/allPayments"));
			Logger.LOG.info("Loading Payments");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.LOG.error("Payment file not found");
			e.printStackTrace();
		}

		ArrayList<String> lines = new ArrayList<String>();

		int d = 0;

		while (scanner.hasNext()) {

			lines.add(scanner.nextLine());

			Logger.LOG.info("Reading Line: " + d);

			d++;

		}

		Logger.LOG.info("Getting Info");

		for (int i = 0; i < lines.size(); i++) {

			ArrayList<String> paymentString = new ArrayList<String>(Arrays.asList(lines.get(i).split(",")));

			String name = paymentString.get(0);
			String date = paymentString.get(1);
			String description = paymentString.get(2);

			int cost = Integer.parseInt(paymentString.get(3));

			boolean verified = Boolean.parseBoolean(paymentString.get(4));

			String ID = paymentString.get(5);
			String otherNotes = paymentString.get(6);
			Account account = Account.getAccountFromID(paymentString.get(7));

			Payment GeneratedPayment = new Payment(name, date, description, otherNotes, cost, UUID.fromString(ID), verified,
					account);
			
//			ManagePayments.allPaymentIDs.add(GeneratedPayment.id);
//			ManagePayments.allPayments.add(GeneratedPayment);
			
			allPayments.put(GeneratedPayment.id, GeneratedPayment);

			Logger.LOG.info("Created Payment: " + i);

		}

	}

	public static void save() {

		Logger.LOG.info("Saving");

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(cwd + "/allPayments", true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logger.LOG.info("Printing to File");

		for (int i = 0; i < allPayments.size(); i++) {
			
			Payment key = (Payment) allPayments.keySet().toArray()[i];
			
			if (key.account.accountID == null) {
				
				break;
				
			}

			try {
				writer.newLine();
				writer.write(Payment.toStringText(key));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
