package br.com.csouza.entities;

import br.com.csouza.annotations.TableName;

@TableName("clients")
public class Client extends DatabaseObject {
    private String name;
    private String surname;
    private String cpf;
    private String telephone;
    private int age;
    private Address address;

    public Client() {}

    public Client(String name, String surname, String cpf, String telephone, int age) {
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
        this.telephone = telephone;
        this.age = age;
    }

    public Client(String name, String surname, String cpf, String telephone, int age, Address address) {
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
        this.telephone = telephone;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telephone='" + telephone + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
