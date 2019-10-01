package com.pulsebeat02.main.gui.windows.thread;

import java.util.ArrayList;
import java.util.List;

import com.pulsebeat02.main.gui.windows.BuyingTickets;
import com.pulsebeat02.main.util.logging.Logger;

public class ManageThreads {
	
	public static MusicThread musicThread;
	public static BuyingTickets paymentThread;

	public static int userSize;

	public static ArrayList<List<Thread>> userThreads = new ArrayList<List<Thread>>(userSize);
	public static ArrayList<Thread> utilityThreads = new ArrayList<Thread>();

	Thread mainThread = Thread.currentThread();

	public static void reuseThreads() {

		for (int i = 0; i < userThreads.size(); i++) {

			for (int z = 0; z < userThreads.get(i).size(); z++) {

				try {
					userThreads.get(i).get(z).join(86400000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (!userThreads.get(i).get(z).isAlive()) {

					userThreads.remove(userThreads.get(i));

				}

			}

		}

	}

	public static void addThread(Thread thread, int maxThreads) throws Exception { // Adds Thread to Necessary Slot

		int usersPerThread = (int) userThreads.size() / maxThreads; // Calculate Users Per Thread
		
		if (maxThreads <= 0) { // Check if thread is 0 or below, throw error
			
			throw new Exception("Invalid Thread Number, Thread Number Should Be Above 0");
			
		}

		for (int i = 0; i < userThreads.size();) { // Check each Thread List in the ArrayList full of Thread List

			if (userThreads.get(i).size() < usersPerThread) { // Check if Thread List Size is Less than Total

				userThreads.get(i).add(thread); // If So, Thread List has Open Slot, Add it

			}

			else {
				
				if ((userThreads.get(i).size() + 1) < userThreads.size()) { // Else if Thread List is Full

					i++; // Check Next List

				}

				else { // Rare Case Where Threads Aren't Enough

					Logger.LOG.error("Error, Not Enough Threads");
					throw new Exception("Not Enough Threads, Please Add More");

				}

			}

		}

	}

}
