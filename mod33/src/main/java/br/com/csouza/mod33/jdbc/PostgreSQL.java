package br.com.csouza.mod33.jdbc;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PostgreSQL {
    private static final String persistenceUnitName = "Mod33";
    private static EntityManagerFactory entityManagerFactory;

    private PostgreSQL() {}

    public static EntityManager getConnection() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            entityManagerFactory = initConnection();
        }

        return entityManagerFactory.createEntityManager();
    }

    public static void closeConnection() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    public static void closeConnection(EntityManager entityManager) {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    private static EntityManagerFactory initConnection() {
        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }
}
