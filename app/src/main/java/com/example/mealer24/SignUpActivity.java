package com.example.mealer24;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.*;




public class SignUpActivity extends AppCompatActivity {
    private String TAG = "SignUpActivity";

    private String role;

    //Database reference
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    //Layout
    private EditText signUpEmail;
    private EditText signUpPassword;
    private Button btnRegister;

    private EditText signUpFirstName;
    private EditText signUpLastName;
    private EditText signUpStreetAddress;
    private EditText signUpCity;
    private EditText signUpPostal;
    private TextView signUpCreditCardInfotext;
    private EditText signUpCardNumber;
    private EditText signUpExpirationDate;
    private EditText signUpCVV;
    private TextView signUpVoidChequetext;
    private EditText signUpVoidCheque;
    private TextView signUpUserDescriptiontext;
    private EditText signUpUserDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Type of account being created
        role = getIntent().getStringExtra("Role");

        signUpEmail = findViewById(R.id.SignUpEmail);
        signUpPassword = findViewById(R.id.SignUpPassword);
        btnRegister = findViewById(R.id.RegisterButton);
        signUpFirstName = findViewById(R.id.SignUpFirstName);
        signUpLastName = findViewById(R.id.SignUpLastName);
        signUpStreetAddress = findViewById(R.id.SignUpStreet);
        signUpCity = findViewById(R.id.SignUpCity);
        signUpPostal = findViewById(R.id.SignUpPostal);
        signUpCreditCardInfotext = findViewById(R.id.SignUpCreditCardText);
        signUpCardNumber = findViewById(R.id.SignUpCardNumber);
        signUpExpirationDate = findViewById(R.id.SignUpExpiration);
        signUpCVV = findViewById(R.id.SignUpCVV);
        signUpVoidChequetext = findViewById(R.id.SignUpVoidCheckText);
        signUpVoidCheque = findViewById(R.id.SignUpVoidCheque);
        signUpUserDescriptiontext = findViewById(R.id.SignUpTextInformation);
        signUpUserDescription = findViewById(R.id.SignUpInformation);



        SignUpHide();

        btnRegister.setOnClickListener(view -> {createAccount();});


    }

    private void createAccount(){

        //Get database instance
        rootNode = FirebaseDatabase.getInstance();


        //Get inputs as strings
        String email = signUpEmail.getText().toString();
        //get encoded email to input in firebase
        UTF8Encoder encodedEmail = new UTF8Encoder(email);
        String encodedEmailAsString = encodedEmail.getEncodedString();
        String password = signUpPassword.getText().toString();
        String nom = signUpFirstName.getText().toString();
        String nomFamille = signUpLastName.getText().toString();
        String streetAddress = signUpStreetAddress.getText().toString();
        String city = signUpCity.getText().toString();
        String postalCode = signUpPostal.getText().toString();
        //make full address
        String fullAddress = "";
        String creditCardNbr = signUpCardNumber.getText().toString();
        String creditCardExp = signUpExpirationDate.getText().toString();
        String creditCardCVV = signUpCVV.getText().toString();
        //maybe have credit card class to store credit card info?
        String fullCreditCardInfo = "";
        String description = signUpUserDescription.getText().toString();


        //Make sure all fields filled
        if(email.isEmpty() || password.isEmpty() || nom.isEmpty() || nomFamille.isEmpty() || streetAddress.isEmpty() || city.isEmpty() || postalCode.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else{
            //Check if an account was already created using input email
            //if no email exists in database, add a user
            rootNode.getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild("Cuisiniers/"+encodedEmailAsString)||snapshot.hasChild("Clients/"+encodedEmailAsString)){
                        Toast.makeText(SignUpActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    }
                    //email is not used -> create account
                    else{
                        if(role.equalsIgnoreCase("cuisinier")){
                            reference = rootNode.getReference("Users/Cuisiniers");
                            //create account object and add account to Cusiniers database
                            Account newAccount = new Cuisinier(email, password, nom,nomFamille, fullAddress, description);
                            //Creates a nested node in Cuisiniers database with all of the object info
                            reference.child(encodedEmailAsString).setValue(newAccount);
                        }

                        else if(role.equalsIgnoreCase("client")){
                            reference = rootNode.getReference("Users/Clients");
                            //create account object and add account to Clients database
                            Account newAccount = new Client(email, password, nom, nomFamille, fullAddress, fullCreditCardInfo);
                            //Creates a nested node in Clients database with all of the object info
                            reference.child(encodedEmailAsString).setValue(newAccount);
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



    private void SignUpHide(){
        if(role.equalsIgnoreCase("cuisinier")){
            signUpCreditCardInfotext.setVisibility(View.GONE);
            signUpCardNumber.setVisibility(View.GONE);
            signUpCVV.setVisibility(View.GONE);
            signUpExpirationDate.setVisibility(View.GONE);
        }
        if (role.equalsIgnoreCase("client")){
            signUpVoidCheque.setVisibility(View.GONE);
            signUpVoidChequetext.setVisibility(View.GONE);
            signUpUserDescription.setVisibility(View.GONE);
            signUpUserDescriptiontext.setVisibility(View.GONE);
        }
    }
}



