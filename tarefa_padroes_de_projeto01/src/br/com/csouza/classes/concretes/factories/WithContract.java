package br.com.csouza.classes.concretes.factories;

import br.com.csouza.classes.abstracts.Car;
import br.com.csouza.classes.abstracts.Factory;
import br.com.csouza.classes.concretes.Client;
import br.com.csouza.classes.concretes.cars.BMWX2;
import br.com.csouza.classes.concretes.cars.Model3;

public class WithContract extends Factory {

    @Override
    protected Car getCar(Client client) {
        switch (client.getLevel()) {
            case 'A':
                return new BMWX2("BMW X2", "BMW", true, 100.00f, false);
            case 'B':
                return new Model3("Model 3", "Tesla", true, 100.00f, false);
            default:
                System.out.println("Nível não válido");
                return null;
        }
    }
}
