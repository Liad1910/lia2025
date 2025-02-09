package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class activity_boy extends AppCompatActivity {
    CharSequence text = "Hello toast!";
    int duration = Toast.LENGTH_SHORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boy);
    }

    @Override
    protected void onStart() {
        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        super.onStop();
    }

    @Override
    protected void onPause() {
        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        super.onPause();
    }

    @Override
    protected void onResume() {
        Toast toast = Toast.makeText(this /* MyActivity */, text, duration);
        super.onResume();
    }

}