package com.example.mealer24.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer24.R;
import com.example.mealer24.model.Account;
import com.example.mealer24.model.Cuisinier;
import com.example.mealer24.Utilities.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    private TextView sign_in_text;
    private EditText sign_in_email;
    private EditText sign_in_password;
    private Button sign_in_login_button;
    private Button sign_in_sign_up_button;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sign_in_text = findViewById(R.id.SignInText);
        sign_in_email = findViewById(R.id.SignInEmail);
        sign_in_password = findViewById(R.id.SignInPassword);
        sign_in_login_button = findViewById(R.id.SignInLoginButton);
        sign_in_sign_up_button = findViewById(R.id.SignInSignUpButton);
        role = getIntent().getStringExtra(Utils.INTENT_EXTRA_ROLE);

        setSignInTextAccordingToRole();
        hideSignUpButtonIfAdmin();
        sign_in_login_button.setOnClickListener(this::sendToHomeScreen);
        sign_in_sign_up_button.setOnClickListener(this::sendToSignUpPage);
    }

    private void sendToHomeScreen(View view) {
        String email = sign_in_email.getText().toString();
        String password = sign_in_password.getText().toString();

        DatabaseReference ref = Utils.getAccountDatabaseReference(role, email);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()) {
                    showMessage("Username or password does not exist!");
                    return;
                }

                Account user_acc = snapshot.getValue(Account.class);
                if(user_acc.getPwd().equals(password)) {
                    Intent intent;
                    if(role.equalsIgnoreCase(Utils.CUISINIER_ROLE)) {
                        Cuisinier userCuisinier = snapshot.getValue(Cuisinier.class);
                        System.out.println(userCuisinier.getStatusOfCook());
                        if(!userCuisinier.getStatusOfCook().equalsIgnoreCase("travaille")) {
                            Intent ban_intent = new Intent(SignInActivity.this, BanScreen.class);
                            ban_intent.putExtra("banMessage", userCuisinier.getStatusOfCook());
                            startActivity(ban_intent);
                            return;
                        }
                        intent = new Intent(SignInActivity.this, HomeScreenChef.class);

                    }else if(role.equalsIgnoreCase(Utils.CLIENT_ROLE)) {
                        intent = new Intent(SignInActivity.this, HomeScreenClient.class);

                    }else if(role.equalsIgnoreCase(Utils.ADMIN_ROLE)) {
                        intent = new Intent(SignInActivity.this, HomeScreenAdmin.class);
                    }else throw new RuntimeException();

                    //sends email to next intent to keep track of logged in user
                    intent.putExtra("email", email);
                    showMessage("Login successful");
                    startActivity(intent);
                    return;
                }

                showMessage("Username or password does not exist");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }




    private void setSignInTextAccordingToRole() {
        String text = sign_in_text.getText().toString();
        sign_in_text.setText(text + " " + role);
    }

    private void hideSignUpButtonIfAdmin() {
        if(role.equalsIgnoreCase("admin")) {
            sign_in_sign_up_button.setVisibility(View.GONE);
        }
    }

    private void sendToSignUpPage(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        intent.putExtra("Role", role);
        startActivity(intent);
    }
}