package com.example.mealer24.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer24.R;
import com.example.mealer24.Utilities.Utils;
import com.example.mealer24.model.DemandeAchat;
import com.example.mealer24.model.OrderLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class OrderRequestActivity extends AppCompatActivity {

    private ListView listViewOrderRequest;
    private DatabaseReference dbOrders;
    private List<DemandeAchat> listOrdersRequest;

    private String currentUserEmail;
    private boolean is_client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_request);

        //gets the email of active user
        currentUserEmail = getIntent().getStringExtra("email");

        listViewOrderRequest= findViewById(R.id.listViewAllOrderRequests);

        dbOrders = FirebaseDatabase.getInstance().getReference(Utils.getPathFrom(Utils.DB_ORDERS_PATH));

        listOrdersRequest = new LinkedList<>();

        listViewOrderRequest.setOnItemLongClickListener((adapterView, view, i, l) -> {
            DemandeAchat order = listOrdersRequest.get(i);
            showUpdateStatusDialog(order.getOrderId());
            return true;
        });
        is_client = getIntent().getStringExtra(Utils.INTENT_EXTRA_ROLE).equalsIgnoreCase(Utils.CLIENT_ROLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayOrders();
    }


    private void displayOrders(){
        dbOrders.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listOrdersRequest.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    DemandeAchat order = dataSnapshot.getValue(DemandeAchat.class);
                    assert order != null;
                    //only add to view if order belong to the cook
                    if(!is_client && order.getCookEmail().equals(currentUserEmail)){
                        listOrdersRequest.add(order);
                    }

                    if(is_client && order.getClientEmail().equals(currentUserEmail)) {
                        listOrdersRequest.add(order);
                    }
                }
                //creates adapter for our custom list view layout
                OrderLayout orderLayoutAdapter = new OrderLayout(OrderRequestActivity.this, listOrdersRequest);

                //attach the adapter to the order list view
                listViewOrderRequest.setAdapter(orderLayoutAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    //pop up with change status of order button
    private void showUpdateStatusDialog(final String orderId) {
        if(is_client) return;

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.setstatus_dialog, null);
        dialogBuilder.setView(dialogView);

        final Button buttonStatusApproved = (Button) dialogView.findViewById(R.id.btnStatusApprove);
        final Button buttonStatusRejected = (Button) dialogView.findViewById(R.id.btnStatusReject);

        dialogBuilder.setTitle(orderId);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonStatusApproved.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateStatus(orderId, DemandeAchat.STATUS_APPROVED);
                b.dismiss();
            }
        });

        buttonStatusRejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStatus(orderId, DemandeAchat.STATUS_REJECTED);
                b.dismiss();
            }
        });
    }
    //update status of order
    private void updateStatus(String id, String updatedStatus){
        if(is_client) return;
        DatabaseReference orderRef = dbOrders.child(id);
        orderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DemandeAchat order = snapshot.getValue(DemandeAchat.class);
                order.setOrderStatus(updatedStatus);
                orderRef.updateChildren(order.toMap());
                displayOrders();
                Toast.makeText(OrderRequestActivity.this, "Order updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}
