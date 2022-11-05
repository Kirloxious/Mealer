package com.example.mealer24;

import java.sql.Time;
import java.util.LinkedList;

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
	private void accessPlaintes(Cuisinier unCuisinier) {
		//go through all cooks' plainte list.
		LinkedList<Plaintes> laListeDesPlaintes = unCuisinier.getCuisinierPlaintes();
		for (int i = 0;i<laListeDesPlaintes.size();i++){

		}
	}

	//methode to change the status of a Cuisinier to either permanent or temporary suspension
	private void suspendTemp(Cuisinier unChef, Time unTemps){
		//take in choice of length of suspension and apply it
		unChef.updateStatusOfCook("Suspended");
		blockCusinierFunctions();
		//while timer<time set by admin: loop? set a timer
		unChef.updateStatusOfCook("traville");
		unBlockCuisinier(unChef);
		deletePlainte();
	}
	private void suspendIndef(Cuisinier unChef){
		unChef.updateStatusOfCook("Banned");
		blockCusinierFunctions();
		deletePlainte();
	}
	private void blockCusinierFunctions(){
		//satus
		//log out the chef, they cannot log back in while their cook status is not banned
		//could put  a massive ifin cuisinier, do whatevrer you want to do.
	}
	public void unBlockCuisinier(Cuisinier unChef){
		//functions to unblock the tasks it could do>
	}

	//reject plainte
	private void deletePlainte(){
		//to implement
	}


}


