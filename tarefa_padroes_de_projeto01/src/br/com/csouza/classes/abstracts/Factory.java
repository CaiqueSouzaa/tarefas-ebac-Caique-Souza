package br.com.csouza.classes.abstracts;

import br.com.csouza.classes.concretes.Client;
import br.com.csouza.classes.concretes.factories.WithContract;
import br.com.csouza.classes.concretes.factories.WithoutContract;

public abstract class Factory {
    public Car buildCar(Client client) {
        Car car = this.getCar(client);
        if (car != null) {
            this.prepareCar(car);
        }
        return car;
    }

    private void prepareCar(Car car) {
        car.clean();
        car.fullTank();
    }

    private Factory getFactory(Client client) {
        return client.isContract() ? new WithContract() : new WithoutContract();
    }

    protected abstract Car getCar(Client client);
}
