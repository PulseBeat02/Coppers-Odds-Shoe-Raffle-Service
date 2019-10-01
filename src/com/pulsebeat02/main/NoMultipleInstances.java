package com.pulsebeat02.main;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;

public class NoMultipleInstances {

	static String userHome = System.getProperty("user.home");

	public static void main(boolean generate) {

		if (generate) {

			File file = new File(userHome, ".lock");
			try {
				FileChannel fc = FileChannel.open(file.toPath(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
				FileLock lock = fc.tryLock();
				if (lock == null) {
					Runtime.getRuntime().exit(0);
				}
			} catch (IOException e) {
				throw new Error(e);
			}

		}

		else {

			new File(userHome + ".lock").delete();

		}

	}

}
