package com.example.project_327929279_326566999_final.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.project_327929279_326566999_final.adapters.OrderAdapter;
import com.example.project_327929279_326566999_final.viewmodel.OrderViewModel;

public class OrderListFragment extends Fragment {
    private OrderViewModel orderViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        OrderAdapter adapter = new OrderAdapter();
        recyclerView.setAdapter(adapter);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

        SharedPreferences prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        int userId = prefs.getInt("user_id", -1);

        if (userId != -1) {
            orderViewModel.getOrdersForUser(userId).observe(getViewLifecycleOwner(), adapter::submitList);
        }

        return view;
    }

}
