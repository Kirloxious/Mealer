package com.example.mealer24.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe DemandeAchat
  */
public class DemandeAchat {

	//DemandeAchat class variable
	private String mealId;
	private String orderId;
	private	String cookEmail;
	private String clientEmail;
	private String orderStatus;

	private final String STATUS_ATTENTE = "attente";
	private final String STATUS_APPROVED = "approved";
	private final String STATUS_REJECTED = "rejected";

	//initialization methode for DemandeAchat
	public DemandeAchat(String mealId, String cookEmail, String clientEmail) {
		this.mealId = mealId;
		this.clientEmail = clientEmail;
		this.cookEmail = cookEmail;
	}


// ************ SETTERS & GETTERS ************* //

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMealId() {
		return mealId;
	}

	public void setMealId(String mealId) {
		this.mealId = mealId;
	}

	public String getCookEmail() {
		return cookEmail;
	}

	public void setCookEmail(String cookEmail) {
		this.cookEmail = cookEmail;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus_ATTENTE(){
		this.orderStatus = STATUS_ATTENTE;
	}
	public void setOrderStatus_APPROVED(){
		this.orderStatus = STATUS_APPROVED;
	}
	public void setOrderStatus_REJECTED(){
		this.orderStatus = STATUS_REJECTED;
	}

	public Map<String, Object> toMap(){
		HashMap<String, Object> result = new HashMap<>();
		result.put("orderId", orderId);
		result.put("mealId", mealId);
		result.put("cookEmail", cookEmail);
		result.put("clientEmail", clientEmail);
		result.put("orderStatus", orderStatus);

		return result;
	}
}
