package com.example.lab4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EveningActivity extends AppCompatActivity {

    private Button btnBack, btnNext;
    private TextView tvEveningActivities;
    private ImageView ivEvening;
    private static final String CHANNEL_ID = "evening_channel";
    private static final int NOTIFICATION_PERMISSION_CODE = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evening);

        initializeViews();
        setupEveningActivities();
        setupClickListeners();
        createNotificationChannel();

        // проверка или запрос разрешения
        checkAndSendNotification();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        tvEveningActivities = findViewById(R.id.tvEveningActivities);
        ivEvening = findViewById(R.id.ivEvening);
        ivEvening.setImageResource(R.drawable.evening);
    }

    private void setupEveningActivities() {
        String activities = "Вечерние развлечения:\n\n" +
                "• Посмотреть сериал\n" +
                "• Посмотреть фильм\n" +
                "• Погулять\n" +
                "• Почитать книгу\n" +
                "• Послушать музыку\n" +
                "• Пообщаться с друзьями\n" +
                "• Заняться хобби\n" +
                "• Приготовить ужин";
        tvEveningActivities.setText(activities);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Evening Channel",
                    NotificationManager.IMPORTANCE_HIGH // ИЗМЕНИТЕ НА HIGH
            );
            channel.setDescription("Channel for evening notifications");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void checkAndSendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // Если есть разрешение сообщение отправляется
                sendSleepNotification();
            } else {
                // Запрос разрешения
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                        NOTIFICATION_PERMISSION_CODE);
                Toast.makeText(this, "Разрешите уведомления для работы приложения", Toast.LENGTH_LONG).show();
            }
        } else {
            // Это чтука для старых версий, она просто отсылает
            sendSleepNotification();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == NOTIFICATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendSleepNotification();
            } else {
                Toast.makeText(this, "Уведомления не будут приходить без разрешения", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void sendSleepNotification() {
        try {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("ПОРА СПАТЬ! 🛌")
                    .setContentText("Не забудьте лечь спать вовремя для хорошего отдыха!")
                    .setPriority(NotificationCompat.PRIORITY_HIGH) // ИЗМЕНИТЕ НА HIGH
                    .setAutoCancel(true);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.notify(2, builder.build());

            Toast.makeText(this, "Уведомление отправлено!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Ошибка отправки уведомления", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EveningActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EveningActivity.this, NightActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}