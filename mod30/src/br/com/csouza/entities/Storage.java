package br.com.csouza.entities;

import br.com.csouza.annotations.TableColumn;
import br.com.csouza.annotations.TableName;

import java.sql.Timestamp;
import java.util.Objects;

@TableName("tb_storage")
public class Storage extends DatabaseEntity {
    private Product product;

    @TableColumn(dbName = "amount", javaName = "setAmount")
    private int amount;

    public Storage() {}

    public Storage(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Storage(Long id, Product product, int amount, Timestamp createdAt) {
        super(id, createdAt);
        this.product = product;
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(product, storage.product);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(product);
    }
}
