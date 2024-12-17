package br.com.csouza.dao;

import br.com.csouza.domain.User;
import br.com.csouza.interfaces.IUserDAO;

public class UserDAO extends GenericDAO<User> implements IUserDAO {
	public UserDAO(String persistenceUnitName) {
		super(persistenceUnitName);
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
