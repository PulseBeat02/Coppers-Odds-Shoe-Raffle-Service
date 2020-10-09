package com.pulsebeat02.main.gui.application.raffles;

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

    public ArrayList<RaffleTicket> allRaffles;
    public Random randomGenerator;

    public boolean doSelect;

    public void start() {

	this.allRaffles = new ArrayList<RaffleTicket>();

	if (doSelect) {

	    RaffleTicket winner = anyItem();

	    Properties prop = new Properties();
	    prop.put("mail.smtp.host", "smtp.gmail.com");
	    prop.put("mail.smtp.port", "587");
	    prop.put("mail.smtp.auth", "true");
	    prop.put("mail.smtp.starttls.enable", "true");

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

		Transport.send(message);
		Logger.LOG.info("Sent Message");

	    } catch (MessagingException e) {
		Logger.LOG.error("Message Exception");
		e.printStackTrace();
	    }
	}

    }

    public int getTotalRaffles() {
	return allRaffles.size();
    }

    public RaffleTicket anyItem() {
	return allRaffles.get(randomGenerator.nextInt(allRaffles.size()));
    }

}
