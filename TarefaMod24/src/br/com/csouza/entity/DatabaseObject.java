package br.com.csouza.entity;

import br.com.csouza.annotations.PrimaryKeyAcronym;

public class DatabaseObject {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
