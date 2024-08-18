package br.com.csouza.domain.vehicles;

import br.com.csouza.abstracts.vehicles.AbstractCar;

public class Car extends AbstractCar {
    public Car(String model, String manufacture, float gasolineTotal, float price, boolean isNew, int tires, int ports) {
        super(model, manufacture, gasolineTotal, price, isNew, tires, ports);
    }
}
