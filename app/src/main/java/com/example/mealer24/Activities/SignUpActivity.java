package com.example.mealer24.Activities;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer24.R;
import com.example.mealer24.model.Account;
import com.example.mealer24.model.Client;
import com.example.mealer24.model.CreditCard;
import com.example.mealer24.model.Cuisinier;
import com.example.mealer24.Utilities.UTF8Encoder;
import com.example.mealer24.Utilities.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This is the sign up screen for a user to create an account
 * */

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
        setContentView(com.example.mealer24.R.layout.activity_sign_up);


        initialiseLayoutVariables();

        //Hide inputs fields based on signup role
        signUpHide();

        //Set up the inputs fields for:
        //credit card info and postal code
        creditCardSetUp();
        expirationDateSetUp();
        postalCodeSignup();


        btnRegister.setOnClickListener(view -> {createAccount();});

    }

    private void createAccount() {

        rootNode = FirebaseDatabase.getInstance();

        //get encoded email to input in firebase
        String email = signUpEmail.getText().toString();
        UTF8Encoder encodedEmail = new UTF8Encoder(email);
        String encodedEmailAsString = encodedEmail.getEncodedString();

        String password = signUpPassword.getText().toString();
        String nom = signUpFirstName.getText().toString();
        String nomFamille = signUpLastName.getText().toString();

        //Address
        String streetAddress = signUpStreetAddress.getText().toString();
        String city = signUpCity.getText().toString();
        String postalCode = signUpPostal.getText().toString();
        String fullAddress = streetAddress + " " + city + ", " + postalCode;

        //Credit card
        String creditCardNbr = signUpCardNumber.getText().toString();
        String creditCardExp = signUpExpirationDate.getText().toString();
        String creditCardCVV = signUpCVV.getText().toString();
        CreditCard fullCreditCardInfo = new CreditCard(creditCardNbr, creditCardExp, creditCardCVV);

        String description = signUpUserDescription.getText().toString();


        //Make sure all fields filled
        if (email.isEmpty() || password.isEmpty() || nom.isEmpty() || nomFamille.isEmpty() || streetAddress.isEmpty() || city.isEmpty() || postalCode.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            //Check if an account was already created using input email
            //if no email exists in database, add a user
            rootNode.getReference().child(Utils.DB_USER_PATH).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String user_path_as_cuisinier = Utils.getPathFrom(Utils.DB_CUISINIER_PATH, encodedEmailAsString);
                    String user_path_as_client = Utils.getPathFrom(Utils.DB_CLIENT_PATH, encodedEmailAsString);
                    if (snapshot.hasChild(user_path_as_cuisinier) || snapshot.hasChild(user_path_as_client)) {
                        Toast.makeText(SignUpActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    }
                    //email is not used -> create account
                    else {
                        if (role.equalsIgnoreCase(Utils.CUISINIER_ROLE)) {
                            reference = rootNode.getReference(Utils.getPathFrom(Utils.DB_USER_PATH, Utils.DB_CUISINIER_PATH));
                            //create account object and add account to Cusiniers database
                            Account newAccount = new Cuisinier(email, password, nom, nomFamille, fullAddress, description, null);
                            //Creates a nested node in Cuisiniers database with all of the object info
                            reference.child(encodedEmailAsString).setValue(newAccount);
                        } else if (role.equalsIgnoreCase(Utils.CLIENT_ROLE)) {
                            reference = rootNode.getReference(Utils.getPathFrom(Utils.DB_USER_PATH, Utils.DB_CLIENT_PATH));
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
                public void onCancelled(@NonNull DatabaseError error) {}
            });

        }

    }


    private void signUpHide() {
        if (role.equalsIgnoreCase(Utils.CUISINIER_ROLE)) {
            signUpCreditCardInfotext.setVisibility(View.GONE);
            signUpCardNumber.setVisibility(View.GONE);
            signUpCVV.setVisibility(View.GONE);
            signUpExpirationDate.setVisibility(View.GONE);
        }

        if (role.equalsIgnoreCase(Utils.CLIENT_ROLE)) {
            signUpVoidCheque.setVisibility(View.GONE);
            signUpVoidChequetext.setVisibility(View.GONE);
            signUpUserDescription.setVisibility(View.GONE);
            signUpUserDescriptiontext.setVisibility(View.GONE);
        }
    }

    private void creditCardSetUp(){
    // Setup of the format for the credit Card number. This create a space each time 4 characters are inputted
        signUpCardNumber.addTextChangedListener(new TextWatcher() {

            int count =0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                int inputlength = signUpCardNumber.getText().toString().length();

                if (count <= inputlength && (inputlength == 4 || inputlength == 9 || inputlength ==14)){

                    signUpCardNumber.setText(signUpCardNumber.getText().toString()+" ");

                    int pos = signUpCardNumber.getText().length();
                    signUpCardNumber.setSelection(pos);
                }
                else if (count >= inputlength && (inputlength == 4 || inputlength == 9 || inputlength ==14)){
                    signUpCardNumber.setText(signUpCardNumber.getText().toString().substring(0, signUpCardNumber.getText().toString().length()-1));

                    int pos = signUpCardNumber.getText().length();
                    signUpCardNumber.setSelection(pos);
                }
                count = signUpCardNumber.getText().toString().length();
            }
        });
    }

    private void expirationDateSetUp(){
        // Setup of the format for the credit Card expiration Date. This create a dash inbetween Days and Month
        signUpExpirationDate.addTextChangedListener(new TextWatcher() {

            int count =0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                int inputlength = signUpExpirationDate.getText().toString().length();

                if (count <= inputlength && (inputlength == 2)){

                    signUpExpirationDate.setText(signUpExpirationDate.getText().toString()+"/");

                    int pos = signUpExpirationDate.getText().length();
                    signUpExpirationDate.setSelection(pos);
                }
                else if (count >= inputlength && (inputlength == 2)){
                    signUpExpirationDate.setText(signUpExpirationDate.getText().toString().substring(0, signUpExpirationDate.getText().toString().length()-1));

                    int pos = signUpExpirationDate.getText().length();
                    signUpExpirationDate.setSelection(pos);
                }
                count = signUpExpirationDate.getText().toString().length();
            }
        });
    }


    private void postalCodeSignup(){
        // Setup of the format for the Postal Code input. This create a space between the two 3 numbers sequences for a Canadian Postal Code.
        signUpPostal.addTextChangedListener(new TextWatcher() {

            int count =0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                int inputlength = signUpPostal.getText().toString().length();

                if (count <= inputlength && (inputlength == 3)){

                    signUpPostal.setText(signUpPostal.getText().toString()+" ");

                    int pos = signUpPostal.getText().length();
                    signUpPostal.setSelection(pos);
                }
                else if (count >= inputlength && (inputlength == 3)){
                    signUpPostal.setText(signUpPostal.getText().toString().substring(0, signUpPostal.getText().toString().length()-1));

                    int pos = signUpPostal.getText().length();
                    signUpPostal.setSelection(pos);
                }
                count = signUpPostal.getText().toString().length();
            }
        });
    }

    private void initialiseLayoutVariables(){

        //Type of account being created
        role = getIntent().getStringExtra(Utils.INTENT_EXTRA_ROLE);

        //Activity layout
        signUpEmail = findViewById(com.example.mealer24.R.id.SignUpEmail);
        signUpPassword = findViewById(com.example.mealer24.R.id.SignUpPassword);
        btnRegister = findViewById(com.example.mealer24.R.id.RegisterButton);
        signUpFirstName = findViewById(com.example.mealer24.R.id.SignUpFirstName);
        signUpLastName = findViewById(com.example.mealer24.R.id.SignUpLastName);
        signUpStreetAddress = findViewById(com.example.mealer24.R.id.SignUpStreet);
        signUpCity = findViewById(com.example.mealer24.R.id.SignUpCity);
        signUpPostal = findViewById(com.example.mealer24.R.id.SignUpPostal);
        signUpCreditCardInfotext = findViewById(com.example.mealer24.R.id.SignUpCreditCardText);
        signUpCardNumber = findViewById(com.example.mealer24.R.id.SignUpCardNumber);
        signUpExpirationDate = findViewById(com.example.mealer24.R.id.SignUpExpiration);
        signUpCVV = findViewById(com.example.mealer24.R.id.SignUpCVV);
        signUpVoidChequetext = findViewById(com.example.mealer24.R.id.SignUpVoidCheckText);
        signUpVoidCheque = findViewById(com.example.mealer24.R.id.SignUpVoidCheque);
        signUpUserDescriptiontext = findViewById(com.example.mealer24.R.id.SignUpTextInformation);
        signUpUserDescription = findViewById(R.id.SignUpInformation);
    }


}



