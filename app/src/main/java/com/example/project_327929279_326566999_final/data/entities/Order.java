package com.example.project_327929279_326566999_final.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String size;
    private String toppings;
    private String drink;
    private int price;
    private int userId;  // לשייך את ההזמנה למשתמש

    // בוני ומאפיינים
    public Order(String name, String size, String toppings, String drink, int price, int userId) {
        this.name = name;
        this.size = size;
        this.toppings = toppings;
        this.drink = drink;
        this.price = price;
        this.userId = userId;
    }

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getToppings() { return toppings; }
    public void setToppings(String toppings) { this.toppings = toppings; }
    public String getDrink() { return drink; }
    public void setDrink(String drink) { this.drink = drink; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
