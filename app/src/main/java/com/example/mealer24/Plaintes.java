package com.example.mealer24;

/**
 * Classe Plaintes
 * Has a description of the problem filed by a Client of a meal provided by a Cuisinier
 * Allow Admin to change the status of a Cuisinier at their discretion
 */

public class Plaintes {

	//Plaintes class variables
	private Cuisinier cuisinierQuiADesPlaintes;
	private Client clientQuiAFaitLaPlainte;
	private String description;

	//initialization methode for Plaintes
	public Plaintes(Cuisinier cuisinierQuiADesPlaintes,Client clientQuiAFaitLaPlainte, String description ) {
		this.cuisinierQuiADesPlaintes = cuisinierQuiADesPlaintes;
		this.clientQuiAFaitLaPlainte =clientQuiAFaitLaPlainte;
		this.description = description;
	}

	//get the description, client and cook involved in the plaintes
	public Cuisinier getCuisinier() {
		return cuisinierQuiADesPlaintes;
	}
	public Client getClient() {
		return clientQuiAFaitLaPlainte;
	}
	public String getDescription() {
		return description;
	}

	//methode to change the status of a cuisinier from "travaille" to either "suspendu temporairement" ou "suspendu indéfiniment"
	public void changeCuisinierStatus() {
		//code to evaluate and change chef status depending on what is being used to evaluate that.
	}

}
