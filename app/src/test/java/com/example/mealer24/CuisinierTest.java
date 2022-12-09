package com.example.mealer24;

import static org.junit.Assert.assertNotEquals;

import android.media.Image;

import com.example.mealer24.model.Cuisinier;

import junit.framework.TestCase;

import org.junit.Assert;


public class CuisinierTest extends TestCase {

    Image voidCheque ;
    Cuisinier cuisinier = new Cuisinier("chef@chef.com","1324","John","Doe","90 Templeton K45 1G5","cuisinier desc.",voidCheque);


    public void testGetSetDescription() {
        cuisinier.setDescription("other desc.");
        Assert.assertEquals("other desc.", cuisinier.getDescription());
        assertNotEquals("cuisinier desc.", cuisinier.getDescription());
    }




}