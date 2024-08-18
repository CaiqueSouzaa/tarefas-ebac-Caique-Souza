package br.com.csouza.dao;

import br.com.csouza.abstracts.GenericDAO;
import br.com.csouza.domain.vehicles.Boat;

public class BoatDAO extends GenericDAO<Boat> {
    @Override
    protected Class<Boat> getClassType() {
        return Boat.class;
    }
}
