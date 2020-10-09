package com.pulsebeat02.main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.UnsupportedLookAndFeelException;

import com.pulsebeat02.main.gui.windows.NoConnection;
import com.pulsebeat02.main.gui.windows.login.LoginPanel;
import com.pulsebeat02.main.gui.windows.payment.ManagePayments;
import com.pulsebeat02.main.gui.windows.session.ClearSessions;
import com.pulsebeat02.main.util.JSON.ReadJSON;
import com.pulsebeat02.main.util.logging.Logger;

public class ShoeRaffleService {

	static ShoeRaffleService INSTANCE;
	
	public boolean isConnected;

	public boolean generate;
	public boolean useLock;

	public int cpuThreads;
	public int userThreadSize;

	public ShoeRaffleService(String[] args) throws IOException {
		
		try {
			switch (args.length) {
			
			case 0:
				this.generate = true;
				this.useLock = true;
				this.cpuThreads = 8;
				this.userThreadSize = 1;
				NoMultipleInstances.check(generate);
				break;
				
			case 1:
				this.generate = Boolean.parseBoolean(args[0]);
				this.useLock = true;
				this.cpuThreads = 8;
				this.userThreadSize = 1;
				NoMultipleInstances.check(generate);
				break;

			case 2:
				this.generate = Boolean.parseBoolean(args[0]);
				this.useLock = Boolean.parseBoolean(args[1]);
				this.cpuThreads = 8;
				this.userThreadSize = 1;
				NoMultipleInstances.check(generate);
				break;

			case 3:
				this.generate = Boolean.parseBoolean(args[0]);
				this.useLock = Boolean.parseBoolean(args[1]);
				this.cpuThreads = Integer.parseInt(args[2]);
				this.userThreadSize = 1;
				NoMultipleInstances.check(generate);
				break;

			case 4:
				this.generate = Boolean.parseBoolean(args[0]);
				this.useLock = Boolean.parseBoolean(args[1]);
				this.cpuThreads = Integer.parseInt(args[2]);
				this.userThreadSize = Integer.parseInt(args[3]);
				NoMultipleInstances.check(generate);
				break;

			default:
				this.generate = Boolean.parseBoolean(args[0]);
				this.useLock = Boolean.parseBoolean(args[1]);
				this.cpuThreads = Integer.parseInt(args[2]);
				this.userThreadSize = Integer.parseInt(args[3]);
				NoMultipleInstances.check(generate);
				break;

			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		ClearSessions clearsession = new ClearSessions();
		clearsession.start();

		PrintStream out = new PrintStream(new FileOutputStream("latest.log"));
		System.setOut(out);

		String cwd = System.getProperty("user.dir");
		String cwdHome = System.getProperty("user.home");

		Logger.LOG.info(cwd);
		Logger.LOG.info(cwdHome);

		ReadJSON.main(null);

		Logger.LOG.info("Program Loading");

		Logger.main(null);

		ManagePayments.read();

		if (isConnected()) {

			try {
			    LoginPanel.start(true);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}

			Logger.LOG.info("User Kept Login");
			Logger.LOG.info("Network Connection Stable");

		} else {

			NoConnection.start();
			this.isConnected = false;
			Logger.LOG.warn("No network connection detected, showing NoConnection GUI");

		}

//		LoadingScreen.main(null);
		
//		Thread resuseThread = new Thread(() -> {
//			ManageThreads.reuseThreads();
//		});
//
//		ManageThreads.utilityThreads.add(resuseThread);
		
	}

	public static void main(String[] args) throws IOException {
		INSTANCE = new ShoeRaffleService(args);
	}
	
	public static ShoeRaffleService getInstance() {
		return INSTANCE;
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

}
