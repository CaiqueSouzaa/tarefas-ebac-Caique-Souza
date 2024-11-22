package br.com.csouza.entities;

import br.com.csouza.annotations.TableColumn;
import br.com.csouza.annotations.TableName;

import java.sql.Timestamp;
import java.util.Objects;

@TableName("tb_clients")
public class Client extends DatabaseEntity {
    @TableColumn(dbName = "name", javaName = "setName")
    private String name;

    @TableColumn(dbName = "surname", javaName = "setSurname")
    private String surname;

    @TableColumn(dbName = "cpf", javaName = "setCpf")
    private String cpf;

    @TableColumn(dbName = "telephone", javaName = "setTelephone")
    private String telephone;

    @TableColumn(dbName = "email", javaName = "setEmail")
    private String email;

    @TableColumn(dbName = "age", javaName = "setAge")
    private int age;

    public Client() {}

    public Client(String name, String surname, String cpf, String telephone, String email, int age) {
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
        this.telephone = telephone;
        this.email = email;
        this.age = age;
    }

    public Client(Long id, String name, String surname, String cpf, String telephone, String email, int age, Timestamp createdAt) {
        super(id, createdAt);
        this.name = name;
        this.surname = surname;
        this.cpf = cpf;
        this.telephone = telephone;
        this.email = email;
        this.age = age;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        final Client c = (Client) o;
        return Objects.equals(this.cpf, c.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cpf, this.email);
    }
}
