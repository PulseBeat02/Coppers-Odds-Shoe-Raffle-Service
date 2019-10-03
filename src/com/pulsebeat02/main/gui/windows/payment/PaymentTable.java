package com.pulsebeat02.main.gui.windows.payment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pulsebeat02.main.gui.windows.StartingWindow;
import com.pulsebeat02.main.gui.windows.account.Account;
import com.pulsebeat02.main.gui.windows.account.ManageAccounts;
import com.pulsebeat02.main.util.logging.Logger;

import java.awt.Cursor;
import java.awt.Toolkit;

public class PaymentTable {

	public static Account account;

	JFrame f;
	JTable j;

	// Constructor
	public PaymentTable() {

		ManageAccounts.read();

		f = new JFrame();
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(
				PaymentTable.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/dollarbill.png")));

		// Frame Title
		f.setTitle("Payments");

		// Data to be displayed in the JTable

		ArrayList<String[]> data = new ArrayList<String[]>();

		for (int i = 0; i < getPayments(account).length; i++) {

			Payment currentPayment = getPayments(account)[i];

			String name = currentPayment.paymentName;
			String date = currentPayment.paymentDate;
			String description = currentPayment.description;
			String cost = String.valueOf(currentPayment.cost);
			String verified = String.valueOf(currentPayment.isVerified);
			String ID = currentPayment.id.toString();
			String notes = currentPayment.otherNotes;

			String[] payment = { name, date, description, cost, verified, ID, notes };

			data.add(payment);

		}

		// Column Names
		String[] columnNames = { "Payment Name", "Date of Payment", "Description", "Cost", "Verified?", "Payment ID",
				"Other Notes" };

		// Initializing the JTable

		String[][] finalData = new String[data.size()][6];
		finalData = data.toArray(finalData);

		j = new JTable(finalData, columnNames);
		j.setBounds(30, 40, 200, 300);
		j.setEnabled(false);

		// adding it to JScrollPane
		JScrollPane sp = new JScrollPane(j);
		f.getContentPane().add(sp);
		// Frame Size
		f.setSize(750, 550);
		// Frame Visible = true
		f.setVisible(true);
		
		StartingWindow.frmShoeRafflePrize.setEnabled(true);
		StartingWindow.frmShoeRafflePrize.setCursor(Cursor.getDefaultCursor());

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				Logger.LOG.info("Shutting Down");
				ManagePayments.save();
			}

		});

	}

	public static void start() {

		new PaymentTable();

	}

	public static Payment[] getPayments(Account account) {

		ManagePayments.read();

		ArrayList<Payment> payments = new ArrayList<Payment>();

		Payment[] allPayments = getPayments();

		for (int i = 0; i < allPayments.length; i++) {

			Payment key = allPayments[i];
			
			System.out.println(key.account);

			if ((key.account.accountID).equals(account.accountID) && key != null) {

				payments.add(key);

			}

			else {

				Logger.LOG.error("Warning, Account is Null");

				Payment[] dummy = new Payment[0];

				dummy[0] = key;

				return dummy;

			}

		}

		return payments.stream().toArray(Payment[]::new);

	}

	static int binarySearch(ArrayList<String> arr, String x) {
		int l = 0, r = arr.size() - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;

			int res = x.compareTo(arr.get(m));

			// Check if x is present at mid
			if (res == 0)
				return m;

			// If x greater, ignore left half
			if (res > 0)
				l = m + 1;

			// If x is smaller, ignore right half
			else
				r = m - 1;
		}

		return -1;
	}

	public static Payment[] getPayments() {

		Set<Entry<String, Payment>> set = ManagePayments.allPayments.entrySet();

		Payment[] payments = new Payment[set.size()];

		for (int i = 0; i < payments.length; i++) {

			payments[i] = Payment.class.cast(getValue(set, i));

		}

		return payments;

	}

	public static Object getValue(Set<Entry<String, Payment>> set, int index) {

		Iterator<Entry<String, Payment>> it = set.iterator();

		int target = 0;

		while (it.hasNext()) {

			target++;

			if (target == index) {

				Map.Entry<String, Payment> entry = (Map.Entry<String, Payment>) it.next();
				return entry.getValue();

			}

		}
		
		return null;

	}

}
