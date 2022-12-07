package com.example.mealer24.model;

import android.widget.ArrayAdapter;

import com.example.mealer24.model.Cuisinier;
import com.example.mealer24.model.Repas;

//class to contain the combination of infos of both the cook and meal

public class CuisinierEtRepasInfo {
    //initializing variables
    private String nomCuisinier;
    private String cuisinierAddress;
    private String cuisinierDescription;
    private String repasNom;
    private Double prix;
    private String typeDeRepas;
    private String typeDeCuisine;
    private String listeIngredients;
    private String listeAllergies;
    private String descriptionRepas;

    //Constructor
    public CuisinierEtRepasInfo(Repas meal, Cuisinier cook){

        nomCuisinier = cook.getNom();
        cuisinierAddress = cook.getAddress();
        cuisinierDescription = cook.getDescription();
        repasNom = meal.getNomDuRepas();
        prix = meal.getPrix();
        typeDeRepas = meal.getTypeDeRepas();
        typeDeCuisine = meal.getTypeDeCuisine();
        listeIngredients = meal.getIngredients();
        listeAllergies = meal.getAllergies();
        descriptionRepas = meal.getDescription();
    }

    //get methods for all the info
    public String getNomCuisinier() {
        return nomCuisinier;
    }

    public String getRepasNom() {
        return repasNom;
    }

    public Double getLePrix() {
        return prix;
    }

    public String getTypeDeCuisine() {
        return typeDeCuisine;
    }

    public String getTypeDeRepas() {
        return typeDeRepas;
    }

    public String getListeIngredients() {
        return listeIngredients;
    }

    public String getListeAllergies() {
        return listeAllergies;
    }

    public String getDescriptionRepas() {
        return descriptionRepas;
    }

    public String getCuisinierDescription() {
        return cuisinierDescription;
    }

    public String getCuisinierAddress() {
        return cuisinierAddress;
    }
}
