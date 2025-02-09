package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private static final int START_GAME = 222;
    private static final int TERMS_REQUEST_CODE = 1;

    Button button1, button2, linear, boy, game, taknonButton;
    Switch switch1;
    ConstraintLayout constraintLayout;
    ImageView imageView, imageView1;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // אתחול רכיבים
        constraintLayout = findViewById(R.id.constraintLayout);
        game = findViewById(R.id.game);
        taknonButton = findViewById(R.id.startButton);
        linear = findViewById(R.id.linear); // נוספו!
        boy = findViewById(R.id.boy); // נוספו!

        // השבתת כפתור המשחק כברירת מחדל
        game.setEnabled(false);

        // הגדרת האזנה ללחיצה על כפתור התקנון
        taknonButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_taknon.class);
            startActivityForResult(intent, TERMS_REQUEST_CODE);
            game.setEnabled(true);
        });

        // אתחול הכפתורים הנוספים
        initViews();
    }

    private void initViews() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "you dumb", Toast.LENGTH_SHORT).show());
        button2.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "YOU SMART", Toast.LENGTH_SHORT).show());

        // מעבר ל-activity של ה-Girl
        linear.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_linear.class);
            startActivity(intent);
        });

        // מעבר ל-activity של ה-Boy
        boy.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_boy.class);
            startActivity(intent);
        });

        // מעבר ל-activity של המשחק
        game.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_game.class);
            startActivityForResult(intent, START_GAME);
        });
    }
}
