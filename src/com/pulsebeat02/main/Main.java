package com.pulsebeat02.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.pulsebeat02.main.gui.application.NoConnection;
import com.pulsebeat02.main.gui.application.StartingWindow;
import com.pulsebeat02.main.gui.application.login.LoginPanel;
import com.pulsebeat02.main.gui.application.payment.ManagePayments;
import com.pulsebeat02.main.gui.application.register.AccountCreated;
import com.pulsebeat02.main.gui.application.session.ClearSessions;
import com.pulsebeat02.main.gui.application.thread.ManageThreads;
import com.pulsebeat02.main.util.JSON.ReadJSON;
import com.pulsebeat02.main.util.logging.Logger;

public class Main {

	public static boolean isConnected = true;

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		boolean generate = Boolean.parseBoolean(args[0]);
		boolean useLock = Boolean.parseBoolean(args[1]);

		int cpuThreads = Integer.parseInt(args[2]);
		int userThreadSize = Integer.parseInt(args[3]);

		ManageThreads.userSize = userThreadSize;
		LoginPanel.maxThreads = cpuThreads;

		AccountCreated.maxThreads = cpuThreads;
		AccountCreated.userThreadSize = userThreadSize;

		StartingWindow.maxThreads = cpuThreads;
		StartingWindow.userThreadSize = userThreadSize;

		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream("latest.log"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (useLock) {

			NoMultipleInstances.main(generate);

		}

		Thread resuseThread = new Thread(() -> {
			ManageThreads.reuseThreads();
		});

		ManageThreads.utilityThreads.add(resuseThread);

		ClearSessions clearsession = new ClearSessions();
		clearsession.start();

		System.setOut(out);

		String cwd = System.getProperty("user.dir");
		String cwdHome = System.getProperty("user.home");

		Logger.LOG.info(cwd);
		Logger.LOG.info(cwdHome);

		ReadJSON.main(null);

		Logger.LOG.info("Program Loading");

		Logger.main(null);

		ManagePayments.read();

		if (/* !LoginPanel.keepLogin && */ isConnected) {

			LoginPanel.start(true);

			Logger.LOG.info("User Kept Login");
			Logger.LOG.info("Network Connection Stable");

		}

		if (!isConnected()) {

			NoConnection.start();
			isConnected = false;
			Logger.LOG.warn("No network connection detected, showing NoConnection GUI");

		}

		// LoadingScreen.main(null);

	}

	public static boolean isConnected() {
		try {
			Logger.LOG.info("Running Network Test");
			final URL url = new URL("http://www.bing.com");
			Logger.LOG.info("Pinging Bing");
			final URLConnection conn = url.openConnection();
			conn.connect();
			conn.getInputStream().close();
			System.out.println("Connection Good");
			return true;
		} catch (MalformedURLException e) {
			Logger.LOG.error("URL has problems");
			throw new RuntimeException(e);
		} catch (IOException e) {
			Logger.LOG.error("IOException");
			return false;
		}
	}

	public static <K> void sort(ConcurrentHashMap<String, K> map) {

		Map<String, K> sortedMap = map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors
				.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		map = (ConcurrentHashMap<String, K>) sortedMap;

	}

}
