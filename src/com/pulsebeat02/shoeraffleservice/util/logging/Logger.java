package com.pulsebeat02.main.util.logging;

import org.apache.log4j.LogManager;

public class Logger {
	
	public static final org.apache.log4j.Logger LOG = LogManager.getLogger(Logger.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LOG.debug("This Will Be Printed On Debug");
        LOG.info("This Will Be Printed On Info");
        LOG.warn("This Will Be Printed On Warn");
        LOG.error("This Will Be Printed On Error");
        LOG.fatal("This Will Be Printed On Fatal");
        LOG.info("Appending string: {}.");
        
        LOG.info("Logger has Loaded");

	}

}
