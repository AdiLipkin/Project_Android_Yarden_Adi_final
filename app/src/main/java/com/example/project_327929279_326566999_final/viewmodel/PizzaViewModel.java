package com.example.project_327929279_326566999_final.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.project_327929279_326566999_final.data.db.AppDatabase;
import com.example.project_327929279_326566999_final.data.dao.PizzaDao;
import com.example.project_327929279_326566999_final.data.entities.Pizza;
import java.util.List;
import java.util.concurrent.Executors;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.project_327929279_326566999_final.data.entities.Pizza;
import com.example.project_327929279_326566999_final.data.PizzaRepository;
import java.util.List;

public class PizzaViewModel extends AndroidViewModel {
    private PizzaRepository repository;
    private LiveData<List<Pizza>> allPizzas;

    public PizzaViewModel(@NonNull Application application) {
        super(application);
        repository = new PizzaRepository(application);
        allPizzas = repository.getAllPizzas();
    }

    public void insertPizza(Pizza pizza) {
        repository.insert(pizza);
    }

    public LiveData<List<Pizza>> getAllPizzas() {
        return allPizzas;
    }
}
