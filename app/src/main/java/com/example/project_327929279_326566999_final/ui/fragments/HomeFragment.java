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
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private PizzaViewModel pizzaViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        pizzaViewModel = new ViewModelProvider(this).get(PizzaViewModel.class);

        // UI components
        EditText etName = view.findViewById(R.id.etName);
        Spinner spSize = view.findViewById(R.id.spSize);  // Spinner for pizza size
        CheckBox cbCheese = view.findViewById(R.id.cbCheese);  // CheckBox for extra cheese
        CheckBox cbCorn = view.findViewById(R.id.cbCorn);  // CheckBox for corn topping
        CheckBox cbOlives = view.findViewById(R.id.cbOlives);  // CheckBox for olives topping
        Spinner spDrink = view.findViewById(R.id.spDrink);  // Spinner for drink choice
        Button btnOrder = view.findViewById(R.id.btnOrder);

        btnOrder.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String size = spSize.getSelectedItem().toString();
            List<String> toppings = new ArrayList<>();

            // Add toppings if checked
            if (cbCheese.isChecked()) toppings.add("Extra Cheese");
            if (cbCorn.isChecked()) toppings.add("Corn");
            if (cbOlives.isChecked()) toppings.add("Olives");

            // Get selected drink
            String drink = spDrink.getSelectedItem().toString();

            // Calculate price
            int price = calculatePrice(size, toppings, drink);

            if (!name.isEmpty()) {
                Pizza pizza = new Pizza(name, size, toppings, drink, cbCheese.isChecked(), price, 1);  // 1 for now as userId
                pizzaViewModel.insertPizza(pizza);
                Toast.makeText(getContext(), "Pizza ordered!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please enter a pizza name", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private int calculatePrice(String size, List<String> toppings, String drink) {
        int basePrice = 0;

        // Set base price based on size
        switch (size) {
            case "S":
                basePrice = 25;
                break;
            case "M":
                basePrice = 35;
                break;
            case "L":
                basePrice = 55;
                break;
        }

        // Add price for toppings (each topping costs 3)
        basePrice += toppings.size() * 3;

        // Add price for drink (each drink costs 10)
        basePrice += 10;  // All drinks cost 10 for simplicity

        return basePrice;
    }
}
