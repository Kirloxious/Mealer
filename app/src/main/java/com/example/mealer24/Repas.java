package com.example.mealer24;

/**
 * Classe Repas
 * Gardes les informations d'un repas(ingredients, allergies, prix, description, type de cuisines, type de repas, descriptions, nom du repas)
 * Gardes aussi le couriel du cuisinier qui prepare le repas et un status de presence si le cuisinier decide de mettre le repas sur le menu ou non.
 *
 */
public class Repas {
	private String nomDuRepas;
	private String cuisinierEmail;
	private Boolean statusPresense;
	private String typeDeRepas;
	private String typeDeCuisine;
	private String ingredients;
	private String allergies;
	private float prix;
	private String description;
	
	//Constructor
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

	//Les fonctions dans les 16 prochaine linges sont pour obtenir et changer les variables
	public String getNomDuRepas() {
		return nomDuRepas;
	}
	public void setNomDuRepas(String nomRepas) {
		nomDuRepas = nomRepas;
	}
	public String getCuisinierEmail() {
		return cuisinierEmail;
	}
	public Boolean getStatus() {
		return statusPresense;
	}
	public String getTypeDeRepas() {
		return typeDeRepas;
	}
	public void setTypeDeRepas(String typeDeRepas) {
		this.typeDeRepas = typeDeRepas;
	}
	public String getTypeDeCuisine() {
		return typeDeCuisine;
	}
	public void setTypeDeCuisine(String typeDeCuisine) {
		this.typeDeCuisine = typeDeCuisine;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients= ingredients;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription (String descri) {
		description= descri;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies= allergies;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}

	//cette fonction sert à inverser le statut de présence du repas sur le menu
	public void flipStatus() {
		statusPresense = !statusPresense;
	}


	
}
