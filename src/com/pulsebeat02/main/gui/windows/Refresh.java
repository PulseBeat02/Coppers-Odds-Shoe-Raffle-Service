package com.pulsebeat02.main.gui.windows;

import java.io.IOException;

import com.pulsebeat02.main.gui.windows.account.ManageAccounts;
import com.pulsebeat02.main.gui.windows.payment.ManagePayments;

public class Refresh {

	static String cwd = System.getProperty("user.dir");

	public static void start(String identifier) throws IOException {

		if (identifier.equals("account")) {

			reloadAccounts();

		}

		else if (identifier.equals("payment")) {

			reloadPayments();

		}

	}

	public static void reloadAccounts() throws IOException {

		ManageAccounts.duplicateLineDeletion();
		ManageAccounts.blankLineDeletion();
		ManageAccounts.save();
		try {
			ManageAccounts.removeFirstLine(cwd + "/loginUsers");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ManageAccounts.read();

	}

	public static void reloadPayments() throws IOException {
		
		// ArrayUtilities.getRidOfNull(ManagePayments.allPayments);
		ManagePayments.save();
		ManagePayments.read();

	}

}
