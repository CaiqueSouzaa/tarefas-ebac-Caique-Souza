package br.com.csouza.domain.vehicles;

import br.com.csouza.abstracts.vehicles.types.Air;

public class Airplane extends Air {
    public Airplane(String model, String manufacture, float gasolineTotal, float price, boolean isNew) {
        super(model, manufacture, gasolineTotal, price, isNew);
    }
}
