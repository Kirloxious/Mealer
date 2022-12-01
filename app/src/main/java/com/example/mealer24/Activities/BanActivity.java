package com.example.mealer24.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer24.model.Admin;
import com.example.mealer24.model.Cuisinier;
import com.example.mealer24.R;
import com.example.mealer24.Utilities.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class BanActivity extends AppCompatActivity {
    private EditText cuisinier_id;
    private EditText complaint_id;
    private Button ban_and_dismiss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);

        cuisinier_id = findViewById(R.id.cuisinierId);
        complaint_id = findViewById(R.id.complaintId);
        ban_and_dismiss = findViewById(R.id.banAndDismiss);

        ban_and_dismiss.setOnClickListener(this::banAndDismiss);
    }

    private void banAndDismiss(View view) {
        String cuisinier_username = cuisinier_id.getText().toString();
        String complaint_number = complaint_id.getText().toString();


        if(!cuisinier_username.isEmpty()) {
            DatabaseReference userDatabaseRef = Utils.getAccountDatabaseReference("Cuisiniers", cuisinier_username);
            userDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Cuisinier user_acc = snapshot.getValue(Cuisinier.class);
                    Admin.suspendIndef(user_acc);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        // dismiss complaint number
    }
}