package br.com.csouza.dao;

import br.com.csouza.domain.Car;
import br.com.csouza.interfaces.ICarDAO;

public class CarDAO extends GenericDAO<Car> implements ICarDAO {
	public CarDAO(String persistenceUnitName) {
		super(persistenceUnitName);
	}

	@Override
	protected Class<Car> getEntityClass() {
		return Car.class;
	}

}
