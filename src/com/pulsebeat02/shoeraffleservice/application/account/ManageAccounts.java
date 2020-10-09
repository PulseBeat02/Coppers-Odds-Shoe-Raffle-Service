package com.pulsebeat02.shoeraffleservice.application.account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

public class ManageAccounts implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static ConcurrentHashMap<String, Account> allAccounts;

    public static void start() throws IOException {

	allAccounts = new ConcurrentHashMap<String, Account>();
	read();

	for (String id : allAccounts.keySet()) {
	    Account a = allAccounts.get(id);
	    if (a.isBanned) {
		allAccounts.remove(a, a.accountID);
	    }
	}

    }

    public static void save() throws IOException {

	Logger.LOG.info("Saving");

	BufferedWriter writer = new BufferedWriter(
		new FileWriter(System.getProperty("user.dir") + "/loginUsers", true));
	for (Account acc : allAccounts.values()) {
	    writer.newLine();
	    writer.write(Account.toStringText(acc));
	}
	writer.close();

    }

    public static void read() throws IOException {

	Logger.LOG.info("Reading File");

	BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/loginUsers"));

	ArrayList<String> lines = new ArrayList<String>();
	String next = br.readLine();
	for (int i = 0; next != null; i++) {
	    lines.add(next);
	    Logger.LOG.info("Reading Line: " + i);
	    next = br.readLine();
	}
	br.close();

	for (int i = 0; i < lines.size(); i++) {

	    String[] accountString = lines.get(i).split(",");

	    String username = accountString[0];
	    String firstName = accountString[1];
	    String lastName = accountString[2];
	    String password = accountString[3];
	    String lastLogin = accountString[4];
	    String email = accountString[5];

	    int raffleTicketsBought = Integer.parseInt(accountString[6]);
	    int shoesWon = Integer.parseInt(accountString[7]);

	    int[] ticketsbought = new int[3];

	    String ticketsBought = accountString[8];
	    String[] ticketsBoughtArray = ticketsBought.split(" ");

	    for (int z = 0; z < ticketsbought.length; z++) {
		ticketsbought[z] = Integer.parseInt(ticketsBoughtArray[z]);
	    }

	    boolean isBanned = Boolean.getBoolean(accountString[9]);

	    String motherBoardID = Account.getMotherboardSN();
	    String UUID = accountString[11];
	    String bio = accountString[12];

	    int accountMusic = Integer.parseInt(accountString[13]);

	    Account account = new Account(username, firstName, lastName, password, lastLogin, email, bio,
		    raffleTicketsBought, shoesWon, ticketsbought, isBanned, motherBoardID, null, accountMusic);
	    account.accountID = UUID;
	    allAccounts.put(account.accountID, account);

	}

    }

    public static void blankLineDeletion() throws IOException {

	Scanner scanner = new Scanner(new FileReader(System.getProperty("user.dir") + "/loginUsers"));
	BufferedWriter writer = new BufferedWriter(
		new FileWriter(System.getProperty("user.dir") + "/loginUsers", true));

	int blankLineCount = 0;
	while (scanner.hasNext()) {
	    String line = scanner.nextLine();
	    if (!line.isEmpty()) {
		writer.write(line);
		writer.write("\n");
		blankLineCount++;
	    }
	}
	Logger.LOG.warn(blankLineCount + " blank lines found");

	scanner.close();
	writer.close();

    }

    public static void duplicateLineDeletion() throws IOException {

	BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/loginUsers"));
	BufferedReader check = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/loginUsers"));

	PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "/loginUsers");

	String line1 = br.readLine();
	while (line1 != null) {
	    boolean flag = false;
	    String line2 = check.readLine();
	    while (line2 != null) {
		if (line1.equals(line2)) {
		    flag = true;
		    break;
		}
		line2 = check.readLine();
	    }
	    if (!flag) {
		pw.println(line1);
		pw.flush();
	    }
	    line1 = br.readLine();
	}
	br.close();
	check.close();

	pw.close();

    }

    public static void removeFirstLine(String fileName) throws IOException {

	RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
	raf.readLine();

	long writePosition = raf.getFilePointer();
	long readPosition = raf.getFilePointer();
	byte[] buff = new byte[1024];
	int n;
	while (-1 != (n = raf.read(buff))) {
	    raf.seek(writePosition);
	    raf.write(buff, 0, n);
	    readPosition += n;
	    writePosition += n;
	    raf.seek(readPosition);
	}
	raf.setLength(writePosition);
	raf.close();

    }

}
