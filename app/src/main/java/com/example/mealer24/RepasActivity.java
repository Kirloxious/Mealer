package com.example.mealer24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

public class RepasActivity extends AppCompatActivity {
    private TextView mesRepas_in_text;
    private ListView listeDeRepas;
    private Button buttonAdd;
    private Button buttonRemove;
    private Button buttonVoirLeMenuDuJour;
    private DatabaseReference mDatabase;
    private LinkedList<Repas> lesRepas;
    private ArrayAdapter<Repas> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas);
        mesRepas_in_text=findViewById(R.id.texteMesRepas);
        listeDeRepas=findViewById(R.id.listeDeRepas);
        buttonAdd=findViewById(R.id.addRepas_btn);
        buttonRemove=findViewById(R.id.deleteRepas_btn);
        buttonVoirLeMenuDuJour=findViewById(R.id.repasDuJour_btn);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Cuisiniers").child("mesRepas");
        lesRepas = new LinkedList<Repas>();

        adapter = new ArrayAdapter<Repas>( this, android.R.layout.simple_list_item_1,lesRepas);
        listeDeRepas.setAdapter(adapter);


    }
    ValueEventListener repasListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
            lesRepas.clear();
            for (DataSnapshot snapshot: datasnapshot.getChildren()){

                lesRepas.add((Repas)snapshot.getValue());
            }
            adapter.notifyDataSetChanged();
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}