package com.example.project_327929279_326566999_final.ui.fragments;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project_327929279_326566999_final.R;
import com.example.project_327929279_326566999_final.data.UserRepository;
import com.example.project_327929279_326566999_final.data.entities.User;
public class LoginFragment extends Fragment {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private UserRepository userRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        userRepository = new UserRepository(requireActivity().getApplication());

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            userRepository.login(username, password, user -> {
                requireActivity().runOnUiThread(() -> {
                    if (user != null) {
                        // שמירת ID ב-SharedPreferences
                        SharedPreferences prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                        prefs.edit()
                                .putInt("user_id", user.getId())
                                .putString("username", user.username)
                                .apply();




                        // בדיקה והדפסה
                        Toast.makeText(getContext(), "התחברת בהצלחה! ID = " + user.getId(), Toast.LENGTH_SHORT).show();

                        // ניווט
                        NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_homeFragment);
                    } else {
                        Toast.makeText(getContext(), "שם משתמש או סיסמה שגויים", Toast.LENGTH_SHORT).show();
                    }
                });
            });
        });

        return view;
    }
}
