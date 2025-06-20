package com.example.project_327929279_326566999_final.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.project_327929279_326566999_final.data.dao.OrderDao;
import com.example.project_327929279_326566999_final.data.db.AppDatabase;
import com.example.project_327929279_326566999_final.data.entities.Order;

import java.util.List;
import java.util.concurrent.Executors;

public class OrderListRepository {
    private final OrderDao orderDao;

    public OrderListRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        orderDao = db.orderDao();
    }

    public LiveData<List<Order>> getOrdersForUser(int userId) { // ✅ שם נכון
        return orderDao.getOrdersByUserId(userId);              // המשתנה נכון
    }

    public void insert(Order order) {
        Executors.newSingleThreadExecutor().execute(() -> orderDao.insert(order));
    }
}

