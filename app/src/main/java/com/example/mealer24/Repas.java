package com.example.mealer24;

/**
 * Classe Repas
 * Gardes les informations d'un repas(ingredients, allergies, prix, description, type de cuisines, type de repas, descriptions, nom du repas)
 * Gardes aussi le couriel du cuisinier qui prepare le repas et un status de presence si le cuisinier decide de mettre le repas sur le menu ou non.
 *
 */
public class Repas {

	//Repas class variables
	private String nomDuRepas;
	private String cuisinierEmail;
	private boolean statusPresense;
	private String typeDeRepas;
	private String typeDeCuisine;
	private String ingredients;
	private String allergies;
	private float prix;
	private String description;
	
	//initialization methode for Repas
	public Repas(String description,String nomDuRepas,String cuisinierEmail, Boolean status, String typeDeRepas, String typeDeCuisine, String ingredients, String allergies, float prix) {
		this.nomDuRepas = nomDuRepas;
		this.cuisinierEmail = cuisinierEmail;
		this.statusPresense = status;
		this.typeDeRepas = typeDeRepas;
		this.typeDeCuisine = typeDeCuisine;
		this.ingredients = ingredients;
		this.allergies = allergies;
		this.prix =prix;
		this.description =description;
	}

	//get and set the name of a meal
	public String getNomDuRepas() {return nomDuRepas;}
	public void setNomDuRepas(String nomRepas) {nomDuRepas = nomRepas;}

	//get email of a Cuisinier
	public String getCuisinierEmail() {return cuisinierEmail;}

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
	public float getPrix() {return prix;}
	public void setPrix(float prix) {this.prix = prix;}

	//cette fonction sert à inverser le statut de présence du repas sur le menu
	public void flipStatus() {
		statusPresense = !statusPresense;
	}


	
}
