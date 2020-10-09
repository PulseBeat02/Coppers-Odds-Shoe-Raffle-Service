package com.pulsebeat02.shoeraffleservice.application.raffles;

import com.pulsebeat02.shoeraffleservice.util.logging.Logger;

public class RaffleTicket {

    public String holder;
    public String ID;

    public RaffleTicket(String holder, String ID) {

	this.ID = ID;
	this.holder = holder;
	Logger.LOG.info("Created Raffle Ticket With ID: " + ID);

    }

    public RaffleTicket copy(RaffleTicket raf) throws CloneNotSupportedException {
	Logger.LOG.info("Copying RaffleTicket With ID: " + raf.ID);
	return (RaffleTicket) raf.clone();
    }

}
