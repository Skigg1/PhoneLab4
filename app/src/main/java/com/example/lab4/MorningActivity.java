package com.example.lab4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MorningActivity extends AppCompatActivity {

    private Button btnBack, btnNext;
    private TextView tvMorningTasks;
    private ImageView ivMorning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning);

        initializeViews();
        setupMorningTasks();
        setupClickListeners();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        tvMorningTasks = findViewById(R.id.tvMorningTasks);
        ivMorning = findViewById(R.id.ivMorning);


        ivMorning.setImageResource(R.drawable.morning);
    }

    private void setupMorningTasks() {
        String tasks = "Утренние дела:\n\n" +
                "• Проснуться и потянуться\n" +
                "• Выпить стакан воды\n" +
                "• Утренняя зарядка (15 минут)\n" +
                "• Принять контрастный душ\n" +
                "• Питательный завтрак\n" +
                "• Почистить зубы\n" +
                "• Одеться по погоде\n" +
                "• Составить план на день";

        tvMorningTasks.setText(tasks);
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Возврат на главный экран
                Intent intent = new Intent(MorningActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на день
                Intent intent = new Intent(MorningActivity.this, DayActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}