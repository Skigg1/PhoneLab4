package com.example.lab4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMorning, btnDay, btnEvening, btnNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        btnMorning = findViewById(R.id.button1);
        btnDay = findViewById(R.id.button11);
        btnEvening = findViewById(R.id.button12);
        btnNight = findViewById(R.id.button13);
    }

    private void setupClickListeners() {
        btnMorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MorningActivity.class);
                startActivity(intent);
            }
        });

        btnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DayActivity.class);
                startActivity(intent);
            }
        });

        btnEvening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EveningActivity.class);
                startActivity(intent);
            }
        });

        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NightActivity.class);
                startActivity(intent);
            }
        });
    }
}