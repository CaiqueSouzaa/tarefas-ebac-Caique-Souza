package br.com.csouza.Classes.Concretas;

import br.com.csouza.Interfaces.IUser;

public class User implements IUser {
    private String name;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}