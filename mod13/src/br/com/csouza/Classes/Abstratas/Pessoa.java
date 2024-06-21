package br.com.csouza.Classes.Abstratas;

import br.com.csouza.Interfaces.IPessoa;

public abstract class Pessoa implements IPessoa {
    private String name;
    private String surname;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }
}