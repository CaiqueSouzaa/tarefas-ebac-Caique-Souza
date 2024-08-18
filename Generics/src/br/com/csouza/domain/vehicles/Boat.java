package br.com.csouza.domain.vehicles;

import br.com.csouza.abstracts.vehicles.types.Aquatic;

public class Boat extends Aquatic {
    public Boat(String model, String manufacture, float gasolineTotal, float price, boolean isNew) {
        super(model, manufacture, gasolineTotal, price, isNew);
    }
}
