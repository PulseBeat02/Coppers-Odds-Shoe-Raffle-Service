package com.pulsebeat02.main.gui.windows.account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import com.pulsebeat02.main.util.logging.Logger;

public class ManageAccounts implements Serializable {

	static String cwd = System.getProperty("user.dir");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static ConcurrentHashMap<String, Account> allAccounts = new ConcurrentHashMap<String, Account>();
	
	// public static ArrayList<Account> allAccounts = new ArrayList<Account>();
	// public static ArrayList<String> allAccountIDs = new ArrayList<String>();

	public static void start() {

		// blankLineDeletion();
		read();

//		String cwd = System.getProperty("user.dir");

//		for (int i = 0; i < allAccounts.size(); i++) {
//
//			BufferedWriter writer = null;
//			try {
//				writer = new BufferedWriter(new FileWriter(cwd + "accounts.txt", true));
//				Logger.LOG.info("Writing accounts in file.");
//			} catch (IOException e1) {
//				Logger.LOG.error("File Not Found");
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			try {
//
//				writer.write(Account.toStringText(allAccounts.get(i)));
//				writer.write("/n");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				Logger.LOG.error("File Not Found");
//				e.printStackTrace();
//			}
//			try {
//				writer.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				Logger.LOG.error("File Not Found");
//				e.printStackTrace();
//			}
//
//		}

		for (int z = 0; z > allAccounts.size(); z++) {
			
			Account key = (Account) allAccounts.keySet().toArray()[z];

			if (key.isBanned) {

				allAccounts.remove(key, key.accountID);

			}

		}

//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			@Override
//			public void run() {
//				Logger.LOG.info("Shutting Down");
//				save();
//			}
//
//		});

	}

	public static void save() {

		Logger.LOG.info("Saving");

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(cwd + "/loginUsers", true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logger.LOG.info("Printing to File");

		for (int i = 0; i < allAccounts.size(); i++) {
			
			Account key = (Account) allAccounts.values().toArray()[i];

			try {
				writer.newLine();
				writer.write(Account.toStringText(key));
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

	public static void read() {

		Logger.LOG.info("Reading File");

		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(cwd + "/loginUsers"));
			Logger.LOG.info("Loading Accounts");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.LOG.error("Account file not found");
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

			ArrayList<String> accountString = new ArrayList<String>(Arrays.asList(lines.get(i).split(",")));

			String username = accountString.get(0);
			String firstName = accountString.get(1);
			String lastName = accountString.get(2);
			String password = accountString.get(3);
			String lastLogin = accountString.get(4);
			String email = accountString.get(5);

			int raffleTicketsBought = Integer.parseInt(accountString.get(6));
			int shoesWon = Integer.parseInt(accountString.get(7));

			int[] ticketsbought = new int[3];

			String ticketsBought = accountString.get(8);
			String[] ticketsBoughtArray = ticketsBought.split(" ");

			for (int z = 0; z < ticketsbought.length; z++) {

				ticketsbought[z] = Integer.parseInt(ticketsBoughtArray[z]);

			}

			boolean isBanned = Boolean.getBoolean(accountString.get(9));

			String motherBoardID = Account.getMotherboardSN();

			String UUID = accountString.get(11);

			String bio = accountString.get(12);
			
			int accountMusic = Integer.parseInt(accountString.get(13));

			Account account = new Account(username, firstName, lastName, password, lastLogin, email, bio,
					raffleTicketsBought, shoesWon, ticketsbought, isBanned, motherBoardID, null, accountMusic);
			account.accountID = UUID;
			allAccounts.put(account.accountID, account);

			Logger.LOG.info("Created Account: " + i);

		}

	}

	public static void blankLineDeletion() {

		Logger.LOG.info("Getting Rid of Blank Lines");

		Scanner scanner = null;
		BufferedWriter writer = null;
		try {
			scanner = new Scanner(new FileReader(cwd + "/loginUsers"));
			Logger.LOG.info("Loading Accounts");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.LOG.error("Account file not found");
			e.printStackTrace();
		}

		try {
			writer = new BufferedWriter(new FileWriter(cwd + "/loginUsers", true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int blankLineCount = 0;

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			if (!line.isEmpty()) {
				Logger.LOG.warn("Blank Line Found");
				try {
					writer.write(line);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					writer.write("\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				blankLineCount++;
			}
		}
		Logger.LOG.info(blankLineCount + " blank lines found");

	}

	public static void duplicateLineDeletion() {

		BufferedReader br1 = null;
		try {
			br1 = new BufferedReader(new FileReader(cwd + "/loginUsers"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(cwd + "/loginUsers");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String line1 = null;
		try {
			line1 = br1.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// loop for each line of input.txt
		while (line1 != null) {
			boolean flag = false;

			// BufferedReader object for output.txt
			BufferedReader br2 = null;
			try {
				br2 = new BufferedReader(new FileReader(cwd + "/loginUsers"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String line2 = null;
			try {
				line2 = br2.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// loop for each line of output.txt
			while (line2 != null) {

				if (line1.equals(line2)) {
					flag = true;
					break;
				}

				try {
					line2 = br2.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			// if flag = false
			// write line of input.txt to output.txt
			if (!flag) {
				pw.println(line1);

				// flushing is important here
				pw.flush();
			}

			try {
				line1 = br1.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// closing resources
		try {
			br1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.close();

	}

	public static void removeFirstLine(String fileName) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
		// Initial write position
		long writePosition = raf.getFilePointer();
		raf.readLine();
		// Shift the next lines upwards.
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
