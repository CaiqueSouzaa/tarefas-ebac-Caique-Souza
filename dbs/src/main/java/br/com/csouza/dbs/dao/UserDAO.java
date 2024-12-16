package br.com.csouza.dbs.dao;

import br.com.csouza.dbs.domain.User;
import br.com.csouza.dbs.interfaces.IUserDAO;

public class UserDAO extends GenericDAO<User> implements IUserDAO {
	public UserDAO(String persistenceUnitName) {
		super(persistenceUnitName);
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}

}
