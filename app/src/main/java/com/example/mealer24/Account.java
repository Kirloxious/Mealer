package com.example.mealer24;
import com.google.firebase.*;

/**
 * Class Account
 */
public class Account {
	private String email;
	private String pwd;
	private String nom;
	private String nomFamille;
	private String address;
	private Boolean logInStatus;

	//Constructor
	public Account(){};

	public Account(String email, String pwd, String nom, String nomFamille, String address) {
		this.address = address;
		this.email= email;
		this.nom = nom;
		this.nomFamille = nomFamille;
		this.pwd= pwd;
		logInStatus = false;
	}
	//Check validity of email on activity
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}
	//Check if passwords match when creating account
	public Boolean setPwd(String newPwd) {
		//check if password matches password on account. if yes return true.use this.
		return false;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNomFamille() {
		return nomFamille;
	}
	public void setNomDeFamille(String nomFamille) {
		this.nomFamille = nomFamille;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address= address;
	}
	public boolean logIn(String email, String pwd) {
		//IN ACTIVITY
		//check with firebase to see if person exist and if so check if the password matches
		//if not then redirect or suggest to create a new account
		//change login status if login successful
		return logInStatus;
	}
	public void logOut() {
		logInStatus = false;
	}




}