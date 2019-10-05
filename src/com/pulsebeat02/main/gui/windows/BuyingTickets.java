package com.pulsebeat02.main.gui.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import com.pulsebeat02.main.gui.Shoe;
import com.pulsebeat02.main.gui.windows.account.Account;
import com.pulsebeat02.main.gui.windows.raffles.PickRandomRaffle;
import com.pulsebeat02.main.gui.windows.raffles.RaffleTicket;
import com.pulsebeat02.main.util.JSON.Edit_JSON;
import com.pulsebeat02.main.util.logging.Logger;
import com.pulsebeat02.main.gui.windows.payment.ManagePayments;
import com.pulsebeat02.main.gui.windows.paypal.ClientID_Secret;
import com.pulsebeat02.main.gui.windows.paypal.RunScript;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;

public class BuyingTickets {

	static Shoe s;

	private JFrame frmBuyingTickets;
	private JTextField textField;
	private JTextField txtExampledomaincom;

	boolean sendEmail = false;

	String orderDetails;

	static int currentShoe;

	static Account account;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyingTickets window = new BuyingTickets(s, account);
					window.frmBuyingTickets.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BuyingTickets(Shoe s, Account account) {
		initialize(s, account);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Shoe s, Account account) {

		Logger.LOG.info("Opening Buying Tickets");

		frmBuyingTickets = new JFrame();
		frmBuyingTickets.getContentPane().setBackground(new Color(211, 211, 211));
		frmBuyingTickets.setIconImage(Toolkit.getDefaultToolkit().getImage(BuyingTickets.class
				.getResource("/com/pulsebeat02/main/resources/images/mainmenu/infoButton/infoicon.png")));
		frmBuyingTickets.setTitle("Buying Tickets");
		frmBuyingTickets.setBounds(100, 100, 450, 250);
		frmBuyingTickets.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmBuyingTickets.getContentPane().setLayout(null);
		frmBuyingTickets.setResizable(false);

		JLabel lblNewLabel = new JLabel("Amount Tickets You Want to Buy:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 347, 35);
		frmBuyingTickets.getContentPane().add(lblNewLabel);

		JLabel lblYourEmail = new JLabel("Your Email:");
		lblYourEmail.setForeground(Color.WHITE);
		lblYourEmail.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblYourEmail.setBounds(10, 57, 124, 35);
		frmBuyingTickets.getContentPane().add(lblYourEmail);

		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost

		textField = new JFormattedTextField(format);
		textField.setForeground(Color.BLACK);
		textField.setText("1");
		textField.setBounds(365, 20, 65, 20);
		frmBuyingTickets.getContentPane().add(textField);
		textField.setColumns(10);

		txtExampledomaincom = new JTextField();
		txtExampledomaincom.setForeground(Color.BLACK);
		txtExampledomaincom.setText(account.email);
		txtExampledomaincom.setBounds(135, 65, 295, 20);
		frmBuyingTickets.getContentPane().add(txtExampledomaincom);
		txtExampledomaincom.setColumns(10);
		String email = txtExampledomaincom.getText();

		JCheckBox chckbxSendAndEmail = new JCheckBox("Send and Email When for Verification");
		chckbxSendAndEmail.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxSendAndEmail.setBounds(10, 97, 418, 23);
		frmBuyingTickets.getContentPane().add(chckbxSendAndEmail);
		chckbxSendAndEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				sendEmail = true;
				Logger.LOG.info("User wants to send email");
			}

			@Override
			public void focusLost(FocusEvent e) {
				sendEmail = false;
				Logger.LOG.info("User doesn't want to send email");
			}

		});

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(10, 187, 124, 23);
		frmBuyingTickets.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			private String shipping = String.valueOf(s.shipping);
			private String tax = String.valueOf(s.tax);

			@Override
			public void actionPerformed(ActionEvent e) {

				String clientID = "AXWWfp8KsCf6kojBHqRj_T_UbMANBywrKpYKF4RYk8d4RAPJuKOLVTssutZFG3QaphfckVX5kMEK9ASq";
				String secret = "EKhXVGxGanyOnEp5rzDryPqnu0nxXJA9l80U9S-mETsAVGyqtnD_g6r6ESjVI3AzBy4wczBdjqJ2mX2w";

				if (!ClientID_Secret.getClientID().equals("empty")) {

					clientID = ClientID_Secret.getClientID();
					Logger.LOG.info("Using Client ID specified in Paypal-ClientID-Secret file.");

				}

				else if (!ClientID_Secret.getSecret().equals("empty")) {

					secret = ClientID_Secret.getSecret();
					Logger.LOG.info("Using Secret specified in Paypal-ClientID-Secret file.");

				}

				else {

					Logger.LOG.info("Using default Client ID and Secret.");

				}

				ArrayList<Payment> currentPayments = new ArrayList<Payment>();
				ArrayList<Payment> failedPayments = new ArrayList<Payment>();

				int value = Integer.valueOf(textField.getText());

				if (value <= 0 || value > 10) {

					textField.setText("1");

				}

				int amountofTickets = Integer.parseInt(textField.getText());
				double price = amountofTickets * (s.shoePrice * 0.01);

				String totalPrice = String.valueOf(price);

				ArrayList<RaffleTicket> raffles = new ArrayList<RaffleTicket>();

				int currentShoe = indexOf(StartingWindow.shoesInGui, s, 0);

				for (int i = 0; i < raffles.size(); i++) {

					raffles.add(new RaffleTicket(txtExampledomaincom.getText(), account.accountID));
					PickRandomRaffle.allRaffles.add(raffles.get(i));

					Logger.LOG.info("Made Raffle Ticket Array With ID: " + raffles.get(i).ID);
					account.rafflesBought[currentShoe]++;

				}

				Logger.LOG.info("Making Payment");

				Payer Payer = new Payer();
				Payer.setPaymentMethod("paypal");

				// Redirect URLs
				RedirectUrls redirectUrls = new RedirectUrls();
				redirectUrls.setCancelUrl("https://adknate05.wixsite.com/website");
				redirectUrls.setReturnUrl("http://localhost:3000/return");

				// Set Payment Details Object
				Details details = new Details();
				details.setShipping(currencyFormat(shipping));
				details.setSubtotal(currencyFormat(totalPrice));
				details.setTax(currencyFormat(tax));

				// Set Payment amount
				Amount amount = new Amount();
				amount.setCurrency("USD");

				double finalPrice = Double.valueOf(shipping) + Double.valueOf(totalPrice) + Double.valueOf(tax);

				String priceString = String.valueOf(finalPrice);

				amount.setTotal(currencyFormat(priceString).replace("$", "").replace(",", ""));
				amount.setDetails(details);

				System.out.println("Price: " + amount.getTotal());

				// Set Transaction information
				Transaction transaction = new Transaction();
				transaction.setAmount(amount);

				transaction.setDescription("Shoe Raffle Tickets by Coppers Odds");
				List<Transaction> transactions = new ArrayList<Transaction>();
				transactions.add(transaction);

				ItemList itemList = new ItemList();
				List<Item> items = new ArrayList<Item>();

				Item item = new Item();
				if (amountofTickets == 1) {

					item.setName("1 Coppers Odds Raffle Ticket");

				}

				else {

					item.setName(amountofTickets + " Coppers Odds Raffle Tickets");

				}

				item.setCurrency("USD");
				item.setPrice(String.valueOf(price));
				items.add(item);
				itemList.setItems(items);
				transaction.setItemList(itemList);

				// Add Payment details
				Payment payment = new Payment();

				// Set Payment intent to authorize
				payment.setIntent("sale");
				payment.setPayer(Payer);
				payment.setTransactions(transactions);
				payment.setRedirectUrls(redirectUrls);

				// Pass the clientID, secret and mode. The easiest, and most widely used option.
				APIContext apiContext = new APIContext(clientID, secret, "sandbox");

				System.out.println(payment.toString());

				Payment createdPayment = null;

				try {
					createdPayment = payment.create(apiContext);
				} catch (PayPalRESTException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String redirectLink = null;

				Iterator<Links> links = createdPayment.getLinks().iterator();

				while (links.hasNext()) {

					Links link = links.next();

					if (link.getRel().equalsIgnoreCase("approval_url")) {

						redirectLink = link.getHref();

					}

				}

				currentPayments.add(createdPayment);

				try {
					Desktop.getDesktop().browse(new URI(redirectLink));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				String payerID = null;
				String paymentId = null;

				try {
					Thread.sleep(900000);
				} catch (InterruptedException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

				if (RunScript.checkApproval()) {

					payerID = payment.getPayer().getPayerInfo().getPayerId();
					paymentId = payment.getId();

					PaymentExecution paymentExecution = new PaymentExecution();
					paymentExecution.setPayerId(payerID);
					payment.setId(paymentId);

					try {
						payment.execute(apiContext, paymentExecution);
					} catch (PayPalRESTException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					Edit_JSON.edit(currentShoe, amountofTickets + Edit_JSON.getOriginal(currentShoe));

					LocalDate localDate = LocalDate.now();
					com.pulsebeat02.main.gui.windows.payment.Payment paymentFinal = new com.pulsebeat02.main.gui.windows.payment.Payment(
							"Bought Raffle Tickets", DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate),
							"Bought " + amountofTickets + " ticket(s)", "Bought with Paypal", price, null, true,
							account);

					ManagePayments.allPayments.put(paymentFinal.id, paymentFinal);

					Logger.LOG.info("Writing Files");

					try {

						String Finalpayment = payment.toString();
						String JSONFile = payment.toJSON();

						BufferedWriter writer = new BufferedWriter(new FileWriter("payment.json"));
						writer.write(JSONFile);
						writer.newLine();
						writer.write("Account ID: " + account.accountID);
						writer.close();

						BufferedWriter writer1 = new BufferedWriter(new FileWriter("totalpayment.txt"));
						writer1.write(Finalpayment);
						writer.newLine();
						writer.write("Account ID: " + account.accountID);
						writer1.close();

						Logger.LOG.info("Files Written Succesfully");

					} catch (IOException e1) {

						// TODO Auto-generated catch block

						Logger.LOG.warn("Files not Written");
						e1.printStackTrace();

					}

					String ID = UUID.randomUUID().toString();

					for (int z = 0; z < amountofTickets; z++) {

						RaffleTicket raffle = new RaffleTicket(email, ID);
						PickRandomRaffle.allRaffles.add(raffle);

					}

					if (sendEmail) {

						Logger.LOG.info("Making Email");

						Logger.LOG.info("Preparing Message");

						Properties prop = new Properties();
						prop.put("mail.smtp.host", "smtp.gmail.com");
						prop.put("mail.smtp.port", "587");
						prop.put("mail.smtp.auth", "true");
						prop.put("mail.smtp.starttls.enable", "true"); // TLS

						// get Session
						javax.mail.Session session1 = javax.mail.Session.getDefaultInstance(prop,
								new javax.mail.Authenticator() {
									protected PasswordAuthentication getPasswordAuthentication() {
										return new PasswordAuthentication("coppersodds@gmail.com", "Chatimac1$");
									}
								});

						// Compose the message
						try {

							MimeMessage message = new MimeMessage(session1);
							message.setFrom(new InternetAddress("Coppers Odds"));
							message.addRecipient(Message.RecipientType.TO, new InternetAddress(account.email));
							message.setSubject("Shoe Raffle Ticket");
							message.setText(
									"Thank You for Buying a Raffle for the Shoe Raffle. Here is your order details: "
											+ orderDetails + ". " + "Your authorization ID for this payment is: " + ID
											+ ".");

							// send the message

							Transport.send(message);

						} catch (MessagingException e1) {

							Logger.LOG.info("Messaging Error");
							e1.printStackTrace();

						}

					}

				}

				else {

					currentPayments.remove(createdPayment);
					failedPayments.add(createdPayment);

					try {
						Desktop.getDesktop().browse(new URI(redirectUrls.getReturnUrl()));
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (URISyntaxException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					String cwd = System.getProperty("user.dir");

					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(cwd + "/failedPayments"));
						writer.newLine();
						writer.write("ID: " + createdPayment.getId() + "STATE: " + createdPayment.getState()
								+ "CREATE_TIME: " + createdPayment.getCreateTime());
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					PaymentExpired.start();

				}

				Logger.LOG.info("Closing Window");
				frmBuyingTickets.setVisible(false);
				frmBuyingTickets.dispose();

			}

		});

		JButton btnClose = new JButton("Close");
		btnClose.setBounds(310, 187, 124, 23);
		frmBuyingTickets.getContentPane().add(btnClose);

		JLabel lblNewLabel_1 = new JLabel(
				"<html> We will redirect you to a website where you can accept the payment. You have 15 minutes to pay, or else, the payment will be invalid. Once done, your raffle ticket will be in.");
		lblNewLabel_1.setFont(new Font("Futura", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 127, 424, 48);
		frmBuyingTickets.getContentPane().add(lblNewLabel_1);
		btnClose.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				Logger.LOG.info("Closing Window");
				frmBuyingTickets.setVisible(false);
				frmBuyingTickets.dispose();

			}

		});
	}

	public static int indexOf(final Object[] array, final Object objectToFind, int startIndex) {
		if (array == null) {
			return 0;
		}
		if (startIndex < 0) {
			startIndex = 0;
		}
		if (objectToFind == null) {
			for (int i = startIndex; i < array.length; i++) {
				if (array[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = startIndex; i < array.length; i++) {
				if (objectToFind.equals(array[i])) {
					return i;
				}
			}
		}
		return 0;
	}

	public static String currencyFormat(String money) {

		double amount = Double.parseDouble(money);
		Locale locale = new Locale("en", "US");
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return currencyFormatter.format(amount);

	}
}
