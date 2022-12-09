package com.example.mealer24;

import static org.junit.Assert.assertNotEquals;

import com.example.mealer24.model.DemandeAchat;

import junit.framework.TestCase;

import org.junit.Assert;

import java.util.HashMap;


public class DemandeAchatTest extends TestCase {
    DemandeAchat demandeAchat = new DemandeAchat("432342","cook@cook.com","client@client.com");
    HashMap<String, Object> result = new HashMap<>();



    public void testGetSetMealId() {
        demandeAchat.setOrderId("334324");
        Assert.assertEquals("334324", demandeAchat.getOrderId());
        assertNotEquals("432342", demandeAchat.getOrderId());
    }



    public void testGetSetCookEmail() {
        demandeAchat.setCookEmail("cook_2@cook2.com");
        Assert.assertEquals("cook_2@cook2.com", demandeAchat.getCookEmail());
        assertNotEquals("cook@cook.com", demandeAchat.getCookEmail());
    }


    public void testGetSetClientEmail() {
        demandeAchat.setCookEmail("client_2@client2.com");
        Assert.assertEquals("client_2@client2.com", demandeAchat.getCookEmail());
        assertNotEquals("client@client.com", demandeAchat.getCookEmail());
    }



}