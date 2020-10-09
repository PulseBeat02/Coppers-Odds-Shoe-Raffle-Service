package com.pulsebeat02.shoeraffleservice.test.newpaypalsamples;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class Credentials {
	static String clientId = "CLIENT-ID";
	static String secret = "CLIENT-SECRET";

	// Creating a sandbox environment
	private static PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
			"AXI-3urGJl2pWozm5fhx3T7nvyBl_wGxOrSxohRui3X3bYWteu0YMTfTjvuYoMoxowf9ntnz_GKr968k",
			"EOMO8SjykzIYvcgupNCufLjWbni4 - sScPGvdTUTjG1E7W7ygFqrA_Xf2xb8HE7EZxdbJQLG6lvyi9xvj");

	// Creating a client for the environment
	static PayPalHttpClient client = new PayPalHttpClient(environment);
}
