package com.example.project_327929279_326566999_final;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project_327929279_326566999_final.databinding.ActivityMainBinding;
import com.example.project_327929279_326566999_final.ui.fragments.HomeFragment;
import com.example.project_327929279_326566999_final.ui.fragments.OrderFragment;
import com.example.project_327929279_326566999_final.ui.fragments.OrderListFragment;
import com.example.project_327929279_326566999_final.ui.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ברירת מחדל - טען את HomeFragment
        loadFragment(new HomeFragment());

        // שינוי פרגמנט לפי לחיצה בתפריט
        binding.bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment;
            int id = item.getItemId();
            if (id == R.id.homeFragment) {
                fragment = new HomeFragment();}
            else if (id == R.id.orderListFragment) { // שם נכון מהתפריט
                    fragment = new OrderListFragment(); // מחליף את OrderFragment
                }else if (id == R.id.profileFragment) {
                fragment = new ProfileFragment();
            } else {
                return false;
            }
            loadFragment(fragment);
            return true;
        });
    }

    private void loadFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
