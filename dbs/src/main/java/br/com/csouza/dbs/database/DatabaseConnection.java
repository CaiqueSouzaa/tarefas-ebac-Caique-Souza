package br.com.csouza.dbs.database;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseConnection {
	private static EntityManagerFactory entityManagerFactory;
		
	public static EntityManagerFactory getConnection(String persistenceUnitName) {
		if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
			entityManagerFactory = initConnection(persistenceUnitName);
		}
		
		return entityManagerFactory;
	}
	
	public static EntityManagerFactory initConnection(String persistenceUnitName) {
		return Persistence.createEntityManagerFactory(persistenceUnitName);
	}
}
