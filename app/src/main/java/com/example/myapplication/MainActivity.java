package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private static final int START_GAME = 222;
    private static final int TERMS_REQUEST_CODE = 1;

    Button button1, button2, linear, boy, game, taknonButton;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        constraintLayout = findViewById(R.id.constraintLayout);
        game = findViewById(R.id.game);
        taknonButton = findViewById(R.id.startButton);
        linear = findViewById(R.id.linear);
        boy = findViewById(R.id.boy);


        game.setEnabled(false);


        taknonButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_taknon.class);
            startActivityForResult(intent, TERMS_REQUEST_CODE);
            game.setEnabled(true);
        });


        initViews();
    }

    private void initViews() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "you dumb", Toast.LENGTH_SHORT).show());
        button2.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "YOU SMART", Toast.LENGTH_SHORT).show());


        linear.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_linear.class);
            startActivity(intent);
        });


        boy.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_boy.class);
            startActivity(intent);
        });


        game.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_game.class);
            startActivityForResult(intent, START_GAME);
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_home) {
            Toast.makeText(this, "עברתם לדף הראשי", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.action_page1) {
            Toast.makeText(this, "עברתם לדף אחד", Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.new_item) {

            Intent intent = new Intent(this, NewActivity.class);
            startActivity(intent);
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
