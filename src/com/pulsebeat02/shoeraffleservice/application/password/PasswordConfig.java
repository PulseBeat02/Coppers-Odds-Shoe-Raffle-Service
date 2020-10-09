package com.pulsebeat02.shoeraffleservice.application.password;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

public class PasswordConfig {

    static Properties prop;

    public static void start() {

	prop = new Properties();

	try {
	    Logger.LOG.info("Loading Password Properties");
	    prop.setProperty("time", "10000");
	    prop.setProperty("PasswordEnabled", "mtrue");
	    prop.store(new FileOutputStream("config.properties"), null);
	} catch (IOException ex) {
	    Logger.LOG.info("File not found");
	    ex.printStackTrace();
	}

    }

}
