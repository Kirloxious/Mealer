package com.example.mealer24;

import com.example.mealer24.model.Client;
import com.example.mealer24.model.CreditCard;

import junit.framework.TestCase;

import org.junit.Assert;

public class ClientTest extends TestCase {

    CreditCard cci = new CreditCard("213 323 292 1324","12/25","324");
    CreditCard cci_2 = new CreditCard("128 000 292 3809","01/29","555");
    Client client = new Client("clientemail@gmail.com","Password12345",
            "John", "Doe", "17 Rue Sainte-Thérèse, Mulhouse",cci);



    public void testGetSetCreditCardInfo() {
        client.setCreditCardInfo(cci_2);
        Assert.assertEquals(cci_2, client.getCreditCardInfo());
        Assert.assertNotEquals(cci, client.getCreditCardInfo());
    }




}