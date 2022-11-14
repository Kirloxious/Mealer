package com.example.mealer24;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the home screen for a chefs account where
 * they can see the best chef depending on their ratings
 * and prepare meals and see all their order request as well as their previous meals
 * */
public class HomeScreenChef extends HomeScreen {
    private Button prepare_meal;
    private Button best_chefs;
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

        best_chefs = findViewById(R.id.BestChefs);
        order_requests = findViewById(R.id.OrderRequests);
        previous_meals = findViewById(R.id.PrevMeals);
        prepare_meal = findViewById(R.id.Meal);
        my_meals = findViewById(R.id.MyMeals);

        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view -> logoutUser());

        my_meals.setOnClickListener(this::sendToRepasPage);

    }


    public void showRepas(){
        //so do I put this or do I put an onlisten to button mesRepas that opens a new activity??


    }
    public void sendToRepasPage(View view){

        Intent intent = new Intent(this, RepasActivity.class);
        intent.putExtra("email", userEmail);
        startActivity(intent);
    }

}
