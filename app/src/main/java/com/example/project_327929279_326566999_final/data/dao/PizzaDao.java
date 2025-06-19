package com.example.project_327929279_326566999_final.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.project_327929279_326566999_final.data.entities.Pizza;
import java.util.List;

@Dao
public interface PizzaDao {
    @Insert
    void insert(Pizza pizza);

    @Delete
    void delete(Pizza pizza);

    @Query("SELECT * FROM pizzas")
    LiveData<List<Pizza>> getAllPizzas();

    @Query("SELECT * FROM pizzas WHERE userId = :userId")
    LiveData<List<Pizza>> getPizzasByUserId(int userId);

}
