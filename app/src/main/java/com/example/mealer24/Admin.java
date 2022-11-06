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
	public static void suspendTemp(Cuisinier unChef, Time unTemps){
		//take in choice of length of suspension and apply it

		blockCusinierFunctions(unChef);
//		long startTime = System.currentTimeMillis();

//		while (System.currentTimeMillis()-startTime <unTemps){
//
//		}

		unBlockCuisinier(unChef);
		deletePlainte();
	}

	public static void suspendIndef(Cuisinier unChef){
		unChef.banCuisinier();
//		blockCusinierFunctions(unChef);
//		deletePlainte();
	}
	public static void blockCusinierFunctions(Cuisinier unChef){
		unChef.logOut();
	}
	public static void unBlockCuisinier(Cuisinier unChef){


	}

	//reject plainte
	private static void deletePlainte(){
		//to implement
	}


}


