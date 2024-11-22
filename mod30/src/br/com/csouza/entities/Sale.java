package br.com.csouza.entities;

import br.com.csouza.annotations.TableName;

import java.sql.Timestamp;

@TableName("tb_sales")
public class Sale extends DatabaseEntity {
    private Client client;
    private Status status;

    public Sale() {}

    public Sale(Client client, Status status) {
        this.client = client;
        this.status = status;
    }

    public Sale(Long id, Client client, Status status, Timestamp createdAt) {
        super(id, createdAt);
        this.client = client;
        this.status = status;
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
}
