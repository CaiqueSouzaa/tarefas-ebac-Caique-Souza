package br.com.csouza.abstracts.vehicles.types;

import br.com.csouza.abstracts.vehicles.Vehicle;

public abstract class Aquatic extends Vehicle {
    public Aquatic(String model, String manufacture, float gasolineTotal, float price, boolean isNew) {
        super(model, manufacture, gasolineTotal, price, isNew);
    }
}
