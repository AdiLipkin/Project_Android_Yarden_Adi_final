package com.example.project_327929279_326566999_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_327929279_326566999_final.data.UserRepository;
import com.example.project_327929279_326566999_final.data.entities.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btnRegister = findViewById(R.id.btnRegister);

        userRepository = new UserRepository(getApplication());

        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            userRepository.register(new User(username, password),
                    () -> runOnUiThread(() -> {
                        Toast.makeText(this, "Registered successfully! You can now log in.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }),
                    () -> runOnUiThread(() ->
                            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show())
            );
        });
    }
}
