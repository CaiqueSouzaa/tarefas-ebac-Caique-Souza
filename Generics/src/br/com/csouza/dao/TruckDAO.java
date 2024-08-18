package br.com.csouza.dao;

import br.com.csouza.abstracts.GenericDAO;
import br.com.csouza.domain.vehicles.Truck;

public class TruckDAO extends GenericDAO<Truck> {
    @Override
    protected Class<Truck> getClassType() {
        return Truck.class;
    }
}
