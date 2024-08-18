package br.com.csouza.domain.vehicles;

import br.com.csouza.abstracts.vehicles.types.Land;

public class Motorcycle extends Land {
    public Motorcycle(String model, String manufacture, float gasolineTotal, float price, boolean isNew, int tires) {
        super(model, manufacture, gasolineTotal, price, isNew, tires);
    }
}
