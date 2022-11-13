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
import java.util.Map;

public class RepasActivity extends AppCompatActivity {
    private TextView mesRepas_in_text;
    private ListView listeDeRepas;
    private Button buttonAdd;
    private Button buttonRemove;
    private Button buttonVoirLeMenuDuJour;
    private DatabaseReference db;
    private LinkedList<Repas> lesRepas;
    private ArrayAdapter<Repas> adapter;

    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas);

        userEmail = getIntent().getStringExtra("email");

        mesRepas_in_text=findViewById(R.id.texteMesRepas);
        listeDeRepas=findViewById(R.id.listeDeRepas);
        buttonAdd=findViewById(R.id.addRepas_btn);
        buttonRemove=findViewById(R.id.deleteRepas_btn);
        buttonVoirLeMenuDuJour=findViewById(R.id.repasDuJour_btn);

        db = Utils.getAccountDatabaseReference(Utils.CUISINIER_ROLE, "abc@abc.com");

        lesRepas = new LinkedList<Repas>();

        adapter = new ArrayAdapter<Repas>( this, android.R.layout.simple_list_item_1,lesRepas);
        listeDeRepas.setAdapter(adapter);


    }
    private void displayRepas(){
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




    private void addRepas(){

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cuisinier cuisinier = snapshot.getValue(Cuisinier.class);
                Repas newRepas = new Repas(cuisinier, "food", "apple", true, "apple", "", "", "", 0);
                cuisinier.addToListOfRepas(newRepas);
                Map<String, Object> repasMap = newRepas.toMapRepas();
                String key = db.child("mesRepas").push().getKey();
                db.child("mesRepas").child(key).updateChildren(repasMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }


}