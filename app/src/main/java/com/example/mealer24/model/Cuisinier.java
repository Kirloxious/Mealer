package com.example.mealer24.model;
import android.media.Image;

import com.example.mealer24.Utilities.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/***
 * Classe Cuisinier
 * décide quel repas est au menu,conserve une liste de toutes les demandes d'achat jamais reçues,
 * une liste des plaintes,son evaluation, le nombre de repas vendus et son status(si le cuisinier et temporairement suspendu ou travaille-t-il ou suspended)
 * le cuisinier a aussi une liste de plaintes que les clients ont portés sur lui
 */

public class Cuisinier extends Account {

	//Cuisinier class variables
	private String description;
	private List<Repas> mesRepas = new LinkedList<Repas>();
	private List<DemandeAchat> toutAchat = new LinkedList<DemandeAchat>();
	private float evaluation;
	private int numEvaluation;
	private List<Plaintes> cuisinierPlaintes = new LinkedList<Plaintes>();
	private int nombreRepasVendu;
	private boolean isBanned = false;
	private String statusOfCook = "travaille";
	private Image voidCheque;

	//Empty constructor
	public Cuisinier(){}

	//initialization methode for Cuisinier
	public Cuisinier(String email, String pwd, String nom, String nomFamille, String address, String description, Image voidCheque) {
		super(email, pwd, nom, nomFamille, address);
		this.description = description;
		this.voidCheque = voidCheque;
		evaluation = 0;
		numEvaluation = 0;
		nombreRepasVendu = 0;
	}

	public String getStatusOfCook() { return statusOfCook; }


	//get and set for Cuisinier's description
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	//get and set for Cuisinier's description
	public Image getVoidCheque() {
		return voidCheque;
	}
	public void setVoidCheque(Image voidcheque) {this.voidCheque = voidcheque;}


	//get and add to list of all the meals for a Cuisinier
	public List<Repas> getListOfAllRepas() {
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


	public List<Repas> getTodaysMenuOnly(){
		List<Repas> todayMenu = new LinkedList<Repas>();
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


	
	//This updates the isBanned value in the database to true
	public void banCuisinier() {
		DatabaseReference db = Utils.getAccountDatabaseReference("Cuisiniers", this.getEmail());
		this.isBanned = true;
		Map<String, Object> cusinierValues = this.toMapCuisinier();
		db.updateChildren(cusinierValues);
	}

	//This updates the isBanned value in the database to false
	public void unBanCusinier(){
		DatabaseReference db = Utils.getAccountDatabaseReference("Cuisiniers", this.getEmail());
		this.isBanned = false;
		Map<String, Object> cusinierValues = this.toMapCuisinier();
		db.updateChildren(cusinierValues);
	}

	public boolean isBanned() {
		return isBanned;
	}

	//get and add to the liste de demande for each Cuisinier
	public void updateListeDeDemande(DemandeAchat uneDemande) {
		toutAchat.add(uneDemande);
	
	}

	public List<DemandeAchat> getListeDeDemande(){
		return toutAchat;
		//make it so that it returns only one
		
	}

	//get(for admin) and add(by Client) plaintes to a Cuisinier
	public List<Plaintes> getCuisinierPlaintes(){
		return cuisinierPlaintes;
	}
	public void addCuisinierPlaintes(Plaintes unePlainte) {
		cuisinierPlaintes.add(unePlainte);
	}

	//deletes the specified meal in the list of repas (mesRepas)
	public void deleteRepas (Repas meal){
		//to implement
		if (mesRepas.contains(meal)) {
			mesRepas.remove(meal);
		}
	}




	//We need this to update values in firebase
	@Exclude
	public Map<String, Object> toMapCuisinier() {
		Map<String, Object> result = this.toMap();
		result.put("isBanned", isBanned);
		result.put("description", description);


		return result;
	}

}
