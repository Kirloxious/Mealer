package com.example.mealer24;

import static org.junit.Assert.assertNotEquals;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;

public class AccountTest extends TestCase {

    @Rule
    Account account = new Account("email@email.com", "pass", "nom", "nomFamille", "address");

    @Test
    public void testGetSetEmail() {
        account.setEmail("anotherEmail@gmail.com");
        assertEquals("anotherEmail@gmail.com", account.getEmail());
        assertNotEquals("email@email.com", account.getEmail());
    }

    @Test
    public void testGetSetPwd() {
        account.setPwd("differentPass");
        assertEquals("differentPass", account.getPwd());
        assertNotEquals("pass", account.getPwd());

    }

    @Test
    public void testGetSetNom() {
        account.setNom("Alex");
        assertEquals("Alex", account.getNom());
        assertNotEquals("nom", account.getNom());
    }

    @Test
    public void testGetSetNomFamille() {
        account.setNomDeFamille("Ringuette");
        assertEquals("Ringuette", account.getNomFamille());
        assertNotEquals("nomFamille", account.getNomFamille());
    }

    @Test
    public void testGetSetAddress() {
        account.setAddress("differentAddress");
        assertEquals("differentAddress", account.getAddress());
        assertNotEquals("address", account.getAddress());

    }

    @Test
    public void testGetSetLogInStatus() {
        assertFalse(account.getLogInStatus());
        account.setLogInStatus(true);
        assertTrue(account.getLogInStatus());
    }


}