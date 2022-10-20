package com.example.mealer24;
import java.util.LinkedList;

/**
 * Classe Client
 * Le client a une liste des demandes d'achat qu'il a fait (present ou pas)
 */
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

	public String getCreditCardInfo() {
		return creditCardInfo;
	}
	public void setCreditCardInfo(String newCCI) {
		creditCardInfo = newCCI;
	}
	public void madeAPurchase(DemandeAchat purchase) {
		tousRepas.add(purchase);
	}

	
}
