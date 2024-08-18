package br.com.csouza.abstracts.vehicles.types;

import br.com.csouza.abstracts.vehicles.Vehicle;

public abstract class Land extends Vehicle {
    private final int tires;

    public Land(String model, String manufacture, float gasolineTotal, float price, boolean isNew, int tires) {
        super(model, manufacture, gasolineTotal, price, isNew);
        this.tires = tires;
    }

    public int getTires() {
        return this.tires;
    }
}
