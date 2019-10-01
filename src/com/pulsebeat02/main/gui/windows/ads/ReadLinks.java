package com.pulsebeat02.main.gui.windows.ads;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadLinks {

	static String cwd = System.getProperty("user.dir");

	public static String[] getLinks() {
		
		List<String> lines = null;

		try {
			lines = Files.readAllLines(Paths.get(cwd + "/AdLinks.txt"), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lines.toArray(new String[0]);

	}

}
