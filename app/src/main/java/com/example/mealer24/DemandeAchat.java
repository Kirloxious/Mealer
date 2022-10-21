package com.example.mealer24;

import java.util.Date;
/**
 * Classe DemandeAchat
 * Garde les informations appartenant pour une demande d'achat
 * pre-condition: Les valeurs donnée sont valides
 * Elle garde les valeurs de statusAchat, quel client qui fait l'acht et de quel cuisinier,
 * l'heure ou l'achat est demandé et quel repas commander dans cette achat
  */
public class DemandeAchat {

	//DemandeAchat class variable
	private String statusAchat;
	private Client clientQuiAchete;
	private Cuisinier cuisinierQuiVend;
	private Date pickUpTime;
	private Repas repasCommander;

	//initialization methode for DemandeAchat
	public DemandeAchat(String statusAchat,Cuisinier cuisinierQuiVend, Client clientQuiAchete, Date pickUpTime, Repas repasCommander) {
		this.statusAchat = statusAchat;
		this.clientQuiAchete= clientQuiAchete;
		this.cuisinierQuiVend = cuisinierQuiVend;
		this.pickUpTime= pickUpTime;
		this.repasCommander= repasCommander;
	}

	//pour changer et get le status d'achat de repas entre: pending, completed, refused
	public void changeStatusDeCommande(String status) {
		statusAchat = status;
	}
	public String getStatusDeCommande() {
		return statusAchat;
	}

	//create a Plainte in the list of Plaintes of the cook (by client)
	public void addPlainte(Plaintes unePlainte) {
		cuisinierQuiVend.addCuisinierPlaintes(unePlainte);
	}

	//adds an evaluation to a cook
	public void addEvaluation(int evaluationDonner) {
		cuisinierQuiVend.updateEvaluation(evaluationDonner);
	}

	//set and get a time and date for pick-up of an order
	public void setPickUpTime(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public Date getPickUpTime() {
		return pickUpTime;
	}

	//set and get the ordered meal
	public Repas getRepasCommander() {
		return repasCommander;
	}
	public void setRepasCommander(Repas repasCommander) {
		this.repasCommander = repasCommander;
	}
}
