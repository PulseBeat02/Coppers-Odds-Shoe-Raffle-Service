package com.pulsebeat02.shoeraffleservice.application.raffles;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

public class Timers {

    public static void start() {

	Logger.LOG.info("Making Timer");

	Calendar today = Calendar.getInstance();
	today.set(Calendar.HOUR_OF_DAY, 2);
	today.set(Calendar.MINUTE, 0);
	today.set(Calendar.SECOND, 0);
	
	Timer timer = new Timer();
	TimerTask reached = new isReached();
	timer.schedule(reached, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day

	Logger.LOG.info("Timer Set");

    }

}
