package com.example.project_327929279_326566999_final.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.project_327929279_326566999_final.R;
import com.example.project_327929279_326566999_final.data.entities.Order;
import com.example.project_327929279_326566999_final.viewmodel.OrderViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private OrderViewModel orderViewModel;
    private String selectedPizza = "מרגריטה";
    private String selectedSize = "S";
    private final List<String> selectedToppings = new ArrayList<>();
    private String selectedDrink = "";
    private String selectedSauce = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        RadioGroup pizzaGroup = view.findViewById(R.id.radioPizzaType);
        RadioGroup sizeGroup = view.findViewById(R.id.radioSize);
        Spinner drinkSpinner = view.findViewById(R.id.spinnerDrink);
        Spinner sauceSpinner = view.findViewById(R.id.spinnerSauce);
        LinearLayout toppingsContainer = view.findViewById(R.id.toppingsContainer);
        Button btnOrder = view.findViewById(R.id.btnOrder);

        // בחירת סוג פיצה
        // בחירת סוג פיצה
        pizzaGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioMargarita) {
                selectedPizza = "מרגריטה";
                updateToppings(toppingsContainer, selectedPizza);
            } else if (checkedId == R.id.radioPesto) {
                selectedPizza = "פסטו";
                updateToppings(toppingsContainer, selectedPizza);
            } else if (checkedId == R.id.radioAlfredo) {
                selectedPizza = "אלפרדו";
                updateToppings(toppingsContainer, selectedPizza);
            }
        });

// בחירת גודל
        sizeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.sizeS) {
                selectedSize = "S";
            } else if (checkedId == R.id.sizeM) {
                selectedSize = "M";
            } else if (checkedId == R.id.sizeL) {
                selectedSize = "L";
            }
        });

        // טופינגים דינמיים מתעדכנים ב-updateToppings()

        btnOrder.setOnClickListener(v -> {
            selectedToppings.clear();
            for (int i = 0; i < toppingsContainer.getChildCount(); i++) {
                View child = toppingsContainer.getChildAt(i);
                if (child instanceof CheckBox) {
                    CheckBox cb = (CheckBox) child;
                    if (cb.isChecked()) {
                        selectedToppings.add(cb.getText().toString());
                    }
                }
            }

            selectedDrink = drinkSpinner.getSelectedItem().toString();
            selectedSauce = sauceSpinner.getSelectedItem().toString();

            int totalPrice = calculatePrice();

            Order order = new Order(selectedPizza, selectedSize, String.join(", ", selectedToppings), selectedDrink, totalPrice, 1);
            orderViewModel.insert(order);
            Toast.makeText(getContext(), "הזמנה בוצעה! סה\"כ: " + totalPrice + " ש\"ח", Toast.LENGTH_LONG).show();
        });

        // ברירת מחדל: מרגריטה
        updateToppings(toppingsContainer, selectedPizza);

        return view;
    }

    private void updateToppings(LinearLayout container, String pizzaType) {
        container.removeAllViews();

        String[] toppings;
        boolean toppingsFree;

        switch (pizzaType) {
            case "פסטו":
                toppings = new String[]{"גבינת עיזים", "בצל אדום", "תוספת גבינה", "זיתי קלמטה"};
                toppingsFree = true;
                break;
            case "אלפרדו":
                toppings = new String[]{"פטריות", "בולגרית", "אקסטרה גבינה"};
                toppingsFree = false;
                break;
            case "מרגריטה":
            default:
                toppings = new String[]{"פטריות", "תירס", "בצל", "בולגרית", "אקסטרה גבינה", "זיתים", "פפרוני"};
                toppingsFree = false;
                break;
        }

        for (String topping : toppings) {
            CheckBox cb = new CheckBox(getContext());
            cb.setText(topping);
            cb.setChecked(pizzaType.equals("פסטו")); // פסטו – מסומן כברירת מחדל
            container.addView(cb);
        }

        container.setTag(toppingsFree);
    }

    private int calculatePrice() {
        int basePrice;
        switch (selectedSize) {
            case "S":
                basePrice = 30;
                break;
            case "M":
                basePrice = 40;
                break;
            case "L":
                basePrice = 50;
                break;
            default:
                basePrice = 30;
        }

        boolean toppingsFree = (boolean) ((LinearLayout) requireView().findViewById(R.id.toppingsContainer)).getTag();
        int toppingCost = toppingsFree ? 0 : selectedToppings.size() * 3;
        int drinkCost = 10;

        return basePrice + toppingCost + drinkCost;
    }
}
