package com.example.project_327929279_326566999_final.data;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.project_327929279_326566999_final.data.dao.PizzaDao;
import com.example.project_327929279_326566999_final.data.db.AppDatabase;
import com.example.project_327929279_326566999_final.data.entities.Pizza;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PizzaRepository {
    private PizzaDao pizzaDao;
    private LiveData<List<Pizza>> allPizzas;
    private Executor executor = Executors.newSingleThreadExecutor();

    public PizzaRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        pizzaDao = db.pizzaDao();
        allPizzas = pizzaDao.getAllPizzas();
    }

    public void insert(Pizza pizza) {
        executor.execute(() -> pizzaDao.insert(pizza));
    }

    public LiveData<List<Pizza>> getAllPizzas() {
        return allPizzas;
    }
}