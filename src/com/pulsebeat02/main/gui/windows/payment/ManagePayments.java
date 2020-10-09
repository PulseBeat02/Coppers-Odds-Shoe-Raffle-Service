package com.pulsebeat02.main.gui.windows.payment;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.pulsebeat02.main.gui.windows.account.Account;
import com.pulsebeat02.main.gui.windows.account.ManageAccounts;
import com.pulsebeat02.main.util.logging.Logger;

public class ManagePayments {

    public ConcurrentHashMap<String, Payment> allPayments;

    public void read() throws IOException {

	this.allPayments = new ConcurrentHashMap<>();

	ManageAccounts.start();

	Scanner scanner = new Scanner(new FileReader(System.getProperty("user.dir") + "/allPayments"));
	Logger.LOG.info("Loading Payments");

	ArrayList<String> lines = new ArrayList<String>();

	int d = 0;
	while (scanner.hasNext()) {
	    lines.add(scanner.nextLine());
	    Logger.LOG.info("Reading Line: " + d);
	    d++;
	}
	scanner.close();

	for (int i = 0; i < lines.size(); i++) {

	    String[] paymentString = lines.get(i).split(",");

	    String name = paymentString[0];
	    String date = paymentString[1];
	    String description = paymentString[2];

	    int cost = Integer.parseInt(paymentString[3]);

	    boolean verified = Boolean.parseBoolean(paymentString[4]);

	    String ID = paymentString[5];
	    String otherNotes = paymentString[6];
	    Account account = Account.getAccountFromID(paymentString[7]);

	    Payment GeneratedPayment = new Payment(name, date, description, otherNotes, cost, UUID.fromString(ID),
		    verified, account);

	    allPayments.put(GeneratedPayment.id, GeneratedPayment);

	    Logger.LOG.info("Created Payment: " + i);

	}

    }

    public void save() throws IOException {

	Logger.LOG.info("Saving");

	BufferedWriter writer = new BufferedWriter(
		new FileWriter(System.getProperty("user.dir") + "/allPayments", true));

	Logger.LOG.info("Printing to File");

	for (String key : allPayments.keySet()) {

	    Payment p = allPayments.get(key);

	    if (p.account.accountID == null) {

		break;

	    }

	    writer.newLine();
	    writer.write(Payment.toStringText(p));

	}
	writer.close();
    }

}
