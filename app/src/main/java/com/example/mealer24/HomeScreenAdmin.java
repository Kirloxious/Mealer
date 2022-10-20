package com.example.mealer24;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreenAdmin extends HomeScreen {
    private Button complaints;
    private Button best_chefs;
    private Button last_complaints;
    private Button sus_chefs;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_admin);

        complaints = findViewById(R.id.Complaints);
        last_complaints = findViewById(R.id.LastComplaints);
        best_chefs = findViewById(R.id.BestChefs);
        sus_chefs = findViewById(R.id.SusChefs);
    }


}
