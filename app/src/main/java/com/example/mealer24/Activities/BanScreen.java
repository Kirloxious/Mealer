package com.example.mealer24.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealer24.R;

public class BanScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_screen);

        TextView view = findViewById(R.id.banMessage);
        String ban_message = "You have been banned " + getIntent().getStringExtra("banMessage");
        view.setText(ban_message);
    }
}