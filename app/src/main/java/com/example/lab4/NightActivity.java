package com.example.lab4;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class NightActivity extends AppCompatActivity {

    private Button btnSleepQuestion;
    private ImageView ivNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {

        btnSleepQuestion = findViewById(R.id.btnSleepQuestion);
        ivNight = findViewById(R.id.ivNight);


        ivNight.setImageResource(R.drawable.night);


        btnSleepQuestion.setText("");
    }

    private void setupClickListeners() {
        btnSleepQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSleepQuestionDialog();
            }
        });
    }

    private void showSleepQuestionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Вопрос")
                .setMessage("Ты спишь?")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Закрыть приложение
                        finishAffinity();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Вернуться на главный экран
                        Intent intent = new Intent(NightActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }
}