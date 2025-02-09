package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class activity_linear extends AppCompatActivity {

    Button buttonB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear); // טעינת הפריסה

        initView(); // אתחול הכפתור

        // מציאת ה-LinearLayout שהוספנו ב-XML
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        if (mainLayout == null) {
            throw new NullPointerException("mainLayout לא נמצא! ודא שה-ID נכון ב-XML.");
        }

        // יצירת HorizontalScrollView
        HorizontalScrollView scrollView = new HorizontalScrollView(this);

        // יצירת LinearLayout לתמונות
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        // קביעת פרמטרים ל-LinearLayout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layout.setLayoutParams(layoutParams);

        // הוספת תמונות דינאמית לתוך ה-LinearLayout
        for (int i = 1; i <= 10; i++) {
            ImageView imageView = new ImageView(this);
            int resId = getResources().getIdentifier("a" + i, "drawable", getPackageName());

            if (resId != 0) { // בדיקה שהתמונה קיימת
                imageView.setImageResource(resId);

                // קביעת גודל התמונה ושוליים
                LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(
                        250, // רוחב
                        250  // גובה
                );
                imgParams.setMargins(10, 10, 10, 10);
                imageView.setLayoutParams(imgParams);

                layout.addView(imageView);
            }
        }

        // הוספת ה-LinearLayout ל-HorizontalScrollView
        scrollView.addView(layout);

        // הוספת ה-HorizontalScrollView ל-layout הראשי
        mainLayout.addView(scrollView);
    }

    private void initView() {
        buttonB = findViewById(R.id.buttonB);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_linear.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
