package com.pulsebeat02.shoeraffleservice;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;

public class NoMultipleInstances {

	public static void check(boolean generate) throws IOException {
		
		String userHome = System.getProperty("user.home");
		
		if (generate) {

			File file = new File(userHome, ".lock");
			FileChannel fc = FileChannel.open(file.toPath(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			FileLock lock = fc.tryLock();
			
			if (lock == null) {
				Runtime.getRuntime().exit(0);
			}

		} else {

			new File(userHome + ".lock").delete();

		}

	}

}
