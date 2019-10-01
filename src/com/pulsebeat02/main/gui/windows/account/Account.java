package com.pulsebeat02.main.gui.windows.account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.UUID;

import com.pulsebeat02.main.gui.windows.Refresh;
import com.pulsebeat02.main.gui.windows.StartingWindow;
import com.pulsebeat02.main.gui.windows.login.LoginPanel;
import com.pulsebeat02.main.util.logging.Logger;

public class Account {

	static String cwd = System.getProperty("user.dir");
	static String OS = System.getProperty("os.name").toLowerCase();

	public String username;
	public String firstName;
	public String lastName;
	public String password;
	public String email;
	public String lastLogin;
	public String accountID;
	public String stayLoggedIn;
	public String biography;

	public int raffleTicketsBought;
	public int shoesWon;

	public int[] rafflesBought;

	public boolean isBanned;

	public int accountMusic;

	public Account(String username, String firstName, String lastName, String password, String lastLogin, String email,
			String biography, int raffleTicketsBought, int shoesWon, int[] raffesBought, boolean isBanned,
			String stayLoggedIn, String accountID, int accountMusic) {

		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.biography = biography;

		if (accountID != null)
			this.accountID = accountID;
		else {

			this.accountID = UUID.randomUUID().toString();

		}
		this.raffleTicketsBought = raffleTicketsBought;
		this.shoesWon = shoesWon;
		this.rafflesBought = raffesBought;
		this.lastLogin = lastLogin;
		this.isBanned = isBanned;
		this.stayLoggedIn = stayLoggedIn;
		this.accountMusic = accountMusic;

		Logger.LOG.info("New Account Made");

	}

	public static String toStringText(Account account) {

		// TODO Auto-generated method stub

		String text = account.username + "," + account.firstName + "," + account.lastName + "," + account.password + ","
				+ account.lastLogin + "," + account.email + "," + account.raffleTicketsBought + "," + account.shoesWon
				+ "," + account.rafflesBought[0] + " " + account.rafflesBought[1] + " " + account.rafflesBought[2] + ","
				+ account.isBanned + "," + account.stayLoggedIn + "," + account.accountID + "," + account.biography
				+ "," + account.accountMusic;

		Logger.LOG.info("Made New Account: " + text);

		return text;

	}

	@SuppressWarnings("static-access")
	public static void findAccount(String accountText) {

		String[] seperatedText = accountText.split(",");

		for (int i = 0; i < ManageAccounts.allAccounts.size(); i++) {
			
			Account key = (Account) ManageAccounts.allAccounts.values().toArray()[i];

			if (key.accountID.equals(seperatedText[11])) {

				Logger.LOG.info("Got Account: " + key.accountID);

				Account account = key;

				Logger.LOG.info("Starting Main Window");

				StartingWindow.account = account;

				account.stayLoggedIn = getMotherboardSN();

				String cwd = System.getProperty("user.dir");

				PrintWriter writer = null;
				try {
					writer = new PrintWriter(cwd + "/lastLogin", "UTF-8");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				writer.println(Account.getMotherboardSN());
				writer.close();
				
				System.out.println("PrintWriter finished");
				
				System.out.println("==========================================");
				System.out.println("DIAGNOSTIC INFORMATION FOR COPPERS ODDS");
				System.out.println("==========================================");
				System.out.println("Account Information for Debugging:");
				System.out.println("Account ID: " + account.accountID);
				System.out.println("Account Username: " + account.username);
				System.out.println("Account First Name: " + account.firstName);
				System.out.println("Account Last Name: " + account.lastName);
				System.out.println("Account Biography: " + account.biography);
				System.out.println("Account Email: " + account.email);
				System.out.println("Account Music: " + account.accountMusic);
				System.out.println("Account Password: " + account.password);
				System.out.println("Account Tickets Bought: " + account.raffleTicketsBought);
				System.out.println("Account Shoes Won: " + account.shoesWon);
				System.out.println("Account Stay Logged In (T/F): " + account.stayLoggedIn);
				System.out.println("==========================================");

				// StartingWindow.frmShoeRafflePrize = frame;
				
				LoginPanel.loadingFrame.setVisible(false);
				LoginPanel.loadingFrame.dispose();

				StartingWindow window = new StartingWindow(account);
				window.frmShoeRafflePrize.setVisible(true);
				
				LoginPanel.frmLogin.setVisible(false);
				LoginPanel.frmLogin.dispose();
				
				Logger.LOG.info("Closing Login Window");
				
				System.out.println("Window Loaded In");
				

			}

		}

	}
	
	public static String getMotherboardSN() {

		if (isWindows()) { // Gets Motherboard ID and returns it as a String

			String result = "";
			try {
				File file = File.createTempFile("realhowto", ".vbs");
				file.deleteOnExit();
				FileWriter fw = new java.io.FileWriter(file);

				String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
						+ "Set colItems = objWMIService.ExecQuery _ \n" + "   (\"Select * from Win32_BaseBoard\") \n"
						+ "For Each objItem in colItems \n" + "    Wscript.Echo objItem.SerialNumber \n"
						+ "    exit for  ' do the first cpu only! \n" + "Next \n";

				fw.write(vbs);
				fw.close();
				Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
				BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = input.readLine()) != null) {
					result += line;
				}
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result.trim();

		}

		if (isMac()) { // Gets Serial Number and returns it as a String

			String sn = null;

			OutputStream os = null;
			InputStream is = null;

			Runtime runtime = Runtime.getRuntime();
			Process process = null;
			try {
				process = runtime.exec(new String[] { "/usr/sbin/system_profiler", "SPHardwareDataType" });
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			os = process.getOutputStream();
			is = process.getInputStream();

			try {
				os.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			String marker = "Serial Number";
			try {
				while ((line = br.readLine()) != null) {
					if (line.contains(marker)) {
						sn = line.split(":")[1].trim();
						break;
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

			if (sn == null) {
				throw new RuntimeException("Cannot find computer SN");
			}

			return sn;

		}

		if (isUnix()) { // Gets ID using "cat /sys/class/dmi/id/product_uuid" and returns it as a String

			try {
				ProcessBuilder builder = new ProcessBuilder("cat /sys/class/dmi/id/product_uuid");
				builder.redirectErrorStream(true);
				Process process = builder.start();
				InputStream is = process.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String ID = reader.readLine();
				return ID;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return OS;

	}

	public static void printToFile(String accountString) {

		try {
			Scanner scanner = new Scanner(new File(cwd + "/loginUsers"));
			BufferedWriter writer = new BufferedWriter(new FileWriter(cwd + "/loginUsers", true));

			if (scanner.hasNext() && (scanner.nextLine() != null || !scanner.nextLine().isEmpty())) {

				writer.newLine();

			}

			else {

				writer.write(accountString);

			}

			scanner.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Account getAccountFromID(String ID) {

		Refresh.reloadAccounts();

		for (int i = 0; i < ManageAccounts.allAccounts.size(); i++) {
			
			Account key = (Account) ManageAccounts.allAccounts.values().toArray()[i];

			if (key.accountID.equals(ID)) {

				return key;

			}

		}
		return null;

	}

	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

	}

	public static boolean checkAccountDuplicate(String username, String email) {
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(cwd + "/loginUsers")));
			try {
				while ((line = reader.readLine()) != null) {

					if (line.split(",")[0].equals(username)) {

						reader.close();
						return true;

					}

					else if (line.split(",")[0].equals(username)) {

						reader.close();
						return true;

					}

					else {

						reader.close();
						return false;

					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

}
