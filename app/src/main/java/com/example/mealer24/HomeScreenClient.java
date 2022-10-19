package com.example.mealer24;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreenClient extends AppCompatActivity {
    private Button order_meal;
    private Button best_chefs;
    private Button order_status;
    private Button previous_orders;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_client);

        best_chefs = findViewById(R.id.BestChefs);
        order_status = findViewById(R.id.OrderRequests);
        previous_orders = findViewById(R.id.PrevMeals);
        order_meal = findViewById(R.id.OrderMeal);
    }
}