package br.com.csouza.entity;

import br.com.csouza.annotations.PrimaryKeyAcronym;

@PrimaryKeyAcronym("CL")
public class Client extends DatabaseObject{
    private String name;
    private String surname;
    private int age;

    public Client() {}

    public Client(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
