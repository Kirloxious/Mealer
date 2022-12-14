package com.example.mealer24.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer24.model.Account;
import com.example.mealer24.model.Admin;
import com.example.mealer24.R;
import com.example.mealer24.Utilities.UTF8Encoder;
import com.example.mealer24.Utilities.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * This is the first screen for all users of the app.
 * This is a page to direct them to a sigh up page of their corresponding account type (Admin, Client, Cuisinier)
 * */
public class MainActivity extends AppCompatActivity {
    private TextView welcome_text;
    private Button sign_in_as_cuisinier;
    private Button sign_in_as_client;
    private Button sign_in_as_admin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.mealer24.R.layout.activity_main);

        welcome_text = findViewById(com.example.mealer24.R.id.WelcomeText);
        sign_in_as_cuisinier = findViewById(com.example.mealer24.R.id.SignInAsCuisinier);
        sign_in_as_client = findViewById(com.example.mealer24.R.id.SignInAsClient);
        sign_in_as_admin = findViewById(R.id.SignInAsAdmin);

        //sends the user to the desired account type to sign in
        sign_in_as_client.setOnClickListener(this::SendToSignInPage);
        sign_in_as_admin.setOnClickListener(this::SendToSignInPage);
        sign_in_as_cuisinier.setOnClickListener(this::SendToSignInPage);


        test(); //ignore

//      createAdminLogin();

    }

    //methode to send the user to the appropriate page depending on the click of the button
    public void SendToSignInPage(View view) {
        int id = view.getId();
        Button button_clicked = findViewById(id);

        Intent intent = new Intent(this, SignInActivity.class);
        intent.putExtra(Utils.INTENT_EXTRA_ROLE, button_clicked.getText().toString());
        startActivity(intent);
    }

    //creation of admin account
    private void createAdminLogin(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users/Admin");
        Account admin = new Admin();
        UTF8Encoder encodedEmail = new UTF8Encoder(admin.getEmail());
        String encodedEmailAsString = encodedEmail.getEncodedString();
        ref.child(encodedEmailAsString).setValue(admin);

    }

    //test firebase stuff, ignore
    private void test(){

    }



}