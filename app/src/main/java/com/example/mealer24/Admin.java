package com.example.mealer24;

public class Admin extends Account {
	/**
	 * Classe Admin
	 * Admin gère les plaintes des clients
	 */
	public Admin() {
		super("admin@admin.com" , "adminpassword", null, null, null);

	}
	private void accessPlaintes() {
		//go through all cooks' plainte list.
	}
//<<<<<<< HEAD
	//add suspend (permanent/temporary) methodes? - cancel all active order from cook if any
	//add reject methode?
//=======

	//called when the admin decides to suspend a cook
	private void suspendCook(){

	}
//>>>>>>> e57f8225c6ad9f1bbdd5088da31d32a5708e08ef


}
