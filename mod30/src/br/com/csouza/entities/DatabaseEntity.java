package br.com.csouza.entities;

import br.com.csouza.annotations.TableColumn;

import java.sql.Timestamp;

public class DatabaseEntity {
    @TableColumn(dbName = "id", javaName = "setId")
    private Long id;

    @TableColumn(dbName = "created_at", javaName = "setCreatedAt")
    private Timestamp createdAt;

    public DatabaseEntity() {}

    public DatabaseEntity(Long id) {
        this.id = id;
    }

    public DatabaseEntity(Long id, Timestamp createdAt) {
        this(id);
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
