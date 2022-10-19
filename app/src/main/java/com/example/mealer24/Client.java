package com.example.mealer24;
import java.util.LinkedList;
public class Client extends Account {




	private String creditCardInfo;
	private LinkedList<DemandeAchat>tousRepas= new LinkedList<DemandeAchat>();

	public Client(String email, String pwd, String nom, String nomFamille, String address, String cci) {
		super(email, pwd, nom, nomFamille, address);
		creditCardInfo = cci;
	}

	public LinkedList<DemandeAchat> getTousRepas() {
		return tousRepas;
	}
	public void addAncienRepas(DemandeAchat repasOrdered) {
		tousRepas.add(repasOrdered);
	}
	public String getCreditCardInfo() {
		return creditCardInfo;
	}
	public void setCreditCardInfo(String newCCI) {
		creditCardInfo = newCCI;
	}
	public void madeAPurchase(DemandeAchat purchase) {
		tousRepas.add(purchase);
		//Demande d'achat sent from activity.
	}
	//Should I add a getStatusAchat function to return the status of all current orders?
	
}
