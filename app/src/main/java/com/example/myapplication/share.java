package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class share extends AppCompatActivity {

    private EditText nameInput, foodInput;
    private Button saveButton, loadButton, clearButton;
    private TextView resultText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        nameInput = findViewById(R.id.nameInput);
        foodInput = findViewById(R.id.foodInput);
        saveButton = findViewById(R.id.saveButton);
        loadButton = findViewById(R.id.loadButton);
        clearButton = findViewById(R.id.clearButton);
        resultText = findViewById(R.id.resultText);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        saveButton.setOnClickListener(v -> saveData());
        loadButton.setOnClickListener(v -> loadData());
        clearButton.setOnClickListener(v -> clearData());

        // טוען נתונים שהוזנו בעבר אם קיימים
        loadData();
    }

    private void saveData() {
        String name = nameInput.getText().toString();
        String food = foodInput.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", name);
        editor.putString("favoriteFood", food);
        editor.apply();

        resultText.setText("נתונים נשמרו בהצלחה!");
    }

    private void loadData() {
        String name = sharedPreferences.getString("userName", "אין שם שמור");
        String food = sharedPreferences.getString("favoriteFood", "אין אוכל שמור");
        resultText.setText("שם: " + name + "\nאוכל אהוב: " + food);
    }

    private void clearData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        nameInput.setText("");
        foodInput.setText("");
        resultText.setText("הנתונים נמחקו!");
    }
}
