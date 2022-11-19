package com.example.mealer24;

import static org.junit.Assert.assertNotEquals;

import junit.framework.TestCase;

import org.junit.Assert;

public class RepasTest extends TestCase {

    Repas repas = new Repas("burrito desc.","burrito",true,"non-vegetarian","mexican","beans, chicken...","none",19, false);

    public void testGetSetNomDuRepas() {
        repas.setNomDuRepas("taco");
        Assert.assertEquals("taco", repas.getNomDuRepas());
        assertNotEquals("burrito", repas.getNomDuRepas());

    }


    public void testGetStatus() {
        repas.setNomDuRepas("taco");
        Assert.assertEquals("taco", repas.getNomDuRepas());
        assertNotEquals("burrito", repas.getNomDuRepas());

    }

    public void testGetSetTypeDeRepas() {
        repas.setTypeDeRepas("vegetarian");
        Assert.assertEquals("vegetarian", repas.getTypeDeRepas());
        assertNotEquals("non-vegetarian", repas.getTypeDeRepas());

    }

    public void testGetSetTypeDeCuisine() {
        repas.setTypeDeCuisine("italian");
        Assert.assertEquals("italian", repas.getTypeDeCuisine());
        assertNotEquals("mexican", repas.getTypeDeCuisine());

    }

    public void testGetSetIngredients() {
        repas.setIngredients("steak");
        Assert.assertEquals("steak", repas.getIngredients());
        assertNotEquals("beans, chicken...", repas.getIngredients());
    }

    public void testGetSetDescription() {
        repas.setDescription("another desc.");
        Assert.assertEquals("another desc.", repas.getDescription());
        assertNotEquals("burrito desc.", repas.getDescription());


    }

    public void testGetSetAllergies() {
        repas.setAllergies("peanut");
        Assert.assertEquals("peanut", repas.getAllergies());
        assertNotEquals("none", repas.getAllergies());

    }





}