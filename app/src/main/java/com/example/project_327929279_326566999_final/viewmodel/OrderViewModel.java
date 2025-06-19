package com.example.project_327929279_326566999_final.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_327929279_326566999_final.data.OrderRepository;
import com.example.project_327929279_326566999_final.data.entities.Order;

import java.util.List;
public class OrderViewModel extends AndroidViewModel {
    private OrderRepository orderRepository;
    private LiveData<List<Order>> allOrders;
    private int userId = -1;  // ברירת מחדל, או תקבלי ממשהו אחר

    public OrderViewModel(Application application) {
        super(application);
        orderRepository = new OrderRepository(application);

        // אם יש userId: את יכולה להגדיר setter ולקרוא מחדש
        allOrders = orderRepository.getOrdersByUserId(userId);  // או ברירת מחדל
    }

    public void setUserId(int userId) {
        this.userId = userId;
        allOrders = orderRepository.getOrdersByUserId(userId);
    }

    public LiveData<List<Order>> getAllOrders() {
        return allOrders;
    }

    public void insert(Order order) {
        orderRepository.insert(order);
    }
}
