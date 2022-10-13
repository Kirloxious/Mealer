package com.example.mealer24;

public class Plaintes {
	private Cuisinier cuisinierQuiADesPlaintes;
	private Client clientQuiAFaitLaPlainte;
	private String description;
	
	public Plaintes(Cuisinier cuisinierQuiADesPlaintes,Client clientQuiAFaitLaPlainte, String description ) {
		this.cuisinierQuiADesPlaintes = cuisinierQuiADesPlaintes;
		this.clientQuiAFaitLaPlainte =clientQuiAFaitLaPlainte;
		this.description = description;
	}
	//do we need set methods??
	public void setCuisinier(Cuisinier chef) {
		cuisinierQuiADesPlaintes = chef;
	}
	public void setClient(Client client) {
		clientQuiAFaitLaPlainte = client;
	}
	public void setDescription (String descri) {
		description = descri;
	}
	
	//get
	public Cuisinier getCuisinier() {
		return cuisinierQuiADesPlaintes;
	}
	public Client getClient() {
		return clientQuiAFaitLaPlainte;
	}
	
	public String getDescription() {
		return description;
	}
	public void changeCuisinierStatus() {
		//code to evaluate and change chef status depending on what is being used to evaluate that.
	}

}
