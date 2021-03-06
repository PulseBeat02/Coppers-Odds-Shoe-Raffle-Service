<?php
    //Build the data to post back to Paypal
    $postback = 'cmd=_notify-validate'; 

    // go through each of the posted vars and add them to the postback variable
    foreach ($_POST as $key => $value) {
    $value = urlencode(stripslashes($value));
    $postback .= "&$key=$value";
    }


    // build the header string to post back to PayPal system to validate
    $header = "POST /cgi-bin/webscr HTTP/1.0\r\n";
    $header .= "Content-Type: application/x-www-form-urlencoded\r\n";
    $header .= "Content-Length: " . strlen($postback) . "\r\n\r\n";

    // Send to paypal or the sandbox depending on whether you're live or developing
    // comment out one of the following lines
    //$fp = fsockopen ('ssl://www.sandbox.paypal.com', 443, $errno, $errstr, 30);//open the connection
    $fp = fsockopen ('ssl://www.paypal.com', 443, $errno, $errstr, 30);
    // or use port 443 for an SSL connection
    //$fp = fsockopen ('ssl://www.paypal.com', 443, $errno, $errstr, 30);

    if (!$fp) 
    {
        // HTTP ERROR Failed to connect
        //error handling or email here
    }
    else // if we've connected OK
    {
        fputs ($fp, $header . $postback);//post the data back
        while (!feof($fp)) 
        {
            $response = fgets ($fp, 1024);

            if (strcmp ($response, "VERIFIED") == 0) //It's verified
            {
                // assign posted variables to local variables, apply urldecode to them all at this point as well, makes things simpler later
                $payment_status = $_POST['payment_status'];//read the payment details and the account holder

                if($payment_status == 'Completed')
                {
                    echo "Completed";
                }
                else if($payment_status == 'Denied' || $payment_status == 'Failed' || $payment_status == 'Refunded' || $payment_status == 'Reversed' || $payment_status == 'Voided')
                {
                    echo "Errored";
                }
                else if($payment_status == 'In-Progress' || $payment_status == 'Pending' || $payment_status == 'Processed')
                {
                    echo "In Progress";
                }
                else if($payment_status == 'Canceled_Reversal')
                {
                    echo "Canceled";
                }
            }
            else if (strcmp ($response, "INVALID") == 0) 
            { 
                //the Paypal response is INVALID, not VERIFIED
            }
        } //end of while
        fclose ($fp);
    }
?>