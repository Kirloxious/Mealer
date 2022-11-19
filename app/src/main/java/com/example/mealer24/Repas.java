package com.example.mealer24;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe Repas
 * Gardes les informations d'un repas(ingredients, allergies, prix, description, type de cuisines, type de repas, descriptions, nom du repas)
 * Gardes aussi le couriel du cuisinier qui prepare le repas et un status de presence si le cuisinier decide de mettre le repas sur le menu ou non.
 *
 */
public class Repas {

	//Repas class variables
	private String cuisinierEmail;
	private String nomDuRepas;
	private boolean statusPresense;
	private String typeDeRepas;
	private String typeDeCuisine;
	private String ingredients;
	private String allergies;
	private double prix;
	private String description;

	private String id;

	private boolean isRepasDujour;

	//No arg constructor for firebase use
	public Repas(){}

	//initialization methode for Repas
	public Repas(String description,String nomDuRepas, Boolean status, String typeDeRepas, String typeDeCuisine, String ingredients, String allergies, double prix) {
		this.cuisinierEmail = cuisinierEmail;

		this.nomDuRepas = nomDuRepas;
		this.statusPresense = status;
		this.typeDeRepas = typeDeRepas;
		this.typeDeCuisine = typeDeCuisine;
		this.ingredients = ingredients;
		this.allergies = allergies;
		this.prix =prix;
		this.description =description;

		this.isRepasDujour = false;

	}

	//get and set the name of a meal
	public String getNomDuRepas() {return nomDuRepas;}
	public void setNomDuRepas(String nomRepas) {nomDuRepas = nomRepas;}

	//get email of a Cuisinier
	public String getCuisinierEmail() {return cuisinierEmail;}
	public void setCuisinierEmail(String cuisinierEmail){
		this.cuisinierEmail = cuisinierEmail;
	}

	//get the status of a meal (available for order or not)
	public boolean getStatus() {return statusPresense;}

	//get and set the type of meal
	public String getTypeDeRepas() {return typeDeRepas;}
	public void setTypeDeRepas(String typeDeRepas) {this.typeDeRepas = typeDeRepas;}

	//get and set the type of cuisine of a meal
	public String getTypeDeCuisine() {return typeDeCuisine;}
	public void setTypeDeCuisine(String typeDeCuisine) {this.typeDeCuisine = typeDeCuisine;}

	//get and set the ingredients of a meal
	public String getIngredients() {return ingredients;}
	public void setIngredients(String ingredients) {this.ingredients= ingredients;}

	//get and set the description of a meal
	public String getDescription() {return description;}
	public void setDescription (String descri) {description= descri;}

	//get and set the allergens of a meal
	public String getAllergies() {return allergies;}
	public void setAllergies(String allergies) {this.allergies= allergies;}

	//get and set the price of a meal
	public double getPrix() {return prix;}
	public void setPrix(float prix) {this.prix = prix;}

	public boolean isRepasDujour() {
		return isRepasDujour;
	}

	public void setRepasDujour(boolean repasDujour) {
		isRepasDujour = repasDujour;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	//cette fonction sert à inverser le statut de présence du repas sur le menu
	public void flipStatus() {
		statusPresense = !statusPresense;
	}


	public Map<String, Object> toMapRepas(){
		HashMap<String, Object> result = new HashMap<>();
		result.put("description", description);
		result.put("nomDuRepas", nomDuRepas);
		result.put("status", false);
		result.put("typeDeRepas", typeDeRepas);
		result.put("typeDeCuisine", typeDeCuisine);
		result.put("ingredients", ingredients);
		result.put("allergies", allergies);
		result.put("prix", prix);
		result.put("id", id);


		return result;
	}
	
}
