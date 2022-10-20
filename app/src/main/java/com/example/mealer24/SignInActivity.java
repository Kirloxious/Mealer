package com.example.mealer24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
        role = getIntent().getStringExtra("Role");

        setSignInTextAccordingToRole();
        hideSignUpButtonIfAdmin();
        sign_in_login_button.setOnClickListener(this::sendToHomeScreen);
        sign_in_sign_up_button.setOnClickListener(this::sendToSignUpPage);
    }

    private void sendToHomeScreen(View view) {
        String email = sign_in_email.getText().toString();
        UTF8Encoder encodedEmail = new UTF8Encoder(email);
        String encodedEmailAsString = encodedEmail.getEncodedString();
        String password = sign_in_password.getText().toString();

        String user_path = "Users/" + transformRoleToDbPath(role) + "/" + encodedEmailAsString;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(user_path);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!snapshot.exists()) {
                    showMessage("Username or password does not exist!");
                    return;
                }

                Account user_acc = snapshot.getValue(Account.class);
                if(user_acc.getPwd().equals(password)) {
                    Intent intent = new Intent(SignInActivity.this, role.equalsIgnoreCase("cuisinier") ?
                            HomeScreenChef.class : HomeScreenClient.class);
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


    //this is a very ugly hack. We seriously need to change it. It shouldn't be that hard to fix.
    public String transformRoleToDbPath(String role) {
        if(role.equalsIgnoreCase("cuisinier")) return "Cuisiniers";
        if(role.equalsIgnoreCase("client")) return "Clients";
        if(role.equalsIgnoreCase("admin")) return "Admins";
        showMessage("This should never happen");
        return null;
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