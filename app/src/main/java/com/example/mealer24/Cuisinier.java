package com.example.mealer24;
import android.media.Image;
import java.util.LinkedList;

/***
 * Classe Cuisinier
 * décide quel repas est au menu,conserve une liste de toutes les demandes d'achat jamais reçues,
 * une liste des plaintes,son evaluation, le nombre de repas vendus et son status(si le cuisinier et temporairement suspendu ou travaille-t-il ou suspended)
 * le cuisinier a aussi une liste de plaintes que les clients ont portés sur lui
 */

 // if cuisinier permanently suspended, make sure can't login

public class Cuisinier extends Account {

	//Cuisinier class variables
	private String description;
	private LinkedList<Repas> mesRepas = new LinkedList<Repas>();
	private LinkedList<DemandeAchat> toutAchat = new LinkedList<DemandeAchat>();
	private float evaluation;
	private int numEvaluation;
	private LinkedList<Plaintes> cuisinierPlaintes = new LinkedList<Plaintes>();
	private int nombreRepasVendu;
	private String status = "travaille";
	private Image voidCheque;

	//initialization methode for Cuisinier
	public Cuisinier(String email, String pwd, String nom, String nomFamille, String address, String description, Image voidCheque) {
		super(email, pwd, nom, nomFamille, address);
		this.description = description;
		this.voidCheque = voidCheque;
		evaluation = 0;
		numEvaluation = 0;
		nombreRepasVendu = 0;
		status = "travaille";
	}

	//get and set for Cuisinier's description
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	//get and add to list of all the meals for a Cuisinier
	public LinkedList<Repas> getListOfAllRepas() {
		return mesRepas;
	}
	public void addToListOfRepas(Repas nouveauRepas) {
		//Repas obtained from activity page.
		mesRepas.add(nouveauRepas);
	}

	//get and update the total number of sold meals
	public void updateNombreRepasVendu() {
		nombreRepasVendu ++;
	}
	public int getNombreRepasVendu() {
		return nombreRepasVendu;
	}


	public LinkedList<Repas> getTodaysMenuOnly(){
		LinkedList<Repas> todayMenu = new LinkedList<Repas>();
		for (int i =0; i<mesRepas.size();i++) {
			if (mesRepas.get(i).getStatus()==true) {
				todayMenu.add(mesRepas.get(i));
			}
		}
		return todayMenu;
	}

	// get and update note d'évaluation from and for Client
	public void updateEvaluation(int sentEvaluation) {
		numEvaluation ++;
		evaluation = (evaluation+sentEvaluation)/numEvaluation;
		
	}
	public float getEvaluation() {
		return evaluation;
	}


	
	//get and update status travail, only admin will use this methode
	public void updateStatusOfCook(String statusOfCook) {
		status = statusOfCook;
	}
	public String getStatusOfCook() {
		return status;
	}

	//get and add to the liste de demande for each Cuisinier
	public void updateListeDeDemande(DemandeAchat uneDemande) {
		toutAchat.add(uneDemande);
	
	}
	public LinkedList<DemandeAchat> getListeDeDemande(){
		return toutAchat;
		//make it so that it returns only one
		
	}

	//get(for admin) and add(by Client) plaintes to a Cuisinier
	public LinkedList<Plaintes> getCuisinierPlaintes(){
		return cuisinierPlaintes;
	}
	public void addCuisinierPlaintes(Plaintes unePlainte) {
		cuisinierPlaintes.add(unePlainte);
	}

	//deletes the specified meal in the list of repas (mesRepas)
	public void deleteRepas (Repas meal){
		//to implement
	}
}
