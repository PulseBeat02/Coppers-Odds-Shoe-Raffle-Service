package com.pulsebeat02.main.gui.windows.password;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.pulsebeat02.main.util.logging.Logger;

public class PasswordConfig {

	static Properties prop;

	public static void start() {
		// TODO Auto-generated method stub

		prop = new Properties();

		try {
			// set the properties value
			
			Logger.LOG.info("Loading Password Properties");
			
			prop.setProperty("time", "10000");
			prop.setProperty("PasswordEnabled", "mtrue");

			// save properties to project root folder

			prop.store(new FileOutputStream("config.properties"), null);

		} catch (IOException ex) {
			
			Logger.LOG.info("File not found");
			ex.printStackTrace();

		}

	}

}
