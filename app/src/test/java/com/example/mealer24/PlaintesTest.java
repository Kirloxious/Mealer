package com.example.mealer24;

import static org.junit.Assert.assertNotEquals;

import com.example.mealer24.model.Account;
import com.example.mealer24.model.Plaintes;


import junit.framework.TestCase;

import org.junit.Assert;

public class PlaintesTest extends TestCase {
    Account cuisinier = new Account();
    Account client = new Account();
    Plaintes plainte = new Plaintes(cuisinier,client,"desc.");



    public void testGetSetId() {
        plainte.setId("00121");
        Assert.assertEquals("00121", plainte.getId());
        assertNotEquals("", plainte.getId());
    }


}