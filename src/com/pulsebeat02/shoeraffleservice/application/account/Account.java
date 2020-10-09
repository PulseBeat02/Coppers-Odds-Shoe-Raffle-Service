package com.pulsebeat02.shoeraffleservice.application.account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.UUID;

import com.pulsebeat02.shoeraffleservice.application.Refresh;
import com.pulsebeat02.shoeraffleservice.application.StartingWindow;
import com.pulsebeat02.shoeraffleservice.application.application.login.LoginPanel;
import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

public class Account {

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
	this.accountID = accountID == null ? UUID.randomUUID().toString() : accountID;
	this.raffleTicketsBought = raffleTicketsBought;
	this.shoesWon = shoesWon;
	this.rafflesBought = raffesBought;
	this.lastLogin = lastLogin;
	this.isBanned = isBanned;
	this.stayLoggedIn = stayLoggedIn;
	this.accountMusic = accountMusic;

	Logger.LOG.info("Account made with UUID " + "[" + this.accountID + "]");

    }

    public static String toStringText(Account account) {

	StringBuilder sb = new StringBuilder();
	sb.append("username=" + account.username);
	sb.append("\n");
	sb.append("firstname=" + account.firstName);
	sb.append("\n");
	sb.append("lastname=" + account.lastName);
	sb.append("\n");
	sb.append("password=" + account.password);
	sb.append("\n");
	sb.append("lastLogin=" + account.lastLogin);
	sb.append("\n");
	sb.append("email=" + account.email);
	sb.append("\n");
	sb.append("raffleTicketsBought=" + account.raffleTicketsBought);
	sb.append("\n");
	sb.append("rafflesBought[0]=" + account.rafflesBought[0]);
	sb.append("\n");
	sb.append("rafflesBought[1]=" + account.rafflesBought[1]);
	sb.append("\n");
	sb.append("rafflesBought[2]=" + account.rafflesBought[2]);
	sb.append("\n");
	sb.append("isBanned=" + account.isBanned);
	sb.append("\n");
	sb.append("stayLoggedIn=" + account.stayLoggedIn);
	sb.append("\n");
	sb.append("accountID=" + account.accountID);
	sb.append("\n");
	sb.append("biography=" + account.biography);
	sb.append("\n");
	sb.append("accountMusic=" + account.accountMusic);
	sb.append("\n");

	return sb.toString();

    }

    public static void findAccount(String accountText) throws IOException {

	String[] seperatedText = accountText.split(",");

	for (Account acc : ManageAccounts.allAccounts.values()) {

	    if (acc.accountID.equals(seperatedText[11])) {

		System.out.println("==========================================");
		System.out.println("DIAGNOSTIC INFORMATION FOR COPPERS ODDS");
		System.out.println("==========================================");
		System.out.println("Account Information for Debugging:");
		System.out.println("Account ID: " + acc.accountID);
		System.out.println("Account Username: " + acc.username);
		System.out.println("Account First Name: " + acc.firstName);
		System.out.println("Account Last Name: " + acc.lastName);
		System.out.println("Account Biography: " + acc.biography);
		System.out.println("Account Email: " + acc.email);
		System.out.println("Account Music: " + acc.accountMusic);
		System.out.println("Account Password: " + acc.password);
		System.out.println("Account Tickets Bought: " + acc.raffleTicketsBought);
		System.out.println("Account Shoes Won: " + acc.shoesWon);
		System.out.println("Account Stay Logged In (T/F): " + acc.stayLoggedIn);
		System.out.println("==========================================");

		acc.stayLoggedIn = getMotherboardSN();

		PrintWriter writer = new PrintWriter(System.getProperty("user.dir") + "/lastLogin", "UTF-8");
		writer.println(Account.getMotherboardSN());
		writer.close();

		LoginPanel.loadingFrame.setVisible(false);
		LoginPanel.loadingFrame.dispose();

		StartingWindow window = new StartingWindow(acc);
		window.frmShoeRafflePrize.setVisible(true);

	    }

	}

    }

    public static String getMotherboardSN() throws IOException {

	if (isWindows()) {

	    String result = "";
	    String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
		    + "Set colItems = objWMIService.ExecQuery _ \n" + "   (\"Select * from Win32_BaseBoard\") \n"
		    + "For Each objItem in colItems \n" + "    Wscript.Echo objItem.SerialNumber \n"
		    + "    exit for  ' do the first cpu only! \n" + "Next \n";

	    File file = File.createTempFile("realhowto", ".vbs");
	    file.deleteOnExit();

	    FileWriter fw = new java.io.FileWriter(file);
	    fw.write(vbs);
	    fw.close();

	    Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
	    BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

	    String line;
	    while ((line = input.readLine()) != null) {
		result += line;
	    }
	    input.close();

	    return result.trim();

	}

	if (isMac()) {

	    Runtime runtime = Runtime.getRuntime();
	    Process process = runtime.exec(new String[] { "/usr/sbin/system_profiler", "SPHardwareDataType" });

	    OutputStream os = process.getOutputStream();
	    InputStream is = process.getInputStream();

	    BufferedReader br = new BufferedReader(new InputStreamReader(is));

	    String sn = null;
	    String line = null;
	    String marker = "Serial Number";
	    while ((line = br.readLine()) != null) {
		if (line.contains(marker)) {
		    sn = line.split(":")[1].trim();
		    break;
		}
	    }

	    if (sn == null) {
		throw new RuntimeException("Cannot find computer SN");
	    }

	    os.close();

	    return sn;

	}

	if (isUnix()) {

	    ProcessBuilder builder = new ProcessBuilder("cat /sys/class/dmi/id/product_uuid");
	    builder.redirectErrorStream(true);

	    Process process = builder.start();
	    InputStream is = process.getInputStream();

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    String ID = reader.readLine();

	    return ID;

	}

	return System.getProperty("os.name").toLowerCase();

    }

    public static void printToFile(String accountString) throws IOException {

	Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/loginUsers"));
	BufferedWriter writer = new BufferedWriter(
		new FileWriter(System.getProperty("user.dir") + "/loginUsers", true));

	if (scanner.hasNext() && (scanner.nextLine() != null || !scanner.nextLine().isEmpty())) {
	    writer.newLine();
	} else {
	    writer.write(accountString);
	}

	scanner.close();
	writer.close();

    }

    public static Account getAccountFromID(String ID) throws IOException {

	Refresh.reloadAccounts();

	for (Account acc : ManageAccounts.allAccounts.values()) {
	    if (acc.accountID.equals(ID)) {
		return acc;
	    }
	}

	return null;

    }

    public static boolean isWindows() {
	return (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0);
    }

    public static boolean isMac() {
	return (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
	return (System.getProperty("os.name").toLowerCase().indexOf("nix") >= 0
		|| System.getProperty("os.name").toLowerCase().indexOf("nux") >= 0
		|| System.getProperty("os.name").toLowerCase().indexOf("aix") > 0);
    }

    public static boolean checkAccountDuplicate(String username, String email) throws IOException {

	BufferedReader reader = new BufferedReader(
		new FileReader(new File(System.getProperty("user.dir") + "/loginUsers")));

	String line = null;
	while ((line = reader.readLine()) != null) {
	    if (line.split(",")[0].equals(username)) {
		reader.close();
		return true;
	    } else if (line.split(",")[0].equals(username)) {
		reader.close();
		return true;
	    } else {
		reader.close();
		return false;
	    }
	}
	reader.close();

	return false;

    }

}
