package br.com.csouza.dbs.dao;

import br.com.csouza.dbs.domain.Car;
import br.com.csouza.dbs.interfaces.ICarDAO;

public class CarDAO extends GenericDAO<Car> implements ICarDAO {
	public CarDAO(String persistenceUnitName) {
		super(persistenceUnitName);
	}

	@Override
	protected Class<Car> getEntityClass() {
		return Car.class;
	}

}
