package com.pulsebeat02.shoeraffleservice.application.paypal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ClientID_Secret {

    public static String getClientID() throws IOException {

	List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/Paypal-ClientID-Secret"),
		StandardCharsets.UTF_8);
	return lines.get(0).substring(10, lines.get(0).length());

    }

    public static String getSecret() throws IOException {

	List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/Paypal-ClientID-Secret"),
		StandardCharsets.UTF_8);
	return lines.get(1).substring(7, lines.get(1).length());

    }

}
