package com.example.mealer24;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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


    private Button my_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_client);

        best_chefs = findViewById(R.id.BestChefs);
        order_status = findViewById(R.id.OrderRequests);
        previous_orders = findViewById(R.id.PrevMeals);
        order_meal = findViewById(R.id.OrderMeal);


        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view -> logoutUser());

    }


}