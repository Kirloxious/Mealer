package com.example.mealer24.Activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer24.R;
import com.example.mealer24.Utilities.Utils;
import com.example.mealer24.model.DemandeAchat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    private DatabaseReference dbOrders;
    private String currentUserEmail;

    private List<DemandeAchat> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        currentUserEmail = getIntent().getStringExtra("email");
        dbOrders = FirebaseDatabase.getInstance().getReference(Utils.getPathFrom(Utils.DB_ORDERS_PATH));

        orderList = new LinkedList<DemandeAchat>();
    }

    private void displayRepas(){
        dbOrders.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    DemandeAchat order = dataSnapshot.getValue(DemandeAchat.class);
                    //only add orders that belong to current user
                    if(order.getClientEmail().equals(currentUserEmail)){
                        orderList.add(order);
                    }

                }
                //creates adapter for our custom list view layout


                //attach the adapter to the repas list view

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }





}