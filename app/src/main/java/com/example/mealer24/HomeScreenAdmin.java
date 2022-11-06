package com.example.mealer24;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * This is the home screen for an admin account where
 * they can see the complaints and best/sus chef depending on their ratings
 * */
public class HomeScreenAdmin extends HomeScreen {
    //Firebase variable
    private DatabaseReference cuisinierDatabse;
    private Button complaints;
    private Button best_chefs;
    private Button last_complaints;
    private Button sus_chefs;
    private Button ban_cuisiniers;
    private Button dismiss_complaint;
    private ListView listOfComplainsView;

    //Array
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_admin);

        //complaints = findViewById(R.id.Complaints);
        //last_complaints = findViewById(R.id.LastComplaints);
        //best_chefs = findViewById(R.id.BestChefs);
        //sus_chefs = findViewById(R.id.SusChefs);
        ban_cuisiniers = findViewById(R.id.banCuisinier);
        dismiss_complaint = findViewById(R.id.dismissComplaint);

        ban_cuisiniers.setOnClickListener(this::sendToBanPage);
        dismiss_complaint.setOnClickListener(this::sendToBanPage);

        listOfComplainsView = findViewById(R.id.complains_list_view);

        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view -> logoutUser());

        adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1,arrayList);
        listOfComplainsView.setAdapter(adapter);

        DatabaseReference cuisinierDatabse = FirebaseDatabase.getInstance().getReference("lesPlaintes");
        cuisinierDatabse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot: datasnapshot.getChildren()){

                    arrayList.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void sendToBanPage(View view) {
        Intent intent = new Intent(this, BanActivity.class);
        startActivity(intent);
    }


}
