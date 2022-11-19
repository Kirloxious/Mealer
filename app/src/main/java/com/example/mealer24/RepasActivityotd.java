package com.example.mealer24;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class RepasActivityotd extends AppCompatActivity {
    private TextView repasTitreOTD;
    private ListView listeDeRepasOTD;
    private Button removeRepasbtn;

    private DatabaseReference db;
    private DatabaseReference dbRepasOTD;
    private LinkedList<Repas> lesRepasOtd;
    private ArrayAdapter<Repas> adapterOtd;

    private LinkedList<Repas> allChefRepas;
    private String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas_activityotd);

        //gets the email of active user
        userEmail = getIntent().getStringExtra("email");
        repasTitreOTD = findViewById(R.id.titreId);
        listeDeRepasOTD = findViewById(R.id.listeRepasDuJourId);
        removeRepasbtn = findViewById(R.id.btnDelFromOtd);

        //gets the path directly to all of the repas of a cuisinier
        db = Utils.getAccountDatabaseReference(Utils.CUISINIER_ROLE, userEmail).child(Utils.DB_REPAS_PATH);

        lesRepasOtd = new LinkedList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayRepas();
    }

    private void displayRepas(){
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lesRepasOtd.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    //go through each repas and add to display list
                    Repas leRepas = dataSnapshot.getValue(Repas.class);
                    if (leRepas.isRepasDujour()){
                        lesRepasOtd.add(leRepas);
                    }

                }
                //creates adapter for our custom list view layout
                RepasList repasAdapter = new RepasList(RepasActivityotd.this, lesRepasOtd);

                //attach the adapter to the repas list view
                listeDeRepasOTD.setAdapter(repasAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

}