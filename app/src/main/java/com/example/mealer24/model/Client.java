package com.example.mealer24.model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;

/**
 * Classe Client
 * Le client a une liste des demandes d'achat qu'il a fait (present ou pas)
 */
public class Client extends Account {

	// Client's class variables
	private CreditCard creditCardInfo;
	private LinkedList<DemandeAchat>tousRepas= new LinkedList<DemandeAchat>();
	private Cuisinier cuisinier;
	private Client client;
	private String description;
	private String plainteId;

	//Empty constructor
	public Client(){}

	//initialization methode for Client
	public Client(String email, String pwd, String nom, String nomFamille, String address, CreditCard cci) {
		super(email, pwd, nom, nomFamille, address);
		creditCardInfo = cci;
	}

	//get all the purchased meals (list of them) of a Client
	public LinkedList<DemandeAchat> getTousRepas() {
		return tousRepas;
	}

	//get and set credit card info
	//uses getters and setters from CreditCard class
	public CreditCard getCreditCardInfo() {
		return creditCardInfo;
	}
	public void setCreditCardInfo(CreditCard newCCI) {
		creditCardInfo = newCCI;
	}

	//making a purchase/adding a meal to their list of purchases
	public void madeAPurchase(DemandeAchat purchase) {
		tousRepas.add(purchase);
	}

	public void deposePlainte(Account cuisinier, Account client, String description){
		this.cuisinier = (Cuisinier) cuisinier;
		this.client = (Client) client;
		this.description = description;
		Plaintes plainte = new Plaintes(cuisinier,client,description);
		addPlaintToDatabase();
	}

	//adds the plaint to the database
	public void addPlaintToDatabase(){
		//Database
		DatabaseReference cuisinierDatabase = FirebaseDatabase.getInstance().getReference("Plaintes");
		//create account object and add account to Clients database
		String id = cuisinierDatabase.push().getKey();
		Plaintes plaintes = new Plaintes(this.cuisinier, this.client, this.description);
		plaintes.setId(id);
		//Creates a nested node in Clients database with all of the object info
		cuisinierDatabase.child(id).setValue(plaintes);
	}

	
}
