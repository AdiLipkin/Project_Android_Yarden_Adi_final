package com.example.project_327929279_326566999_final.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.project_327929279_326566999_final.databinding.FragmentMenuBinding;
import com.example.project_327929279_326566999_final.viewmodel.PizzaViewModel;

public class MenuFragment extends Fragment {
    private FragmentMenuBinding binding;
    private PizzaViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(PizzaViewModel.class);
        return binding.getRoot();
    }
}