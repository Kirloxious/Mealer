package com.example.mealer24.Activities;

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

import com.example.mealer24.R;
import com.example.mealer24.model.Repas;
import com.example.mealer24.model.RepasList;
import com.example.mealer24.Utilities.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
/**
 * This is the screen for a cuisinier account where
 * they can make changes to the status of a repas to remove it
 * from the "list" of repas du jour and see all their current list of repas du jour.
 **/
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

        listeDeRepasOTD.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Repas repas = lesRepasOtd.get(i);
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

    //show popup dialog box
    private void showRemoveDialog(final String repasId, String repasName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.remove_dialog, null);
        dialogBuilder.setView(dialogView);

        Button buttonAddRepas = (Button) dialogView.findViewById(R.id.buttonAddToRepasDuJour);
        buttonAddRepas.setVisibility(View.GONE);

        final Button buttonRemove = (Button) dialogView.findViewById(R.id.buttonRemove);

        dialogBuilder.setTitle(repasName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeRepas(repasId);
                displayRepas();
                b.dismiss();
            }
        });
    }

    private void displayRepas(){
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lesRepasOtd.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    //go through each repas and add to display list
                    Repas leRepas = dataSnapshot.getValue(Repas.class);
                    if (leRepas.getisRepasDujour()){
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

    //removes the specified repas from the "list" of repas du jour but changing the status of it
    private void removeRepas(String id){
        DatabaseReference repasToRemove = db.child(id);
        repasToRemove.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Repas repas = snapshot.getValue(Repas.class);
                repas.setisRepasDujour(false);
                repasToRemove.updateChildren(repas.toMapRepas());
                displayRepas();
                Toast.makeText(RepasActivityotd.this, "Repas removed from repas du jour.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }


}