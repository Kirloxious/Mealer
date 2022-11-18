package com.example.mealer24;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.LinkedList;

public class RepasActivityotd extends AppCompatActivity {
    private TextView repasTitreOTD;
    private ListView listeDeRepasOTD;
    private Button removeRepasbtn;

    private DatabaseReference db;
    private DatabaseReference dbRepasOTD;
    private LinkedList<Repas> lesRepasOtd;
    private ArrayAdapter<Repas> adapterOtd;
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
        //db = Utils.getAccountDatabaseReference(Utils.CUISINIER_ROLE, userEmail).child(Utils.DB_REPAS_PATH);

    }
}