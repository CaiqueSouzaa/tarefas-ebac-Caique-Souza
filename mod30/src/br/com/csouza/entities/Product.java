package br.com.csouza.entities;

import br.com.csouza.annotations.TableColumn;
import br.com.csouza.annotations.TableName;

import java.sql.Timestamp;
import java.util.Objects;

@TableName("tb_products")
public class Product extends DatabaseEntity {
    @TableColumn(dbName = "code", javaName = "setCode")
    private String code;

    @TableColumn(dbName = "name", javaName = "setName")
    private String name;

    @TableColumn(dbName = "description", javaName = "setDescription")
    private String description;

    @TableColumn(dbName = "price", javaName = "setPrice")
    private float price;

    public Product() {}

    public Product(String code, String name, String description, float price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(Long id, String code, String name, String description, float price, Timestamp createdAt) {
        super(id, createdAt);
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return true;
        final Product p = (Product) o;
        return Objects.equals(this.code, p.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.code);
    }
}
