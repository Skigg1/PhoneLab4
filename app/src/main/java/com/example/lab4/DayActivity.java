package com.example.lab4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {

    private Button btnBack, btnNext;
    private TextView tvDayTasks;
    private ImageView ivDay;
    private static final String CHANNEL_ID = "day_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        initializeViews();
        setupDayTasks();
        setupClickListeners();
        createNotificationChannel();
        sendEndOfWorkdayNotification();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        tvDayTasks = findViewById(R.id.tvDayTasks);
        ivDay = findViewById(R.id.ivDay);
        ivDay.setImageResource(R.drawable.day);
    }

    private void setupDayTasks() {
        String tasks = "Дневные дела:\n\n" +
                "• Работа/Учеба\n" +
                "• Обед\n" +
                "• Тихий час/Отдых\n" +
                "• Дневная зарядка\n" +
                "• Прогулка на свежем воздухе\n" +
                "• Выполнение текущих задач\n" +
                "• Встречи и звонки";
        tvDayTasks.setText(tasks);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Day Channel";
            String description = "Channel for day notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendEndOfWorkdayNotification() {
        try {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Скоро конец рабочего дня")
                    .setContentText("Завершайте дела и готовьтесь к вечеру!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.notify(1, builder.build());
        } catch (SecurityException e) {
        }
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DayActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DayActivity.this, EveningActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}