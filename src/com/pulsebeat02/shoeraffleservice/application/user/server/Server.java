package com.pulsebeat02.shoeraffleservice.application.user.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	static String cwd = System.getProperty("user.dir");

	public static void send(String fileToSend) {

		while (true) {
			ServerSocket welcomeSocket = null;
			Socket connectionSocket = null;
			BufferedOutputStream outToClient = null;

			try {
				welcomeSocket = new ServerSocket(3248);
				connectionSocket = welcomeSocket.accept();
				outToClient = new BufferedOutputStream(connectionSocket.getOutputStream());
			} catch (IOException ex) {
				// Do exception handling
			}

			if (outToClient != null) {
				File myFile = new File(fileToSend);
				byte[] mybytearray = new byte[(int) myFile.length()];

				FileInputStream fis = null;

				try {
					fis = new FileInputStream(myFile);
				} catch (FileNotFoundException ex) {
					// Do exception handling
				}
				BufferedInputStream bis = new BufferedInputStream(fis);

				try {
					bis.read(mybytearray, 0, mybytearray.length);
					outToClient.write(mybytearray, 0, mybytearray.length);
					outToClient.flush();
					outToClient.close();
					connectionSocket.close();

					// File sent, exit the main method
					return;
				} catch (IOException ex) {
					// Do exception handling
				}
			}
		}

	}

	public static void recieve(String serverIP, int serverPort) {
		byte[] aByte = new byte[1];
		int bytesRead;

		Socket clientSocket = null;
		InputStream is = null;

		try {
			clientSocket = new Socket(serverIP, serverPort);
			is = clientSocket.getInputStream();
		} catch (IOException ex) {
			// Do exception handling
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		if (is != null) {

			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			try {
				fos = new FileOutputStream(cwd + "/users");
				bos = new BufferedOutputStream(fos);
				bytesRead = is.read(aByte, 0, aByte.length);

				do {
					baos.write(aByte);
					bytesRead = is.read(aByte);
				} while (bytesRead != -1);

				bos.write(baos.toByteArray());
				bos.flush();
				bos.close();
				clientSocket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
