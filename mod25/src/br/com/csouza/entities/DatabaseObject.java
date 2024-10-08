package br.com.csouza.entities;

import java.util.Date;

public class DatabaseObject {
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    public DatabaseObject() {}

    public Long getId() {
        return this.id;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
