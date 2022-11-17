package com.example.mealer24;
import android.content.Intent;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Account
 */

public class Account{

	private String email;
	private String pwd;
	private String nom;
	private String nomFamille;
	private String address;
	private boolean logInStatus;

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
	public void setPwd(String newPwd) {
		pwd = newPwd;
	}

	public String getNom() { return nom; }
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
	public boolean getLogInStatus() {
		return logInStatus;
	}
	public void setLogInStatus(boolean status){this.logInStatus = status;}
	public void logOut() {
		logInStatus = false;
	}


	//We need this to update values in firebase
	@Exclude
	public Map<String, Object> toMap() {
		HashMap<String, Object> result = new HashMap<>();
		result.put("email", email);
		result.put("pwd", pwd);
		result.put("nom", nom);
		result.put("nomFamille", nomFamille);
		result.put("address", address);

		return result;
	}

}