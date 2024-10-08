package br.com.csouza.entities;

import br.com.csouza.annotations.TableName;

@TableName("products")
public class Product extends DatabaseObject {
    private String name;
    private float price;
    private int amount;

    public Product() {}

    public Product(String name, float price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public int getAmount() {
        return this.amount;
    }
}
