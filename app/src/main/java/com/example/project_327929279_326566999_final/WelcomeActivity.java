package com.example.project_327929279_326566999_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // בדיקת התחברות
        SharedPreferences prefs = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        if (userId != -1) {
            // המשתמש כבר מחובר -> מעבר ישיר למסך הראשי
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        // המשתמש לא מחובר -> המשך למסך ברוך הבא
        setContentView(R.layout.activity_welcome);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
