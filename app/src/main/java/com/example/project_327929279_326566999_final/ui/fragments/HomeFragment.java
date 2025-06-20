package com.example.project_327929279_326566999_final.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        // רכיבי ממשק
        RadioButton radioMargarita = view.findViewById(R.id.radioMargarita);
        RadioButton radioPesto = view.findViewById(R.id.radioPesto);
        RadioButton radioAlfredo = view.findViewById(R.id.radioAlfredo);

        ImageButton imgMargarita = view.findViewById(R.id.imgMargarita);
        ImageButton imgPesto = view.findViewById(R.id.imgPesto);
        ImageButton imgAlfredo = view.findViewById(R.id.imgAlfredo);

        RadioGroup sizeGroup = view.findViewById(R.id.radioSize);
        Spinner drinkSpinner = view.findViewById(R.id.spinnerDrink);
        Spinner sauceSpinner = view.findViewById(R.id.spinnerSauce);
        LinearLayout toppingsContainer = view.findViewById(R.id.toppingsContainer);
        Button btnOrder = view.findViewById(R.id.btnOrder);

        // ברירת מחדל
        radioMargarita.setChecked(true);
        updateToppings(toppingsContainer, selectedPizza);

        // מאזין אחיד לפיצה (תמונה או רדיו)
        View.OnClickListener pizzaSelectionListener = v -> {
            radioMargarita.setChecked(false);
            radioPesto.setChecked(false);
            radioAlfredo.setChecked(false);

            if (v instanceof RadioButton) {
                ((RadioButton) v).setChecked(true);
            } else {
                int id = v.getId();
                if (id == R.id.imgMargarita) {
                    radioMargarita.setChecked(true);
                } else if (id == R.id.imgPesto) {
                    radioPesto.setChecked(true);
                } else if (id == R.id.imgAlfredo) {
                    radioAlfredo.setChecked(true);
                }
            }

            // עדכון סוג הפיצה לפי הבחירה
            if (radioMargarita.isChecked()) {
                selectedPizza = "מרגריטה";
            } else if (radioPesto.isChecked()) {
                selectedPizza = "פסטו";
            } else if (radioAlfredo.isChecked()) {
                selectedPizza = "אלפרדו";
            }

            updateToppings(toppingsContainer, selectedPizza);
        };

        // מאזינים לפיצה (תמונות + כפתורים)
        radioMargarita.setOnClickListener(pizzaSelectionListener);
        radioPesto.setOnClickListener(pizzaSelectionListener);
        radioAlfredo.setOnClickListener(pizzaSelectionListener);
        imgMargarita.setOnClickListener(pizzaSelectionListener);
        imgPesto.setOnClickListener(pizzaSelectionListener);
        imgAlfredo.setOnClickListener(pizzaSelectionListener);

        // מאזין לשינוי גודל
        sizeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.sizeS) {
                selectedSize = "S";
            } else if (checkedId == R.id.sizeM) {
                selectedSize = "M";
            } else if (checkedId == R.id.sizeL) {
                selectedSize = "L";
            }
        });

        // לחיצה על "בצע הזמנה"
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

            selectedDrink = (String) drinkSpinner.getSelectedItem();
            selectedSauce = (String) sauceSpinner.getSelectedItem();

            int totalPrice = calculatePrice(toppingsContainer);

            // קבלת userId מתוך SharedPreferences
            SharedPreferences prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            int userId = prefs.getInt("user_id", -1);

            if (userId == -1) {
                Toast.makeText(getContext(), "שגיאה בזיהוי המשתמש", Toast.LENGTH_SHORT).show();
                return;
            }

            Order order = new Order(
                    selectedPizza,
                    selectedSize,
                    String.join(", ", selectedToppings),
                    selectedDrink + " + " + selectedSauce,
                    totalPrice,
                    userId // ✅ משתמש מחובר
            );


            orderViewModel.insert(order);

            String summary = "פיצה: " + selectedPizza +
                    " (" + selectedSize + ")\n" +
                    "תוספות: " + (selectedToppings.isEmpty() ? "ללא" : String.join(", ", selectedToppings)) + "\n" +
                    "שתייה: " + selectedDrink + "\n" +
                    "רוטב: " + selectedSauce + "\n" +
                    "סה\"כ לתשלום: " + totalPrice + " ש\"ח";

            Toast.makeText(getContext(), summary, Toast.LENGTH_LONG).show();
        });

        return view;
    }

    /**
     * עדכון תוספות לפי סוג הפיצה
     */
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
                toppings = new String[]{"פטריות", "תירס", "בולגרית", "בצל", "זיתים", "פפרוני", "אקסטרה גבינה"};
                toppingsFree = false;
                break;
        }

        for (String topping : toppings) {
            CheckBox cb = new CheckBox(getContext());
            cb.setText(topping);
            cb.setChecked(pizzaType.equals("פסטו")); // פסטו – תוספות מסומנות כברירת מחדל
            container.addView(cb);
        }

        container.setTag(toppingsFree);
    }

    /**
     * חישוב מחיר כולל לפי גודל, תוספות, שתייה ורוטב
     */
    private int calculatePrice(LinearLayout toppingsContainer) {
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

        boolean toppingsFree = false;
        Object tag = toppingsContainer.getTag();
        if (tag instanceof Boolean) {
            toppingsFree = (Boolean) tag;
        }

        int toppingCost = toppingsFree ? 0 : selectedToppings.size() * 3;
        int drinkCost = 10;
        int sauceCost = 5;

        return basePrice + toppingCost + drinkCost + sauceCost;
    }
}
