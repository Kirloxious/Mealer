package com.example.mealer24;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
	private String plainteId;

	//initialization methode for Plaintes
	public Plaintes(Account cuisinierQuiADesPlaintes,Account clientQuiAFaitLaPlainte, String description) {
		this.cuisinierQuiADesPlaintes = (Cuisinier) cuisinierQuiADesPlaintes;
		this.clientQuiAFaitLaPlainte = (Client) clientQuiAFaitLaPlainte;
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

	public void setId(String id){
		this.plainteId = id;
	}

	//methode to change the status of a cuisinier from "travaille" to either "suspendu temporairement" ou "suspendu indéfiniment"
	public void changeCuisinierStatus() {
		//code to evaluate and change chef status depending on what is being used to evaluate that.
	}

	//adds the plaint to the database
	public void addPlaintToDatabase(){
		//Database
		DatabaseReference cuisinierDatabase = FirebaseDatabase.getInstance().getReference("Plaintes");
		//create account object and add account to Clients database
		String id = cuisinierDatabase.push().getKey();
		Plaintes plaintes = new Plaintes(this.cuisinierQuiADesPlaintes, this.clientQuiAFaitLaPlainte, this.description);
		plaintes.setId(id);
		//Creates a nested node in Clients database with all of the object info
		cuisinierDatabase.child(id).setValue(plaintes);
	}

}
