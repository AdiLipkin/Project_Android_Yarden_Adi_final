package com.example.project_327929279_326566999_final.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.project_327929279_326566999_final.R;
import com.example.project_327929279_326566999_final.data.entities.Pizza;
import com.example.project_327929279_326566999_final.viewmodel.PizzaViewModel;


public class HomeFragment extends Fragment {
    private PizzaViewModel pizzaViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        pizzaViewModel = new ViewModelProvider(this).get(PizzaViewModel.class);

        CheckBox cbCheese = view.findViewById(R.id.cbCheese);
        Button btnOrder = view.findViewById(R.id.btnOrder);
        EditText etName = view.findViewById(R.id.etName);
        EditText etSize = view.findViewById(R.id.etSize);

        btnOrder.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String size = etSize.toString();
            boolean extraCheese = cbCheese.isChecked();

            if (!name.isEmpty()) {
                pizzaViewModel.insertPizza(new Pizza(name, size, extraCheese));
                Toast.makeText(getContext(), "Pizza ordered!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please enter a pizza name", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}