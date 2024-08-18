package br.com.csouza.dao;

import br.com.csouza.abstracts.GenericDAO;
import br.com.csouza.domain.vehicles.Airplane;

public class AirplaneDAO extends GenericDAO<Airplane> {
    @Override
    protected Class<Airplane> getClassType() {
        return Airplane.class;
    }
}
