package com.example.project_327929279_326566999_final.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_327929279_326566999_final.R;
import com.example.project_327929279_326566999_final.data.entities.Pizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {
    private List<Pizza> pizzaList = new ArrayList<>();

    public void submitList(List<Pizza> pizzas) {
        pizzaList = pizzas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.tvName.setText(pizza.getName());
        holder.tvSize.setText(pizza.getSize());
        holder.tvCheese.setText(pizza.extraCheese ? "Extra Cheese" : "No Extra Cheese");
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvSize, tvCheese;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvCheese = itemView.findViewById(R.id.tvCheese);
        }
    }
}
