package com.liad.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button button;
    Button button3;
    TextView pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button3 = findViewById(R.id.button3);
        pre = findViewById(R.id.pre);

    }

    public void lia(View view) {
        if (view == button) {
            pre.setText("button");
            Log.d("Lia", "button1");
        } else if (view == button3) {
            pre.setText("button3");
            Log.d("Lia", "button2");


        }
    }
}