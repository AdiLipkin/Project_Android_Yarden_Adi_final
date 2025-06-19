package com.example.project_327929279_326566999_final.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.project_327929279_326566999_final.data.dao.OrderDao;
import com.example.project_327929279_326566999_final.data.db.AppDatabase;
import com.example.project_327929279_326566999_final.data.entities.Order;

import java.util.List;
import java.util.concurrent.Executors;

public class OrderRepository {
    private OrderDao orderDao;

    public OrderRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        orderDao = db.orderDao();
    }

    public void insert(Order order) {
        Executors.newSingleThreadExecutor().execute(() -> orderDao.insert(order));
    }

    public LiveData<List<Order>> getOrdersByUserId(int userId) {
        return orderDao.getOrdersByUserId(userId);
    }


}
