package com.example.mealer24;

import static org.junit.Assert.assertNotEquals;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class AccountTest {

    //account variable to test
    Account account = new Account("email@email.com", "pass", "nom", "nomFamille", "address");

    //testing the variable "email" for the account class
    @Test
    public void testGetSetEmail() {
        account.setEmail("anotherEmail@gmail.com");
        Assert.assertEquals("anotherEmail@gmail.com", account.getEmail());
        assertNotEquals("email@email.com", account.getEmail());
    }

    //testing the variable "pwd"(password) for the account class
    @Test
    public void testGetSetPwd() {
        account.setPwd("differentPass");
        Assert.assertEquals("differentPass", account.getPwd());
        assertNotEquals("pass", account.getPwd());

    }

    //testing the variable "nom" for the account class
    @Test
    public void testGetSetNom() {
        account.setNom("Alex");
        Assert.assertEquals("Alex", account.getNom());
        assertNotEquals("nom", account.getNom());
    }

    //testing the variable "nomFamille" for the account class
    @Test
    public void testGetSetNomFamille() {
        account.setNomDeFamille("Ringuette");
        Assert.assertEquals("Ringuette", account.getNomFamille());
        assertNotEquals("nomFamille", account.getNomFamille());
    }

    //testing the variable "address" for the account class
    @Test
    public void testGetSetAddress() {
        account.setAddress("differentAddress");
        Assert.assertEquals("differentAddress", account.getAddress());
        assertNotEquals("address", account.getAddress());

    }

    //testing the variable "logInStatus" for the account class
    @Test
    public void testGetSetLogInStatus() {
        Assert.assertFalse(account.getLogInStatus());
        account.setLogInStatus(true);
        Assert.assertTrue(account.getLogInStatus());
    }

    //testing if the cook can no longer long in when they are banned
    @Test
    public void testBanLogInCook() {
    //to implement
    }

}