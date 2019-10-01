package com.pulsebeat02.main.gui.windows.raffles;

import java.util.TimerTask;

import com.pulsebeat02.main.util.logging.Logger;

public class isReached extends TimerTask{
	
	public static void start() {
		
		Logger.LOG.info("Opening isReached");
		Logger.LOG.info("Time Limit for Raffle Ended!");
		PickRandomRaffle.doSelect = true;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		start();
		
	}

}
