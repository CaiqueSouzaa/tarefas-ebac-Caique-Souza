package br.com.csouza;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private String surname;
    private int age;
    private char sex;

    public Person() {}

    public Person(String name, String surname, int age, char sex) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
    }

    public Collection<Person> populate() {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Caique", "Souza", 21, 'M'));
        persons.add(new Person("Ana", "Silva", 34, 'F'));
        persons.add(new Person("João", "Pereira", 28, 'M'));
        persons.add(new Person("Maria", "Ferreira", 45, 'F'));
        persons.add(new Person("Carlos", "Santos", 19, 'M'));
        persons.add(new Person("Juliana", "Oliveira", 33, 'F'));
        persons.add(new Person("Lucas", "Martins", 26, 'M'));

        return persons;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getFormated() {
        return "Nome: " + this.getName() + ' ' + this.getSurname() + ", Idade: " + this.getAge() + " anos, Sexo: " + (this.getSex() == 'F' ? "Feminino" : this.getSex() == 'M' ? "Masculino" : "Não identificado");
    }

    @Override
    public String toString() {
        return String.format("Person { 'name': '%s', 'surname': '%s', 'age': %s, 'Sex': '%s' }", this.name, this.surname, this.age, this.sex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Person p = (Person) o;
        return Objects.equals(p.getName(), this.name) && Objects.equals(p.getSurname(), this.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
}
