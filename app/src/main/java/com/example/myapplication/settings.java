package com.example.myapplication;

import android.os.Bundle;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat musicSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
}
