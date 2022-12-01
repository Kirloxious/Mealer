package com.example.mealer24.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer24.R;
import com.example.mealer24.Utilities.Utils;
import com.example.mealer24.model.Cuisinier;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private TextView firstname;
    private TextView lastname;
    private TextView description;
    private TextView repasVendus;
    private TextView evaluation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        description = findViewById(R.id.description);
        repasVendus = findViewById(R.id.RepasVendus);
        evaluation = findViewById(R.id.evaluation);

        populateFields();
    }

    private void populateFields() {
        DatabaseReference db = Utils
                .getAccountDatabaseReference(Utils.CUISINIER_ROLE, getIntent().getStringExtra("email"));
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cuisinier cuisinier = snapshot.getValue(Cuisinier.class);
                firstname.setText(String.format("%s %s", firstname.getText(), cuisinier.getNom()));
                lastname.setText(String.format("%s %s", lastname.getText(), cuisinier.getNomFamille()));
                description.setText(String.format("%s %s", description.getText(), cuisinier.getDescription()));
                repasVendus.setText(String.format("%s %s", repasVendus.getText(), String.valueOf(cuisinier.getNombreRepasVendu())));
                evaluation.setText(String.format("%s %s", evaluation.getText(), cuisinier.getEvaluation()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}