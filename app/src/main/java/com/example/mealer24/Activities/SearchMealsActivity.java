package com.example.mealer24.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer24.R;
import com.example.mealer24.Utilities.Utils;
import com.example.mealer24.model.DemandeAchat;
import com.example.mealer24.model.Repas;
import com.example.mealer24.model.RepasList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class SearchMealsActivity extends AppCompatActivity {

    private ListView listViewOfMeals;
    private List<Repas> meals;
    private DatabaseReference dbOrders;
    private DatabaseReference dbCook;

    private String userEmail;

    //TEMP for testing placing order
    private String tempEmail = "test100@gmail.com";
    //TEMP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_meals);

        userEmail = getIntent().getStringExtra("email");

        //path to all active orders
        dbOrders = FirebaseDatabase.getInstance().getReference(Utils.getPathFrom(Utils.DB_ORDERS_PATH));

        //path to tempEmail cook's meals
        dbCook = Utils.getAccountDatabaseReference(Utils.CUISINIER_ROLE, tempEmail).child(Utils.DB_REPAS_PATH);
        meals = new LinkedList<Repas>();

        listViewOfMeals = findViewById(R.id.listOfMealsID);

        listViewOfMeals.setOnItemLongClickListener((parent, view, position, id) -> {
            Repas repas = meals.get(position);
            showPlaceOrderDialog(repas);
            return true;
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayRepas();
    }

    //currently only searching at the temp email
    private void displayRepas(){
       dbCook.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                meals.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Repas repas = dataSnapshot.getValue(Repas.class);
                    meals.add(repas);

                }
                //creates adapter for our custom list view layout
               ArrayAdapter<Repas> repasAdapter = new RepasList(SearchMealsActivity.this, meals);

                //attach the adapter to the repas list view
                listViewOfMeals.setAdapter(repasAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    //show popup dialog box with place order button
    private void showPlaceOrderDialog(Repas repas) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.demand_dialog, null);
        dialogBuilder.setView(dialogView);

        final Button buttonPlaceOrder = (Button) dialogView.findViewById(R.id.buttonPlaceOrder);


        dialogBuilder.setTitle(repas.getNomDuRepas());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonPlaceOrder.setOnClickListener(view -> {
            placeOrder(repas);
            displayRepas();
            b.dismiss();
        });

    }


    //Creates an order and sends it to database
    private void placeOrder(Repas meal){

        String id = dbOrders.push().getKey();
        DemandeAchat order = new DemandeAchat(meal.getId(), meal.getCuisinierEmail(), userEmail);
        order.setOrderId(id);
        dbOrders.child(id).updateChildren(order.toMap());
    }


}