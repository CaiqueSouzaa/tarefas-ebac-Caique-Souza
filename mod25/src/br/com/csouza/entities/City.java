package br.com.csouza.entities;

public class City {
    private String name;
    private String state;
    private String stateAcronym;

    public City() {}

    public City(String name, String state, String stateAcronym) {
        this.name = name;
        this.state = state;
        this.stateAcronym = stateAcronym;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStateAcronym(String stateAcronym) {
        this.stateAcronym = stateAcronym;
    }

    @Override
    public String toString() {
        return "City {'name': " + this.name + ", 'state': " + this.state + ", 'stateAcronym': " + this.stateAcronym + "}";
    }
}
