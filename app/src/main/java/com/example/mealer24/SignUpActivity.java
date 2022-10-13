package com.example.mealer24;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.*;


public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    private String role;

    //Database reference
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    //Layout
    private EditText signUpEmail;
    private EditText signUpPassword;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signUpEmail = findViewById(R.id.SignUpEmail);
        signUpPassword = findViewById(R.id.SignUpPassword);
        btnRegister = findViewById(R.id.RegisterButton);

        btnRegister.setOnClickListener(view -> {createAccount();});

    }

    private void createAccount(){

        //Get database instance
        rootNode = FirebaseDatabase.getInstance();


        //Type of account being created
        role = getIntent().getStringExtra("Role");

        //Get email & password inputs
        String email = signUpEmail.getText().toString();
        String password = signUpPassword.getText().toString();

        if(role.equalsIgnoreCase("cuisinier")){
            reference = rootNode.getReference("Users/Cuisiniers");
            //create account object and add account to Cusiniers database
            Account newAccount = new Cuisinier(email, password, "","", "");
            //Creates a nested child in Cuisiniers database with all of the object info
            reference.child("aCuisinier").setValue(newAccount);

        }

        else if(role.equalsIgnoreCase("client")){
            reference = rootNode.getReference("Users/Clients");
            //create account object and add account to Clients database
            Account newAccount = new Client(email, password, "","", "");
            //Creates a nested child in Clients database with all of the object info
            reference.child("aClient").setValue(newAccount);
        }

    }

}