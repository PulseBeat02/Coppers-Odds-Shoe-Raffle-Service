package com.pulsebeat02.shoeraffleservice.application.payment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pulsebeat02.shoeraffleservice.application.StartingWindow;
import com.pulsebeat02.shoeraffleservice.application.application.account.Account;
import com.pulsebeat02.shoeraffleservice.application.application.account.ManageAccounts;
import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.io.IOException;

public class PaymentTable {

    public Account account;
    
    public PaymentTable() throws IOException {

	ManageAccounts.read();
	
	JFrame f = new JFrame();
	f.setTitle("Payments");
	f.setIconImage(Toolkit.getDefaultToolkit().getImage(
		PaymentTable.class.getResource("/com/pulsebeat02/main/resources/images/mainmenu/dollarbill.png")));

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

	String[] columnNames = { "Payment Name", "Date of Payment", "Description", "Cost", "Verified?", "Payment ID",
		"Other Notes" };

	String[][] finalData = new String[data.size()][6];
	finalData = data.toArray(finalData);

	JTable j = new JTable(finalData, columnNames);
	j.setBounds(30, 40, 200, 300);
	j.setEnabled(false);

	JScrollPane sp = new JScrollPane(j);
	f.getContentPane().add(sp);
	f.setSize(750, 550);
	f.setVisible(true);

	StartingWindow.frmShoeRafflePrize.setEnabled(true);
	StartingWindow.frmShoeRafflePrize.setCursor(Cursor.getDefaultCursor());

	Runtime.getRuntime().addShutdownHook(new Thread() {
	    @Override
	    public void run() {
		Logger.LOG.info("Shutting Down");
		try {
		    ManagePayments.save();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }

	});

    }

    public static void start() throws IOException {

	new PaymentTable();

    }

    public static Payment[] getPayments(Account account) throws IOException {

	ManagePayments.read();

	ArrayList<Payment> payments = new ArrayList<Payment>();
	Payment[] allPayments = getPayments();
	for (int i = 0; i < allPayments.length; i++) {
	    Payment key = allPayments[i];
	    if ((key.account.accountID).equals(account.accountID) && key != null) {
		payments.add(key);
	    } else {
		Logger.LOG.error("Warning, Account is Null");
		Payment[] dummy = new Payment[0];
		dummy[0] = key;
		return dummy;
	    }
	}

	return payments.stream().toArray(Payment[]::new);

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
