package com.pulsebeat02.shoeraffleservice.application.ads;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadLinks {

    public static String[] getLinks() throws IOException {

	return Files.readAllLines(Paths.get(System.getProperty("user.dir") + "/AdLinks.txt"), StandardCharsets.UTF_8)
		.toArray(new String[0]);

    }

}
