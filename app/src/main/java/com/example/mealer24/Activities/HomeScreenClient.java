package com.example.mealer24.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;

import com.example.mealer24.R;
import com.example.mealer24.Utilities.Utils;
import com.example.mealer24.model.Cuisinier;
import com.example.mealer24.model.CuisinierEtRepasInfo;
import com.example.mealer24.model.Repas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the home screen for a client account where
 * they can see the best chef depending on their ratings,
 * their previous orders, the order status of their active (no status by chef) orders
 * and where they can order and search for meals
 * */

public class HomeScreenClient extends HomeScreen {
    private Button order_meal;
    private Button best_chefs;
    private Button order_status;
    private Button previous_orders;
    private ArrayList<CuisinierEtRepasInfo> displayListChoice;
    private ArrayList<Cuisinier> listDeCuisinier;
    private ListView viewListOfMeals;
    private DatabaseReference cuisinierDatabase;

    private String userEmail;
    private Button my_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_client);
        userEmail = getIntent().getStringExtra("email");
        best_chefs = findViewById(R.id.profile);
        order_status = findViewById(R.id.OrderRequests);
        order_status.setOnClickListener(this::sendToViewOrdersPage);
        previous_orders = findViewById(R.id.PrevMeals);

        order_meal = findViewById(R.id.OrderMeal);
        order_meal.setOnClickListener(this::sendToSearchMealsPage);

        my_orders = findViewById(R.id.MyMeals);
        my_orders.setOnClickListener(this::sendToOrderRequestPage);

        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view -> logoutUser());

        displayListChoice = new ArrayList<CuisinierEtRepasInfo>();
        listDeCuisinier = new ArrayList<Cuisinier>();
        viewListOfMeals = findViewById(R.id.listOfMealsId);
        cuisinierDatabase = FirebaseDatabase.getInstance().getReference("Users").child("Cuisiniers");
        putSetAllCuisinierInfo();

    }

    /**
     * Following 3 methods send the client user towards the requested page based
     * on the page the user selected (order request page, meal searching page, view order page).
     * */
    public void sendToOrderRequestPage(View view) {
        Intent intent = new Intent(this, OrderRequestActivity.class);
        intent.putExtra(Utils.INTENT_EXTRA_ROLE, Utils.CLIENT_ROLE);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }

    public void sendToSearchMealsPage(View view){
        Intent intent = new Intent(this, SearchMealsActivity.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }

    public void sendToViewOrdersPage(View view){
        Intent intent = new Intent(this, OrdersActivity.class );
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }

    //method to search the database for all meals corresponding relatively to the user's input into the search bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mealmenu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)menuItem.getActionView();

        searchView.setQueryHint("Que voulez-vous manger");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //called when the user types in text and press enter
            public boolean onQueryTextSubmit(String query) {
                displayListChoice.clear();
                //for each cuisinier, go through each repas.
                for (Cuisinier i:listDeCuisinier){
                    List<Repas> listOfRepas = i.getListOfAllRepas();
                    //find query through nom de repas, type de repas, type de cuisinie
                    for (Repas j:listOfRepas){
                        if (j.getNomDuRepas().equalsIgnoreCase(query) || j.getTypeDeCuisine().equalsIgnoreCase(query)||j.getTypeDeRepas().equalsIgnoreCase(query)){
                            CuisinierEtRepasInfo addThisToList = new CuisinierEtRepasInfo(j,i);
                            displayListChoice.add(addThisToList);
                        }
                    }
                }
                // put adapter for the display
                //non
                MealLayout mealAdapter = new MealLayout(HomeScreenClient.this, displayListChoice);
                //attach the adapter to the repas list view
                viewListOfMeals.setAdapter(mealAdapter);
                return false;
            }
            //call when user is typing on ever change
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        }) ;
        return super.onCreateOptionsMenu(menu);
    }

    //gets all the cooks that are not banned (working) and their info from the database
    public void putSetAllCuisinierInfo() {
        cuisinierDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDeCuisinier.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Cuisinier currentCuisinier = dataSnapshot.getValue(Cuisinier.class);

                    //bringing all active cuisisinier locally
                    if (currentCuisinier.getStatusOfCook()=="Travaille"){
                        listDeCuisinier.add(currentCuisinier);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}