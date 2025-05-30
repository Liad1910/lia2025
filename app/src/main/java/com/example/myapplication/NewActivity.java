package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewActivity extends AppCompatActivity {

    private ImageView timerImage;

    final int[] images = {
            R.drawable.number_5,
            R.drawable.number_4,
            R.drawable.number_3,
            R.drawable.number_2,
            R.drawable.number_1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new);


        timerImage = findViewById(R.id.timer_Image);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new CountDownTimer(5000, 1000) {
            int i = 0;

            public void onTick(long millisUntilFinished) {
                if (i < images.length) {
                    timerImage.setImageResource(images[i]);
                    i++;
                }
            }

            public void onFinish() {
                Intent intent = new Intent(NewActivity.this, NextActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
