package br.com.csouza.classes.concretes;

public class Client {
    private String name;
    private String surname;
    private boolean isContract;
    private char level;

    public Client(String name, String surname, boolean isContract, char level) {
        this.name = name;
        this.surname = surname;
        this.isContract = isContract;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isContract() {
        return isContract;
    }

    public char getLevel() {
        return level;
    }
}
