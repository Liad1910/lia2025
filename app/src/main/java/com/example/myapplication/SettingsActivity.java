package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat musicSwitch;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // יצירת SharedPreferences
        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        musicSwitch = findViewById(R.id.music_switch);

        // טעינת מצב הכפתור האחרון
        boolean isMusicOn = sharedPreferences.getBoolean("music_state", true);
        musicSwitch.setChecked(isMusicOn);
        MainActivity.isMusicPlaying = isMusicOn;

        if (isMusicOn && MainActivity.mediaPlayer != null) {
            MainActivity.mediaPlayer.start();
        } else if (!isMusicOn && MainActivity.mediaPlayer != null) {
            MainActivity.mediaPlayer.pause();
        }

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

                // שמירת המצב של הכפתור
                editor.putBoolean("music_state", isChecked);
                editor.apply();
            }
        });

        // כפתור חזרה למיין אקטיביטי
        Button btnBackToMain = findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(v -> finish());
    }
}
