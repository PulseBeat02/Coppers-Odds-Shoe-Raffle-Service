package com.pulsebeat02.shoeraffleservice.testunit.paypalsamples;
/*
 * package com.test.paypalsamples;
 * 
 * import java.util.ArrayList; import java.util.List; import
 * com.paypal.api.payments.Address; import com.paypal.api.payments.Amount;
 * import com.paypal.api.payments.CreditCard; import
 * com.paypal.api.payments.Details; import
 * com.paypal.api.payments.FundingInstrument; import
 * com.paypal.api.payments.Item; import com.paypal.api.payments.ItemList; import
 * com.paypal.api.payments.Links; import com.paypal.api.payments.Payee; import
 * com.paypal.api.payments.Payer; import com.paypal.api.payments.Payment; import
 * com.paypal.api.payments.RedirectUrls; import
 * com.paypal.api.payments.Transaction;
 * 
 * public class PaymentTestCase { private String createdPaymentID = null;
 * 
 * public static final String CREATEDTIME = "2013-01-17T18:12:02.347Z";
 * 
 * public static final String CANCELURL = "http://somedomain.com";
 * 
 * public static final String RETURNURL = "http://somedomain.com";
 * 
 * public static final String INTENT = "sale";
 * 
 * public static final String EXPERIENCEPROFILEID = "XP-ABCD-1234-EFGH-5678";
 * 
 * public static final String ID = "12345";
 * 
 * public static Payment payment;
 * 
 * public static final String NAME = "Sample Item";
 * 
 * public static final String CURRENCY = "USD";
 * 
 * public static final String PRICE = "8.82";
 * 
 * public static final String TAX = "1.68";
 * 
 * public static final String QUANTITY = "5";
 * 
 * public static final String SKU = "123";
 * 
 * public static Item createItem() { Item item = new Item(); item.setName(NAME);
 * item.setCurrency(CURRENCY); item.setPrice(PRICE); item.setTax(TAX);
 * item.setQuantity(QUANTITY); item.setSku(SKU); return item; }
 * 
 * public static final String CITY = "Niagara Falls";
 * 
 * public static final String COUNTRYCODE = "US";
 * 
 * public static final String LINE1 = "3909 Witmer Road";
 * 
 * public static final String LINE2 = "Niagara Falls";
 * 
 * public static final String POSTALCODE = "14305";
 * 
 * public static final String PHONE = "716-298-1822";
 * 
 * public static final String STATE = "NY";
 * 
 * public static Address createAddress() { Address billingAddress = new
 * Address(); billingAddress.setCity(CITY);
 * billingAddress.setCountryCode(COUNTRYCODE); billingAddress.setLine1(LINE1);
 * billingAddress.setLine2(LINE2); billingAddress.setPostalCode(POSTALCODE);
 * billingAddress.setPhone(PHONE); billingAddress.setState(STATE); return
 * billingAddress; }
 * 
 * public static final String HREF = "http://sample.com";
 * 
 * public static final String METHOD = "POST";
 * 
 * public static final String REL = "authorize";
 * 
 * public static Links createLinks() { Links link = new Links();
 * link.setHref(HREF); link.setMethod(METHOD); link.setRel(REL); return link; }
 * 
 * public static final String CANCELURL1 = "http://somedomain.com";
 * 
 * public static final String RETURNURL1 = "http://somedomain.com";
 * 
 * public static RedirectUrls createRedirectUrls() { RedirectUrls redirectUrls =
 * new RedirectUrls(); redirectUrls.setCancelUrl(CANCELURL1);
 * redirectUrls.setReturnUrl(RETURNURL1); return redirectUrls; }
 * 
 * public static void main(String [] args) {
 * 
 * Payment payment = createPaymentForExecution();
 * System.out.println(payment.toString()); //System.out.println(payment.url);
 * 
 * }
 * 
 * 
 * 
 * public static Payment createCallPayment() { Address billingAddress =
 * createAddress();
 * 
 * CreditCard creditCard = new CreditCard();
 * creditCard.setBillingAddress(billingAddress); creditCard.setCvv2(617);
 * creditCard.setExpireMonth(01); creditCard.setExpireYear(2020);
 * creditCard.setFirstName("Joe"); creditCard.setLastName("Shopper");
 * creditCard.setNumber("4422009910903049"); creditCard.setType("visa");
 * 
 * 
 * ItemList itemList = new ItemList(); List<Item> items = new ArrayList<Item>();
 * items.add(createItem()); itemList.setItems(items);
 * 
 * Details amountDetails = new Details(); amountDetails.setTax("8.40");
 * amountDetails.setSubtotal("44.10"); amountDetails.setShipping("4.99");
 * 
 * Amount amount = new Amount(); amount.setDetails(amountDetails);
 * amount.setCurrency("USD"); amount.setTotal("57.49");
 * 
 * Transaction transaction = new Transaction(); transaction.setAmount(amount);
 * transaction.setItemList(itemList); transaction
 * .setDescription("This is the payment transaction description.");
 * List<Transaction> transactions = new ArrayList<Transaction>();
 * transactions.add(transaction);
 * 
 * FundingInstrument fundingInstrument = new FundingInstrument();
 * fundingInstrument.setCreditCard(creditCard); List<FundingInstrument>
 * fundingInstrumentList = new ArrayList<FundingInstrument>();
 * fundingInstrumentList.add(fundingInstrument);
 * 
 * Payer payer = new Payer();
 * payer.setFundingInstruments(fundingInstrumentList);
 * payer.setPaymentMethod("credit_card");
 * 
 * Payment payment = new Payment(); payment.setIntent("sale"); //
 * payment.setExperienceProfileId(EXPERIENCEPROFILEID); payment.setPayer(payer);
 * payment.setTransactions(transactions); return payment; }
 * 
 * public static Payment createPayment() { Address billingAddress =
 * createAddress();
 * 
 * CreditCard creditCard = new CreditCard();
 * creditCard.setBillingAddress(billingAddress); creditCard.setCvv2(874);
 * creditCard.setExpireMonth(11); creditCard.setExpireYear(2018);
 * creditCard.setFirstName("Joe"); creditCard.setLastName("Shopper");
 * creditCard.setNumber("4111111111111111"); creditCard.setType("visa");
 * 
 * Details details = new Details(); details.setShipping("10");
 * details.setSubtotal("75"); details.setTax("15");
 * 
 * Amount amount = new Amount(); amount.setCurrency("USD");
 * amount.setTotal("100"); amount.setDetails(details);
 * 
 * Payee payee = new Payee(); payee.setMerchantId("NMXBYHSEL4FEY");
 * 
 * ItemList itemList = new ItemList(); List<Item> items = new ArrayList<Item>();
 * items.add(createItem()); itemList.setItems(items);
 * 
 * Transaction transaction = new Transaction(); transaction.setAmount(amount);
 * transaction.setItemList(itemList); transaction.setPayee(payee); transaction
 * .setDescription("This is the payment transaction description.");
 * List<Transaction> transactions = new ArrayList<Transaction>();
 * transactions.add(transaction);
 * 
 * FundingInstrument fundingInstrument = new FundingInstrument();
 * fundingInstrument.setCreditCard(creditCard); List<FundingInstrument>
 * fundingInstrumentList = new ArrayList<FundingInstrument>();
 * fundingInstrumentList.add(fundingInstrument);
 * 
 * Payer payer = new Payer();
 * payer.setFundingInstruments(fundingInstrumentList);
 * payer.setPaymentMethod("credit_card");
 * 
 * List<Links> links = new ArrayList<Links>(); links.add(createLinks());
 * 
 * RedirectUrls redirectUrls = createRedirectUrls();
 * 
 * Payment payment = new Payment(); payment.setIntent("sale");
 * payment.setExperienceProfileId(EXPERIENCEPROFILEID); payment.setId(ID);
 * payment.setPayer(payer); payment.setTransactions(transactions);
 * payment.setCreateTime(CREATEDTIME); payment.setLinks(links);
 * payment.setRedirectUrls(redirectUrls); return payment; }
 * 
 * public static Payment createPaymentForExecution() { Details details = new
 * Details(); details.setShipping("10"); details.setSubtotal("75");
 * details.setTax("15");
 * 
 * Amount amount = new Amount(); amount.setCurrency("USD");
 * amount.setTotal("100"); amount.setDetails(details);
 * 
 * RedirectUrls redirectUrls = new RedirectUrls();
 * redirectUrls.setCancelUrl("http://www.hawaii.com");
 * redirectUrls.setReturnUrl("http://www.hawaii.com");
 * 
 * ItemList itemList = new ItemList(); List<Item> items = new ArrayList<Item>();
 * items.add(createItem()); itemList.setItems(items);
 * 
 * Transaction transaction = new Transaction(); transaction.setAmount(amount);
 * transaction.setItemList(itemList); transaction
 * .setDescription("This is the payment transaction description.");
 * List<Transaction> transactions = new ArrayList<Transaction>();
 * transactions.add(transaction);
 * 
 * Payer payer = new Payer(); payer.setPaymentMethod("paypal");
 * 
 * Payment payment = new Payment(); payment.setIntent("sale");
 * payment.setPayer(payer); payment.setRedirectUrls(redirectUrls);
 * payment.setTransactions(transactions); return payment; }
 * 
 * }
 */