package com.pulsebeat02.shoeraffleservice.application;

import java.io.IOException;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;
import com.pulsebeat02.shoeraffleservice.application.account.ManageAccounts;

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
	ShoeRaffleService.service.getInstanceManager().MANAGE_PAYMENTS.save();
	ShoeRaffleService.service.getInstanceManager().MANAGE_PAYMENTS.read();
    }

}
