package br.com.csouza.abstracts.vehicles;

import br.com.csouza.abstracts.Persistence;

public abstract class Vehicle extends Persistence {
    private final String model;
    private final String manufacture;
    private final float gasolineTotal;
    private final float price;
    private final boolean isNew;

    public Vehicle(String model, String manufacture, float gasolineTotal, float price, boolean isNew) {
        this.model = model;
        this.manufacture = manufacture;
        this.gasolineTotal = gasolineTotal;
        this.price = price;
        this.isNew = isNew;
    }

    public String getModel() {
        return model;
    }

    public String getManufacture() {
        return manufacture;
    }

    public float getGasolineTotal() {
        return gasolineTotal;
    }

    public float getPrice() {
        return price;
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public String toString() {
        return this.getCode() + ": " +this.model + " - " + this.manufacture;
    }
}
