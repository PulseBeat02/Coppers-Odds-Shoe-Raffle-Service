package com.pulsebeat02.main.gui.windows.session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.pulsebeat02.main.gui.windows.account.Account;
import com.pulsebeat02.main.gui.windows.privacy.Location;

public class Session {

	public Location location;

	public String locationFinal;
	public String time;
	public String os;
	public String osVersion;
	public String osArchitecture;

	public Account account;

	public Session(Location location, Account account) {

		this.location = location;
		this.locationFinal = location.city + ", " + location.region_state + ", " + location.zipcode + " ("
				+ location.country + ")";

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		LocalDateTime now = LocalDateTime.now();

		this.time = dtf.format(now);
		this.os = System.getProperty("os.name");
		this.osVersion = System.getProperty("os.version");
		this.osArchitecture = System.getProperty("os.arch");

		this.account = account;

	}

	public String toStringText(Session s) {

		String string = Location.toStringText(s.location) + "," + s.os + "," + s.osVersion + "," + s.osArchitecture
				+ "," + s.account.accountID + s.time;
		
		return string;

	}

}
