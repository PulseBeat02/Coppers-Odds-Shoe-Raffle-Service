package com.pulsebeat02.shoeraffleservice.application;

import java.io.IOException;

import com.pulsebeat02.shoeraffleservice.application.application.account.ManageAccounts;
import com.pulsebeat02.shoeraffleservice.application.application.payment.ManagePayments;

public class Refresh {

    public String cwd = System.getProperty("user.dir");

    public void start(String identifier) throws IOException {

	if (identifier.equals("account") || identifier.equals("payment")) {
	    reloadAccounts();
	}
    }

    public void reloadAccounts() throws IOException {
	ManageAccounts.duplicateLineDeletion();
	ManageAccounts.blankLineDeletion();
	ManageAccounts.save();
	try {
	    ManageAccounts.removeFirstLine(cwd + "/loginUsers");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	ManageAccounts.read();
    }

    public void reloadPayments() throws IOException {
	ManagePayments.save();
	ManagePayments.read();
    }

}
