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
	private String statusAchat;
	private Client clientQuiAchete;
	private Cuisinier cuisinierQuiVend;
	private Date pickUpTime;
	private Repas repasCommander;

	//Constructor
	public DemandeAchat(String statusAchat,Cuisinier cuisinierQuiVend, Client clientQuiAchete, Date pickUpTime, Repas repasCommander) {
		this.statusAchat = statusAchat;
		this.clientQuiAchete= clientQuiAchete;
		this.cuisinierQuiVend = cuisinierQuiVend;
		this.pickUpTime= pickUpTime;
		this.repasCommander= repasCommander;
	}

	//pour changer les status d'achat de repas entre: pending, completed, refused

	public void changeBuyingStatus(String status) {
		statusAchat = status;
	}
	public String getStatusDeCommande() {
		return statusAchat;
	}
	public void addPlainte(Plaintes unePlainte) {
		cuisinierQuiVend.addCuisinierPlaintes(unePlainte);
	}
	public void addEvaluation(int evaluationDonner) {
		cuisinierQuiVend.updateEvaluation(evaluationDonner);
	}
	
	public void setPickUpDate(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public Date getPickUpTime() {
		return pickUpTime;
	}
	public Repas getRepas() {
		return repasCommander;
	}
	public void setRepasCommander(Repas repasCommander) {
		this.repasCommander = repasCommander;
	}
}
