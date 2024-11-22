package br.com.csouza.entities;

import br.com.csouza.annotations.TableColumn;
import br.com.csouza.annotations.TableName;

import java.sql.Timestamp;

@TableName("tb_status")
public class Status extends DatabaseEntity {
    @TableColumn(dbName = "name", javaName = "setName")
    private String name;

    @TableColumn(dbName = "description", javaName = "setDescription")
    private String description;

    public Status() {}

    public Status(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Status(Long id, String name, String description, Timestamp createdAt) {
        super(id, createdAt);
        this.name = name;
        this.description = description;
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
}
