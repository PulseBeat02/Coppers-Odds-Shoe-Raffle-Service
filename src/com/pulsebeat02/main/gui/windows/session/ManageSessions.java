package com.pulsebeat02.main.gui.windows.session;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.pulsebeat02.main.gui.windows.account.Account;
import com.pulsebeat02.main.gui.windows.privacy.InvalidAccounts;
import com.pulsebeat02.main.gui.windows.privacy.Location;

public class ManageSessions {

	static Account account;

//	public static ArrayList<Session> allSessions = new ArrayList<Session>();
//	public static ArrayList<String> allSessionIPs = new ArrayList<String>();

	public static ConcurrentHashMap<String, Session> allSessions = new ConcurrentHashMap<String, Session>();

	static String cwd = System.getProperty("user.dir");

	public static void makeSession() {

		String systemipaddress = "";
		try {
			URL url_name = new URL("http://bot.whatismyipaddress.com");

			BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));

			// reads system IPAddress
			systemipaddress = sc.readLine().trim();
		} catch (Exception e) {
			systemipaddress = "Cannot Execute Properly";
		}

		System.out.println("Public IP Address: " + systemipaddress + "\n");

		Location currentLocation = new Location();

		Session s = new Session(currentLocation, account);

		allSessions.put(s.location.IP, s);

	}

	public static void check() {

		if (multipleSessions(account)) {

			if ((boolean) multipleLocations()[0]) {

				InvalidAccounts.s = (Session[]) multipleLocations()[1];
				InvalidAccounts.start();

			}

		}

	}

	public static void write(Session s) {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(cwd + "/session"));

			if (reader.readLine() == null) {

				reader.close();
				return;

			}

			else {

				reader.close();
				FileWriter fw = new FileWriter(cwd + "/session", true);
				fw.write(s.toStringText(s));
				fw.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void read() {

		List<String> lines = null;

		try {
			lines = Files.readAllLines(Paths.get(cwd + "/session"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < lines.size(); i++) {

			String[] content = lines.get(i).split(",");

			Location location = Location.getLocation(allSessions, content[0]);
			Account account = Account.getAccountFromID(content[4]);

			Session session = new Session(location, account);

			allSessions.put(session.location.IP, session);

		}

	}

	public static Object[] multipleLocations() {

		ArrayList<Session> warningSessions = new ArrayList<Session>();

//		for (int i = 0; i < allSessions.size(); i++) {
//			
//			if ((allSessions.get(i).account).equals(allSessions.get(index))
//			
//		}

		for (int i = 0; i < allSessions.size(); i++) {// Check if two sessions are equal or not

			Session key = (Session) ManageSessions.allSessions.keySet().toArray()[i];

			for (int j = 0; j < allSessions.size(); j++) {

				Session otherKey = (Session) allSessions.keySet().toArray()[i];

				if ((key.account).equals(otherKey.account)) {

					warningSessions.add(key);

				}

			}

		}

		ArrayList<Session> differentLocations = new ArrayList<Session>();

		for (int i = 0; i < warningSessions.size(); i++) {

			for (int j = 0; j < warningSessions.size(); j++) {

				if (!(warningSessions.get(i).location).equals(warningSessions.get(i).location)) {

					Object[] objects = { true, differentLocations };

					return objects;
				}

			}

		}

		Object[] objects = { false, null };

		return objects;

	}

	public static boolean multipleSessions(Account account) {

		int count = 0;

		for (int i = 0; i < allSessions.size(); i++) {

			Session key = (Session) allSessions.keySet().toArray()[i];

			if ((key.account).equals(account)) {

				count++;

			}

		}

		if (count >= 2) {

			return true;

		}

		else {

			return false;

		}

	}

	public static void deleteOldSessions() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		List<String> lines = null;

		try {
			lines = Files.readAllLines(Paths.get(cwd + "/session"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (lines.get(0).split(",")[5].contains(dateFormat.format(yesterday()))) {

			PrintWriter writer = null;
			try {
				writer = new PrintWriter(cwd + "/session");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.print("");
			// other operations
			writer.close();

		}

	}

	private static Date yesterday() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

}
