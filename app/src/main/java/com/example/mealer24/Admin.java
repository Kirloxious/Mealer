package com.example.mealer24;

public class Admin extends Account {
	/**
	 * Classe Admin
	 * Admin gère les plaintes des clients
	 */
	public Admin() {
		super("admin@admin.com" , "adminpassword", null, null, null);

	}
	public void accessPlaintes() {
		//go through all cooks' plainte list.
	}
	//add suspend (permanent/temporary) methodes? - cancel all active order from if any
	//add reject methode?


}
