package com.example.mealer24.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mealer24.R;
import com.example.mealer24.Utilities.Utils;

/**
 * This is the home screen for a chefs account where
 * they can see the best chef depending on their ratings
 * and prepare meals and see all their order request as well as their previous meals
 * */
public class HomeScreenChef extends HomeScreen {
    private Button prepare_meal;
    private Button profile;
    private Button order_requests;
    private Button previous_meals;

    private Button my_meals;

    private String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_chef);

        //gets the email of active user
        userEmail = getIntent().getStringExtra("email");

        profile = findViewById(R.id.profile);
        order_requests = findViewById(R.id.OrderRequests);
        previous_meals = findViewById(R.id.PrevMeals);
        prepare_meal = findViewById(R.id.Meal);
        my_meals = findViewById(R.id.MyMeals);

        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view -> logoutUser());

        order_requests.setOnClickListener(this::sendToOrderRequestPage);
        my_meals.setOnClickListener(this::sendToRepasPage);
        profile.setOnClickListener(this::sendToProfilePage);
    }



    public void sendToRepasPage(View view){
        Intent intent = new Intent(this, RepasActivity.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }

    public void sendToProfilePage(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }

    public void sendToOrderRequestPage(View view){
        Intent intent = new Intent(this, OrderRequestActivity.class);
        intent.putExtra("email", userEmail);
        intent.putExtra(Utils.INTENT_EXTRA_ROLE, Utils.CUISINIER_ROLE);
        startActivity(intent);
    }

}
