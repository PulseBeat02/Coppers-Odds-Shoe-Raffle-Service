package com.pulsebeat02.main.gui.windows.raffles;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.pulsebeat02.main.util.logging.Logger;

public class PickRandomRaffle {

	public static ArrayList<RaffleTicket> allRaffles;
	private static Random randomGenerator;

	static boolean doSelect;

	public static void start() {

		Logger.LOG.info("Opening PickRandomRaffle");

		allRaffles = new ArrayList<RaffleTicket>();

		if (doSelect) {

			RaffleTicket winner = anyItem();

			Logger.LOG.info("Preparing Message");

			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true"); // TLS

			// get Session
			Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("coppersodds@gmail.com", "Chatimac1$");
				}
			});
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress("coppersodds@gmail.com"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(winner.holder));
				message.setSubject("Raffle Ticket Winner");
				message.setText(
						"Hello. This is Coppers Odds. We are pleased to say that your have won the shoe raffle! Please copy the following ID and email us to get your shoe: "
								+ winner.ID);

				// send the message
				Transport.send(message);

				Logger.LOG.info("Sent Message");

			} catch (MessagingException e) {
				Logger.LOG.error("Message Exception");
				e.printStackTrace();
			}
		}

		// Email Winner to Get Shoe

	}

	public static int getTotalRaffles() {

		return allRaffles.size();

	}

	public static RaffleTicket anyItem() {

		int index = randomGenerator.nextInt(allRaffles.size());
		RaffleTicket raffle = allRaffles.get(index);

		Logger.LOG.info("Picked Random Raffle");

		return raffle;

	}

}
