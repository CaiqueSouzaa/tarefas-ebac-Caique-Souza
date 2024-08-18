package br.com.csouza.abstracts.vehicles;

import br.com.csouza.abstracts.vehicles.types.Land;

public abstract class AbstractCar extends Land {
    private final int ports;

    public AbstractCar(String model, String manufacture, float gasolineTotal, float price, boolean isNew, int tires, int ports) {
        super(model, manufacture, gasolineTotal, price, isNew, tires);
        this.ports = ports;
    }

    public int getPorts() {
        return this.ports;
    }
}
