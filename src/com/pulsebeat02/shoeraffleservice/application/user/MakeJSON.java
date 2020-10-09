package com.pulsebeat02.shoeraffleservice.application.user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson;
import com.pulsebeat02.shoeraffleservice.application.application.account.Account;
import com.pulsebeat02.shoeraffleservice.application.application.account.ManageAccounts;
import com.pulsebeat02.shoeraffleservice.application.user.server.Server;

public class MakeJSON {
	
	static String cwd = System.getProperty("user.dir");
	
	public static void addNewUserToNewFile(Account account) {
		
		String json = convertToJSON(account);
		write(json, account);
		
		ManageAccounts.allAccounts.put(account.accountID, account);
		
	}
	
	public static void save() {
		
		for (int i = 0; i < ManageAccounts.allAccounts.size(); i++) {
			
			Account account = (Account) ManageAccounts.allAccounts.keySet().toArray()[i];
			
			String json = convertToJSON(account);
			
			write(json, account);
			
		}
		
	}

	private static void write(String JSON, Account account) {

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(cwd + "/users/" + account.accountID), "UTF-8"))) {
			try {
				writer.write(JSON);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Server.send(cwd + "/users/" + account.accountID);

	}

	private static String convertToJSON(Account account) {

		Gson gson = new Gson();
		return gson.toJson(account);

	}

	public static void read() {
	
		ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<String, Account>();
		
		Server.recieve("127.0.0.1", 26665);

		File listOfFiles = new File(cwd + "/users");

		File[] files = listOfFiles.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile();
			}
		});

		for (int i = 0; i < files.length; i++) {

			byte[] bytes = null;
			try {
				bytes = Files.readAllBytes(files[i].toPath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String json = null;
			try {
				json = new String(bytes, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Gson gson = new Gson();
			Account account = gson.fromJson(json, Account.class);
			
			accounts.put(account.accountID, account);

		}
		
		ManageAccounts.allAccounts = accounts;

	}

}
