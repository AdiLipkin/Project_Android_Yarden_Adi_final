package com.example.project_327929279_326566999_final.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_327929279_326566999_final.data.OrderListRepository;
import com.example.project_327929279_326566999_final.data.OrderRepository;
import com.example.project_327929279_326566999_final.data.entities.Order;

import java.util.List;
public class OrderViewModel extends AndroidViewModel {
    private final OrderListRepository repository;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderListRepository(application);
    }

    public LiveData<List<Order>> getOrdersForUser(int userId) {
        return repository.getOrdersForUser(userId);
    }

    public void insert(Order order) {
        repository.insert(order);
    }
}


