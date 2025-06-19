package com.example.project_327929279_326566999_final.data.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.project_327929279_326566999_final.data.converters.Converters;
import com.example.project_327929279_326566999_final.data.dao.OrderDao;
import com.example.project_327929279_326566999_final.data.dao.PizzaDao;
import com.example.project_327929279_326566999_final.data.dao.UserDao;
import com.example.project_327929279_326566999_final.data.entities.Order;
import com.example.project_327929279_326566999_final.data.entities.Pizza;
import com.example.project_327929279_326566999_final.data.entities.User;

@Database(entities = {Pizza.class, User.class, Order.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract OrderDao orderDao();
    public abstract PizzaDao pizzaDao();
    public abstract UserDao userDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "pizza_db"
                    )
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
