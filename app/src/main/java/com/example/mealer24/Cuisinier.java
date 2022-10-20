package com.example.mealer24;
import java.util.LinkedList;

/***
 * Classe Cuisinier
 * décide quel repas est au menu,conserve une liste de toutes les demandes d'achat jamais reçues,
 * une liste des plaintes,
 */

public class Cuisinier extends Account {
	private String description;
	private LinkedList<Repas> mesRepas = new LinkedList<Repas>();
	private LinkedList<DemandeAchat> toutAchat = new LinkedList<DemandeAchat>();
	private float evaluation;
	private int numEvaluation;
	private LinkedList<Plaintes> cuisinierPlaintes = new LinkedList<Plaintes>();
	private int nombreRepasVendu;
	private String status = "travaille";


	public Cuisinier(String email, String pwd, String nom, String nomFamille, String address, String description) {
		super(email, pwd, nom, nomFamille, address);
		this.description = description;
		evaluation = 0;
		numEvaluation = 0;
		nombreRepasVendu = 0;
		status = "travaille";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addToListOfRepas(Repas nouveauRepas) {
		//Repas obtained from activity page.
		mesRepas.add(nouveauRepas);
	}
	public LinkedList<Repas> getListOfAllRepas() {
		return mesRepas;
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
	public void updateEvaluation(int sentEvaluation) {
		numEvaluation ++;
		evaluation = (evaluation+sentEvaluation)/numEvaluation;
		
	}
	
	public float getEvaluation() {
		return evaluation;
	}
	public void updateNombreRepasVendu() {
		nombreRepasVendu ++;
	}
	public int getNombreRepasVendu() {
		return nombreRepasVendu;
	}

	
	//update status travail, only admin should access
	public void updateStatusOfCook(String statusOfCook) {
		status = statusOfCook;
	}
	public String getStatusOfCook() {
		return status;
	}
	
	public void updateListeDeDemande(DemandeAchat uneDemande) {
		toutAchat.add(uneDemande);
	
	}
	public LinkedList<DemandeAchat> getListeDeDemande(){
		return toutAchat;
		//make it so that it returns only one
		
	}
	public void handleDemande(DemandeAchat uneDemande) {
		//code fpr handling demande
		updateListeDeDemande( uneDemande);
		
	}
	
	public LinkedList<Plaintes> getCuisinierPlaintes(){
		return cuisinierPlaintes;
	}
	public void addCuisinierPlaintes(Plaintes unePlainte) {
		cuisinierPlaintes.add(unePlainte);
	}
	
	
}
