package com.example.project_327929279_326566999_final.data.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "pizzas")
public class Pizza {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String size;
    public List<String> toppings;
    public String drink;
    public boolean extraCheese;
    public int totalPrice;
    public int userId;

    public Pizza(String name, String size, List<String> toppings, String drink, boolean extraCheese, int totalPrice, int userId) {
        this.name = name;
        this.size = size;
        this.toppings = toppings;
        this.drink = drink;
        this.extraCheese = extraCheese;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public double getPrice() { return totalPrice; }
    public void setPrice(int totalPrice) { this.totalPrice = totalPrice; }
}

