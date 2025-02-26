package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class share extends AppCompatActivity {

    private EditText nameInput, foodInput;
    private Button saveButton, loadButton;
    private TextView resultText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_share);

        nameInput = findViewById(R.id.nameInput);
        foodInput = findViewById(R.id.foodInput);
        saveButton = findViewById(R.id.saveButton);
        loadButton = findViewById(R.id.loadButton);
        resultText = findViewById(R.id.resultText);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        saveButton.setOnClickListener(v -> saveData());
        loadButton.setOnClickListener(v -> loadData());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void saveData() {
        String name = nameInput.getText().toString();
        String food = foodInput.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", name);
        editor.putString("favoriteFood", food);
        editor.apply();
    }

    private void loadData() {
        String name = sharedPreferences.getString("userName", "אין שם שמור");
        String food = sharedPreferences.getString("favoriteFood", "אין אוכל שמור");
        resultText.setText("שם: " + name + "\nאוכל אהוב: " + food);
    }
}
