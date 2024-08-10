package br.com.csouza.classes.concretes.factories;

import br.com.csouza.classes.abstracts.Car;
import br.com.csouza.classes.abstracts.Factory;
import br.com.csouza.classes.concretes.Client;
import br.com.csouza.classes.concretes.cars.BMWX2;
import br.com.csouza.classes.concretes.cars.Gol;
import br.com.csouza.classes.concretes.cars.HB20;
import br.com.csouza.classes.concretes.cars.Model3;

public class WithoutContract extends Factory {

    @Override
    protected Car getCar(Client client) {
        switch (client.getLevel()) {
            case 'A':
                return new HB20("HB20", "Hyundai", true, 76.00f, false);
            case 'B':
                return new Gol("Gol", "volkswagen", false, 12.00f, false);
            default:
                System.out.println("Nível não válido");
                return null;
        }
    }
}
