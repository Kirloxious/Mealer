package com.example.mealer24.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        String cuisinier_username = cuisinier_id.getText().toString().split(",")[0];
        String cuisinier_duration = cuisinier_id.getText().toString().split(",")[1];
        String complaint_number = complaint_id.getText().toString();


        if(!cuisinier_username.isEmpty()) {
            DatabaseReference userDatabaseRef = Utils.getAccountDatabaseReference(Utils.CUISINIER_ROLE, cuisinier_username);
            userDatabaseRef.child("statusOfCook").setValue(cuisinier_duration);
            Intent intent = new Intent(this, HomeScreenAdmin.class);
            startActivity(intent);
//            userDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Cuisinier user_acc = snapshot.getValue(Cuisinier.class);
//                    Admin.suspendIndef(user_acc);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
        }
        else {
            Toast.makeText(this, "No cuisinier with that username", Toast.LENGTH_SHORT).show();
        }

        // dismiss complaint number
    }
}