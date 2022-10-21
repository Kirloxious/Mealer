package com.example.mealer24;
import java.util.LinkedList;

/**
 * Classe Client
 * Le client a une liste des demandes d'achat qu'il a fait (present ou pas)
 */
public class Client extends Account {

	// Client's class variables
	private String creditCardInfo;
	private LinkedList<DemandeAchat>tousRepas= new LinkedList<DemandeAchat>();

	//initialization methode for Client
	public Client(String email, String pwd, String nom, String nomFamille, String address, String cci) {
		super(email, pwd, nom, nomFamille, address);
		creditCardInfo = cci;
	}

	//get all the purchased meals (list of them) of a Client
	public LinkedList<DemandeAchat> getTousRepas() {
		return tousRepas;
	}

	//get and set credit card info
	public String getCreditCardInfo() {
		return creditCardInfo;
	}
	public void setCreditCardInfo(String newCCI) {
		creditCardInfo = newCCI;
	}

	//making a purchase/adding a meal to their list of purchases
	public void madeAPurchase(DemandeAchat purchase) {
		tousRepas.add(purchase);
	}

	
}
