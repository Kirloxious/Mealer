package com.example.mealer24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {
    private TextView sign_in_text;
    private EditText sign_in_email;
    private EditText sign_in_password;
    private Button sign_in_login_button;
    private Button sign_in_sign_up_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sign_in_text = findViewById(R.id.SignInText);
        sign_in_email = findViewById(R.id.SignInEmail);
        sign_in_password = findViewById(R.id.SignInPassword);
        sign_in_login_button = findViewById(R.id.SignInLoginButton);
        sign_in_sign_up_button = findViewById(R.id.SignInSignUpButton);

        setSignInTextAccordingToRole();
        hideSignUpButtonIfAdmin();
    }

    private void setSignInTextAccordingToRole() {
        String text = sign_in_text.getText().toString();
        String role = getIntent().getStringExtra("Role");
        sign_in_text.setText(text + " " + role);
    }

    private void hideSignUpButtonIfAdmin() {
        String role = getIntent().getStringExtra("Role");
        if(role.equalsIgnoreCase("admin")) {
            sign_in_sign_up_button.setVisibility(View.GONE);
        }
    }
}