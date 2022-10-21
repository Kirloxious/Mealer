package com.example.mealer24;

/**
 * Classe Admin
 * Admin gère les plaintes des clients
 */

public class Admin extends Account {

	//initialization methode for Admin
	public Admin() {
		super("admin@admin.com" , "adminpassword", null, null, null);

	}

	/*for Admin to access all plaintes of a certain cook with the help
	of the getCuisinierPlaintes() methode in the Cuisinier class*/
	private void accessPlaintes() {
		//go through all cooks' plainte list.
	}

	//methode to change the status of a Cuisinier to either permanent or temporary suspension
	private void suspendTemp(){
		//to implement
	}
	private void suspendIndef(){
		//to implement
	}

	//reject plainte
	private void rejectPlainte(){
		//to implement
	}

<<<<<<< HEAD
<<<<<<< HEAD
	=======
	>>>>>>> 6e79e7aff71278929b35e5cfcab91ab7e3d16113
=======

	}
<<<<<<< HEAD
>>>>>>> e57f8225c6ad9f1bbdd5088da31d32a5708e08ef
=======/>>>>>>>

>>>>>>> 6e79e7aff71278929b35e5cfcab91ab7e3d16113


