package br.com.csouza.dao;

import br.com.csouza.abstracts.GenericDAO;
import br.com.csouza.domain.vehicles.Car;

public class CarDAO extends GenericDAO<Car> {
    @Override
    protected Class<Car> getClassType() {
        return Car.class;
    }
}
