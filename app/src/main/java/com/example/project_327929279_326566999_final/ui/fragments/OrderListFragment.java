package com.example.project_327929279_326566999_final.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project_327929279_326566999_final.R;
import com.example.project_327929279_326566999_final.adapters.PizzaAdapter;
import com.example.project_327929279_326566999_final.viewmodel.PizzaViewModel;

public class OrderListFragment extends Fragment {
    private PizzaViewModel pizzaViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PizzaAdapter adapter = new PizzaAdapter();
        recyclerView.setAdapter(adapter);

        pizzaViewModel = new ViewModelProvider(this).get(PizzaViewModel.class);
        pizzaViewModel.getAllPizzas().observe(getViewLifecycleOwner(), adapter::submitList);

        return view;
    }
}