package com.example.project_327929279_326566999_final;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.project_327929279_326566999_final.databinding.ActivityMainBinding;
import com.example.project_327929279_326566999_final.ui.fragments.HomeFragment;
import com.example.project_327929279_326566999_final.ui.fragments.OrderListFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // טוען את דף הבית כברירת מחדל
        loadFragment(new HomeFragment());

        binding.bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.homeFragment) {
                loadFragment(new HomeFragment());
            } else if (itemId == R.id.orderListFragment) {
                loadFragment(new OrderListFragment());
            }

            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
