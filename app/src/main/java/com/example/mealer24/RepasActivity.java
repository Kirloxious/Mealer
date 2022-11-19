package com.example.mealer24;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class RepasActivity extends AppCompatActivity {
    private TextView mesRepas_in_text;
    private ListView listeDeRepas;
    private Button buttonVoirLeMenuDuJour;
    private Button buttonRemove;
    private Button buttonAdd;
    private DatabaseReference db;
    private DatabaseReference dbRepas;
    private LinkedList<Repas> lesRepas;
    private ArrayAdapter<Repas> adapter;

    private String userEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas);

        //gets the email of active user
        userEmail = getIntent().getStringExtra("email");

        mesRepas_in_text=findViewById(R.id.texteMesRepas);
        listeDeRepas=findViewById(R.id.listeDeRepas);
        buttonAdd=findViewById(R.id.addRepas_btn);

        buttonVoirLeMenuDuJour=findViewById(R.id.repasDuJour_btn);


        //gets the path directly to all of the repas of a cuisinier
        db = Utils.getAccountDatabaseReference(Utils.CUISINIER_ROLE, userEmail).child(Utils.DB_REPAS_PATH);



        lesRepas = new LinkedList<Repas>();


        buttonAdd.setOnClickListener(this::sendToAddRepasPage);
        buttonVoirLeMenuDuJour.setOnClickListener(this::sendToAddRepasotd);

        listeDeRepas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Repas repas = lesRepas.get(i);
                showRemoveDialog(repas.getId(), repas.getNomDuRepas());
                return true;
            }
        });

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
                lesRepas.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Repas repas = dataSnapshot.getValue(Repas.class);
                    lesRepas.add(repas);

                }
                //creates adapter for our custom list view layout
                RepasList repasAdapter = new RepasList(RepasActivity.this, lesRepas);

                //attach the adapter to the repas list view
                listeDeRepas.setAdapter(repasAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    //show popup dialog box
    private void showRemoveDialog(final String repasId, String repasName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.remove_dialog, null);
        dialogBuilder.setView(dialogView);

        final Button buttonAddRepas = (Button) dialogView.findViewById(R.id.buttonAddToRepasDuJour);
        final Button buttonRemove = (Button) dialogView.findViewById(R.id.buttonRemove);

        dialogBuilder.setTitle(repasName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                removeRepas(repasId);
                displayRepas();
                b.dismiss();
            }
        });

        buttonAddRepas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRepasDuJour(repasId);
                b.dismiss();
            }
        });
    }


    private void removeRepas(String id){
        DatabaseReference repasToRemove = db.child(id);
        repasToRemove.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Repas repas = snapshot.getValue(Repas.class);
                if(!repas.getisRepasDujour()){
                    repasToRemove.removeValue();
                    Toast.makeText(RepasActivity.this, "Repas removed.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RepasActivity.this, "Cannot remove, Repas is a repas du jour.", Toast.LENGTH_SHORT).show();
                }
                displayRepas();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        
    }

    //add repas to repas du jour
    private void addRepasDuJour(String id){
        DatabaseReference repasToUpdate = db.child(id);
        repasToUpdate.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Repas repas = snapshot.getValue(Repas.class);
                repas.setisRepasDujour(true);
                repasToUpdate.updateChildren(repas.toMapRepas());
                Toast.makeText(RepasActivity.this, "Repas added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Method to redirect user to add repas page.
    public void sendToAddRepasPage(View view){

        Intent intent = new Intent(this, AddRepasActivity.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }

    public void sendToAddRepasotd(View view){

        Intent intent = new Intent(this, RepasActivityotd.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }


}