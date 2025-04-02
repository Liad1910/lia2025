package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat musicSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // כפתור חזרה למיין אקטיביטי
        Button btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // מסיים את SettingsActivity כדי לא לצבור מסכים פתוחים
        });

        // חץ חזרה בסרגל העליון
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        musicSwitch = findViewById(R.id.music_switch);
        musicSwitch.setChecked(MainActivity.isMusicPlaying);

        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.isMusicPlaying = isChecked;
                if (isChecked) {
                    if (MainActivity.mediaPlayer != null) {
                        MainActivity.mediaPlayer.start();
                    }
                } else {
                    if (MainActivity.mediaPlayer != null) {
                        MainActivity.mediaPlayer.pause();
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // סוגר את SettingsActivity וחוזר אחורה
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}