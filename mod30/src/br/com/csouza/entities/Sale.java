package br.com.csouza.entities;

import br.com.csouza.annotations.TableName;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@TableName("tb_sales")
public class Sale extends DatabaseEntity {
    private Client client;
    private Status status;
    private List<ProductSale> products;

    public Sale() {
        this.products = new ArrayList<>();
    }

    public Sale(Client client, Status status) {
        this();
        this.client = client;
        this.status = status;
    }

    public Sale(Long id, Client client, Status status, Timestamp createdAt) {
        super(id, createdAt);
        this.client = client;
        this.status = status;
        this.products = new ArrayList<>();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isOpen() {
        return this.status.getId() == 1L;
    }

    public boolean isFinished() {
        return this.status.getId() == 2L;
    }

    public boolean isClosed() {
        return this.status.getId() == 3L;
    }
}
