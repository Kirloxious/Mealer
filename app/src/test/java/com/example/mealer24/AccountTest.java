package com.example.mealer24;

import static org.junit.Assert.assertNotEquals;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class AccountTest {

    Account account = new Account("email@email.com", "pass", "nom", "nomFamille", "address");

    @Test
    public void testGetSetEmail() {
        account.setEmail("anotherEmail@gmail.com");
        Assert.assertEquals("anotherEmail@gmail.com", account.getEmail());
        assertNotEquals("email@email.com", account.getEmail());
    }

    @Test
    public void testGetSetPwd() {
        account.setPwd("differentPass");
        Assert.assertEquals("differentPass", account.getPwd());
        assertNotEquals("pass", account.getPwd());

    }

    @Test
    public void testGetSetNom() {
        account.setNom("Alex");
        Assert.assertEquals("Alex", account.getNom());
        assertNotEquals("nom", account.getNom());
    }

    @Test
    public void testGetSetNomFamille() {
        account.setNomDeFamille("Ringuette");
        Assert.assertEquals("Ringuette", account.getNomFamille());
        assertNotEquals("nomFamille", account.getNomFamille());
    }

    @Test
    public void testGetSetAddress() {
        account.setAddress("differentAddress");
        Assert.assertEquals("differentAddress", account.getAddress());
        assertNotEquals("address", account.getAddress());

    }

    @Test
    public void testGetSetLogInStatus() {
        Assert.assertFalse(account.getLogInStatus());
        account.setLogInStatus(true);
        Assert.assertTrue(account.getLogInStatus());
    }


}