package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class activity_game extends AppCompatActivity {

    CharSequence text = "Hello toast!";
    int duration = Toast.LENGTH_SHORT;

/*    int result;
    int guessCount = 0;

    static int getRandomNumber(int max, int min) {
        return (int) ((Math.random() * (max - min + 1)) + min);
    }

    public void makeToast(String str) {
        Toast.makeText(activity_game.this, str, Toast.LENGTH_SHORT).show();
    }

    public void startGame(View view) {

        EditText variable = (EditText) findViewById(R.id.num1);
        EditText variable1 = (EditText) findViewById(R.id.num2);

        String minStr = variable.getText().toString();
        String maxStr = variable1.getText().toString();

        if (minStr.isEmpty() || maxStr.isEmpty()) {
            makeToast("Please enter both minimum and maximum values!");
            return;
        }

        int min1 = Integer.parseInt(minStr);
        int max = Integer.parseInt(maxStr);

        if (min1 >= max) {
            makeToast("Minimum number should be less than Maximum number!");
        } else {
            result = getRandomNumber(max, min1);
            guessCount = 0;
            makeToast("Game Started! Try guessing the number.");

        }
    }

    public void clickFunction(View view) {


        EditText variable = (EditText) findViewById(R.id.editId);
        String guessStr = variable.getText().toString();

        if (guessStr.isEmpty()) {
            makeToast("Please enter a guess!");
            return;
        }

        int userGuessing = Integer.parseInt(guessStr);
        guessCount++;

        if (userGuessing < result) {
            makeToast("Think of a Higher Number, Try Again");
        } else if (userGuessing > result) {
            makeToast("Think of a Lower Number, Try Again");
        } else {
            makeToast("Congratulations, You Got the Number!"+"the guss count is"+guessCount);
        }


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



    }*/
int result;
    int guessCount = 0;

    RadioButton radioButton1, radioButton2, radioButton3;
    RadioGroup radioGroup;

    static int getRandomNumber(int max, int min) {
        return (int) ((Math.random() * (max - min + 1)) + min);
    }

    public void makeToast(String str) {
        Toast.makeText(activity_game.this, str, Toast.LENGTH_SHORT).show();
    }

    // פונקציה להתחלת המשחק
    public void startGame(View view) {
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioGroup = findViewById(R.id.radioGroup);

        // אם המשתמש לא בחר ברדיו בטן, יוצג לו Toast להודיע לו לבחור
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            makeToast("Please select a difficulty level.");
            return;
        }

        // אתחול מחדש לכל הפעמים
        guessCount = 0;

        if (radioButton1.isChecked()) {
            int min1 = 1;
            int max = 10;
            result = getRandomNumber(max, min1);
            makeToast("Game Started! Try guessing the number between 1 and 10.level:easy👶");
        } else if (radioButton2.isChecked()) {
            int min1 = 1;
            int max = 50;
            result = getRandomNumber(max, min1);
            makeToast("Game Started! Try guessing the number between 1 and 50.level:medium👧");
        } else if (radioButton3.isChecked()) {
            int min1 = 1;
            int max = 100;
            result = getRandomNumber(max, min1);
            makeToast("Game Started! Try guessing the number between 1 and 100.level:hard👩");
        }
    }

    // פונקציה לבדיקת הניחוש
    public void clickFunction(View view) {
        EditText variable = findViewById(R.id.editId);
        String guessStr = variable.getText().toString();

        if (guessStr.isEmpty()) {
            makeToast("Please enter a guess!🤩");
            return;
        }

        int userGuessing = Integer.parseInt(guessStr);
        guessCount++;

        if (userGuessing < result) {
            makeToast("Think of a Higher Number, Try Again⬆⬆⬆⬆");
        } else if (userGuessing > result) {
            makeToast("Think of a Lower Number, Try Again⬇⬇⬇⬇⬇");
        } else {
            makeToast("Congratulations, You Got the Number!💫💫 The guess count is " + guessCount);
            Intent result = new Intent();
            result.putExtra("num_guesses",guessCount);
            result.putExtra("user_name","lia");
            setResult(RESULT_OK,result);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioGroup = findViewById(R.id.radioGroup);

        // במידה ולא נבחר רדיו בטן, נוודא שנבחר ברדיו בטן ברירת מחדל
        radioButton1.setChecked(true);
    }

    @Override
    protected void onStart() {
        Toast.makeText(this, "START", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "DESTROY", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "STOP", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this, "resume", Toast.LENGTH_SHORT).show();
        super.onResume();
    }
}

