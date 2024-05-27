package br.com.csouza;

public class Pessoa {
    private String name;
    private char sex;

    public Pessoa(String name, char sex) {
        this.name = name;
        this.sex = Character.toUpperCase(sex);
    }

    public String getName() {
        return this.name;
    }

    public char getSexChar() {
        return this.sex;
    }

    @Override
    public String toString() {
        return String.format("Pessoa {nome: '%s', sexo: '%s'}", this.getName(), this.getSexChar());
    }
}