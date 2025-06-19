package com.example.project_327929279_326566999_final.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.project_327929279_326566999_final.data.entities.Order;

import java.util.List;

@Dao
public interface OrderDao {
    @Insert
    void insert(Order order);

    @Query("SELECT * FROM orders WHERE userId = :userId")
    LiveData<List<Order>> getOrdersByUserId(int userId);
}

