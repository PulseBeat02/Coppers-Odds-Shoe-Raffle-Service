package com.pulsebeat02.shoeraffleservice.application.raffles;

import java.util.TimerTask;

import com.pulsebeat02.shoeraffleservice.ShoeRaffleService;
import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

public class isReached extends TimerTask {
    
    @Override
    public void run() {
	Logger.LOG.info("Opening isReached");
	Logger.LOG.info("Time Limit for Raffle Ended!");
	ShoeRaffleService.service.getInstanceManager().PICK_RANDOM_RAFFLE.doSelect = true;
    }

}
