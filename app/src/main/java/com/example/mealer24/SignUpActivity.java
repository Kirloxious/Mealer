package com.example.mealer24;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.*;


public class SignUpActivity extends AppCompatActivity {


    private String role;

    //Database reference
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    //Layout
    private EditText signUpEmail;
    private EditText signUpPassword;
    private EditText signUpUsername;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signUpEmail = findViewById(R.id.SignUpEmail);
        signUpPassword = findViewById(R.id.SignUpPassword);
        signUpUsername = findViewById(R.id.SignUpUsername);
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
        String username = signUpUsername.getText().toString();

        //Make sure all fields filled
        if(email.isEmpty() || password.isEmpty() || username.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else{
            //Check if an account was already created using inputed username
            //Username needs to be a unique ID with no special characters
            rootNode.getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild("Cuisiniers/"+username)||snapshot.hasChild("Client/"+username)){
                        Toast.makeText(SignUpActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    }
                    //username is not used -> create account
                    else{
                        if(role.equalsIgnoreCase("cuisinier")){
                            reference = rootNode.getReference("Users/Cuisiniers");
                            //create account object and add account to Cusiniers database
                            Account newAccount = new Cuisinier(email, password, "","", "");
                            //Creates a nested node in Cuisiniers database with all of the object info
                            reference.child(username).setValue(newAccount);
                        }

                        else if(role.equalsIgnoreCase("client")){
                            reference = rootNode.getReference("Users/Clients");
                            //create account object and add account to Clients database
                            Account newAccount = new Client(email, password, "","", "");
                            //Creates a nested node in Clients database with all of the object info
                            reference.child(username).setValue(newAccount);
                        }
                        Toast.makeText(SignUpActivity.this, "User registered", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }

    private void addUserToDatabase(String role){


    }

}