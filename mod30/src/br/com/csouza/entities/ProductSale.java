package br.com.csouza.entities;

import br.com.csouza.annotations.TableColumn;
import br.com.csouza.annotations.TableName;

import java.sql.Timestamp;

@TableName("tb_products_sales")
public class ProductSale extends DatabaseEntity {
    private Product product;
    private Sale sale;

    @TableColumn(dbName = "amount", javaName = "setAmount")
    private int amount;

    @TableColumn(dbName = "price", javaName = "setPrice")
    private float price;

    public ProductSale() {}

    public ProductSale(Product product, int amount, float price, Sale sale) {
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.sale = sale;
    }

    public ProductSale(Long id, Product product, int amount, float price, Sale sale, Timestamp createdAt) {
        super(id, createdAt);
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
