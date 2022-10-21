package com.example.mealer24;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
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
