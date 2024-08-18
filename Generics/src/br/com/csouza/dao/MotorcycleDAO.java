package br.com.csouza.dao;

import br.com.csouza.abstracts.GenericDAO;
import br.com.csouza.domain.vehicles.Motorcycle;

public class MotorcycleDAO extends GenericDAO<Motorcycle> {
    @Override
    protected Class<Motorcycle> getClassType() {
        return Motorcycle.class;
    }
}
