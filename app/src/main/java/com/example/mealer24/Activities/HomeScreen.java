package com.example.mealer24.Activities;

import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public abstract class HomeScreen extends AppCompatActivity {
    private Button home;
    private Button browse;
    private Button settings;

    public Button logoutBtn;


    //sends user back to MainActivity screen
    public void logoutUser(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
