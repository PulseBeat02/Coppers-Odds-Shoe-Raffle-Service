package com.pulsebeat02.main.gui.application.thread;

import java.util.ArrayList;
import java.util.List;

import com.pulsebeat02.main.ShoeRaffleService;
import com.pulsebeat02.main.gui.application.BuyingTickets;
import com.pulsebeat02.main.util.logging.Logger;

public class ManageThreads {

    public MusicThread musicThread;
    public BuyingTickets paymentThread;

    public List<List<Thread>> userThreads;
    public List<Thread> utilityThreads;

    Thread mainThread = Thread.currentThread();

    @Deprecated
    public void reuseThreads() {

	for (int i = 0; i < userThreads.size(); i++) {
	    for (int z = 0; z < userThreads.get(i).size(); z++) {
		try {
		    userThreads.get(i).get(z).join(86400000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		if (!userThreads.get(i).get(z).isAlive()) {
		    userThreads.remove(userThreads.get(i));
		}

	    }

	}

    }

    public void addThread(Thread thread, int maxThreads) throws Exception { // Adds Thread to Necessary Slot
	if (userThreads == null || utilityThreads == null) {
	    userThreads = new ArrayList<List<Thread>>(ShoeRaffleService.getInstance().userThreadSize);
	    utilityThreads = new ArrayList<Thread>();
	}
	int usersPerThread = (int) userThreads.size() / maxThreads; // Calculate Users Per Thread
	if (maxThreads <= 0) { // Check if thread is 0 or below, throw error
	    throw new Exception("Invalid Thread Number, Thread Number Should Be Above 0");
	}
	for (int i = 0; i < userThreads.size();) { // Check each Thread List in the ArrayList full of Thread List
	    if (userThreads.get(i).size() < usersPerThread) { // Check if Thread List Size is Less than Total
		userThreads.get(i).add(thread); // If So, Thread List has Open Slot, Add it
	    } else {
		if ((userThreads.get(i).size() + 1) < userThreads.size()) { // Else if Thread List is Full
		    i++; // Check Next List
		} else { // Rare Case Where Threads Aren't Enough
		    Logger.LOG.error("Error, Not Enough Threads");
		    throw new Exception("Not Enough Threads, Please Add More");
		}
	    }
	}
    }

}
