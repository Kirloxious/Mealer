package com.example.mealer24;

import java.util.Date;

public class DemandeAchat {
	private String statusAchat;
	private Client clientQuiAchete;
	private Cuisinier cuisinierQuiVend;
	private Date pickUpTime;
	private Repas repasCommander;
	
	public DemandeAchat(String statusAchat,Cuisinier cuisinierQuiVend, Client clientQuiAchete, Date pickUpTime, Repas repasCommander) {
		this.statusAchat = statusAchat;
		this.clientQuiAchete= clientQuiAchete;
		this.cuisinierQuiVend = cuisinierQuiVend;
		this.pickUpTime= pickUpTime;
		this.repasCommander= repasCommander;
	}
	public void changeBuyingSatus(String status) {
		//pending, refused, completed
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
