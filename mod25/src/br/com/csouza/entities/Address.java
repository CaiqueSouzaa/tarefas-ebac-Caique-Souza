package br.com.csouza.entities;

public class Address {
    private String street;
    private String number;
    private City city;

    public Address() {}

    public Address(String street, String number) {
        this.street = street;
        this.number = number;
    }

    public Address(String street, String number, City city) {
        this.street = street;
        this.number = number;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", city=" + city +
                '}';
    }
}
