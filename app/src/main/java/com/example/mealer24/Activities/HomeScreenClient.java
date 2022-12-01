package com.example.mealer24.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mealer24.R;


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


        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view -> logoutUser());

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

}