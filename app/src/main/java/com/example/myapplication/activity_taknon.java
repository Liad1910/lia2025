package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_taknon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taknon);

        // אתחול רכיבים
        CheckBox agreeCheckbox = findViewById(R.id.agreeCheckbox);
        Button submitButton = findViewById(R.id.submitButton);

        // האזנה לשינויים בצ'קבוקס
        agreeCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            submitButton.setEnabled(isChecked); // מאפשר/מבטל את הכפתור
        });

        // האזנה ללחיצה על הכפתור
        submitButton.setOnClickListener(v -> {
            Toast.makeText(this, "Thank you for agreeing to the terms!🐲", Toast.LENGTH_SHORT).show();

            // החזרת תוצאה ל-MainActivity
            setResult(RESULT_OK);
            finish(); // סיום הפעילות וחזרה ל-MainActivity
        });
    }
}


