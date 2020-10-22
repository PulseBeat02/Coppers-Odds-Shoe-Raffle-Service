package com.pulsebeat02.shoeraffleservice.application.session;

import java.io.BufferedReader;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;
import com.pulsebeat02.shoeraffleservice.application.account.Account;
import com.pulsebeat02.shoeraffleservice.application.privacy.InvalidAccounts;
import com.pulsebeat02.shoeraffleservice.application.privacy.Location;

public class ManageSessions {

    static Account account;

    public ConcurrentHashMap<String, Session> allSessions = new ConcurrentHashMap<String, Session>();

    static String cwd = System.getProperty("user.dir");

    public void makeSession() throws IOException {

	String systemipaddress = "";
	try {
	    URL url_name = new URL("http://bot.whatismyipaddress.com");
	    BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
	    systemipaddress = sc.readLine().trim();
	} catch (Exception e) {
	    systemipaddress = "Cannot Execute Properly";
	}
	System.out.println("Public IP Address: " + systemipaddress + "\n");
	Location currentLocation = new Location();
	Session s = new Session(currentLocation, account);
	allSessions.put(s.location.IP, s);

    }

    public void check() {
	if (multipleSessions(account)) {
	    if ((boolean) multipleLocations()[0]) {
		
		List<Session> sessions = convert(multipleLocations()[1]);
		ShoeRaffleService.service.getInstanceManager().INVALID_ACCOUNTS = new InvalidAccounts(sessions.toArray(new Session[0]));
		
	    }

	}

    }

    @SuppressWarnings("unchecked")
    public List<Session> convert(Object obj) {
	List<Session> list = new ArrayList<>();
	if (obj.getClass().isArray()) {
	    list = Arrays.asList((Session[]) obj);
	} else if (obj instanceof Collection) {
	    list = new ArrayList<>((Collection<Session>) obj);
	}
	return list;
    }

    public void write(Session s) throws IOException {

	BufferedReader reader = new BufferedReader(new FileReader(cwd + "/session"));
	if (reader.readLine() == null) {
	    reader.close();
	    return;
	} else {
	    reader.close();
	    FileWriter fw = new FileWriter(cwd + "/session", true);
	    fw.write(s.toStringText(s));
	    fw.close();
	}

    }

    public void read() throws IOException {

	List<String> lines = Files.readAllLines(Paths.get(cwd + "/session"), StandardCharsets.UTF_8);
	for (int i = 0; i < lines.size(); i++) {
	    String[] content = lines.get(i).split(",");
	    Location location = Location.getLocation(allSessions, content[0]);
	    Account account = Account.getAccountFromID(content[4]);
	    Session session = new Session(location, account);
	    allSessions.put(session.location.IP, session);
	}

    }

    public Object[] multipleLocations() {

	ArrayList<Session> warningSessions = new ArrayList<Session>();
	for (Session first : allSessions.values()) {
	    for (Session second : allSessions.values()) {
		if (!first.equals(second) && (first.account).equals(second.account)) {
		    warningSessions.add(first);
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

    public boolean multipleSessions(Account account) {

	int count = 0;
	for (int i = 0; i < allSessions.size(); i++) {
	    Session key = (Session) allSessions.values().toArray()[i];
	    if ((key.account).equals(account)) {
		count++;
	    }
	}

	return count >= 2;

    }

    public void deleteOldSessions() throws IOException {

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	List<String> lines = Files.readAllLines(Paths.get(cwd + "/session"), StandardCharsets.UTF_8);

	if (lines.get(0).split(",")[5].contains(dateFormat.format(yesterday()))) {

	    PrintWriter writer = new PrintWriter(cwd + "/session");
	    writer.print("");
	    writer.close();

	}

    }

    private Date yesterday() {
	final Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DATE, -1);
	return cal.getTime();
    }

}
