package com.example.project_327929279_326566999_final.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project_327929279_326566999_final.R;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvProfile = view.findViewById(R.id.tvProfile);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        // קריאת פרטי המשתמש מה־SharedPreferences
        SharedPreferences prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String username = prefs.getString("username", "משתמש לא מזוהה");
        String email = prefs.getString("email", "אין אימייל שמור");

        // הגדרת טקסט הפרופיל
        String profileInfo = "שם משתמש: " + username +
                "\nאימייל: " + email +
                "\n\nPizza App v1.0" +
                "\nDevelopers: Adi & Yarden";

        tvProfile.setText(profileInfo);

        // כפתור התנתקות
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();
            Toast.makeText(getContext(), "התנתקת בהצלחה", Toast.LENGTH_SHORT).show();

            // מעבר חזרה למסך התחברות
            NavHostFragment.findNavController(this).navigate(R.id.action_profileFragment_to_loginFragment);
        });

        return view;
    }
}
