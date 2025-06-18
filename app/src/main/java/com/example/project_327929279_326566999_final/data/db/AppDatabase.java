package com.example.project_327929279_326566999_final.data.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.project_327929279_326566999_final.data.dao.PizzaDao;
import com.example.project_327929279_326566999_final.data.entities.Pizza;

@Database(entities = {Pizza.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract PizzaDao pizzaDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "pizza_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}