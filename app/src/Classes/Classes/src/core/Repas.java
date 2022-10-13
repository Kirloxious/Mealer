package core;

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
	public String getNomDuRepas() {
		return nomDuRepas;
	}
	public void setNomDuRepas(String nomRepas) {
		nomDuRepas = nomRepas;
	}
	public String getCuisinierEmail() {
		return cuisinierEmail;
	}
	public void setCuisinierEmail() {
		//send to sign up
		//or
		//make sure the email isnt already exisiting before switching it
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
	
	public void flipStatus() {
		statusPresense = !statusPresense;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	
}
