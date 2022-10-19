package com.example.mealer24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private TextView welcome_text;
    private Button sign_in_as_cuisinier;
    private Button sign_in_as_client;
    private Button sign_in_as_admin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome_text = findViewById(R.id.WelcomeText);
        sign_in_as_cuisinier = findViewById(R.id.SignInAsCuisinier);
        sign_in_as_client = findViewById(R.id.SignInAsClient);
        sign_in_as_admin = findViewById(R.id.SignInAsAdmin);

        sign_in_as_client.setOnClickListener(this::SendToSignInPage);
        sign_in_as_admin.setOnClickListener(this::SendToSignInPage);
        sign_in_as_cuisinier.setOnClickListener(this::SendToSignInPage);

    }

    public void SendToSignInPage(View view) {
        int id = view.getId();
        Button button_clicked = findViewById(id);

        Intent intent = new Intent(this, SignInActivity.class);
        intent.putExtra("Role", button_clicked.getText().toString());
        startActivity(intent);
    }


}