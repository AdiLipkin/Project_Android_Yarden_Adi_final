package com.example.project_327929279_326566999_final.data.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pizzas")
public class Pizza {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String size;
    private double price;
    public boolean extraCheese;

    public Pizza(String name, String size, boolean extraCheese) {
        this.name = name;
        this.size = size;
        this.extraCheese = extraCheese;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}

