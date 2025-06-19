package com.example.project_327929279_326566999_final.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_327929279_326566999_final.R;
import com.example.project_327929279_326566999_final.data.entities.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orderList;

    public void submitList(List<Order> orders) {
        orderList = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvName.setText(order.getName());
        holder.tvSize.setText(order.getSize());
        holder.tvToppings.setText(order.getToppings());
        holder.tvDrink.setText(order.getDrink());
        holder.tvPrice.setText("Price: " + order.getPrice() + " ₪");
    }

    @Override
    public int getItemCount() {
        return orderList == null ? 0 : orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvSize, tvToppings, tvDrink, tvPrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvToppings = itemView.findViewById(R.id.tvToppings);
            tvDrink = itemView.findViewById(R.id.tvDrink);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
