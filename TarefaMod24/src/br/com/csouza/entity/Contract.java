package br.com.csouza.entity;

import br.com.csouza.annotations.PrimaryKeyAcronym;

@PrimaryKeyAcronym("CO")
public class Contract extends DatabaseObject {
    private String name;
    private Client client;

    public Contract() {}

    public Contract(String name, Client client) {
        this.name = name;
        this.client = client;
    }

    public String getName() {
        return this.name;
    }

    public Client getClient() {
        return this.client;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
