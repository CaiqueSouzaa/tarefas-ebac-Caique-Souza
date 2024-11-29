package br.com.csouza.mod32.jdbc;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PostgreSQLConnection {
    private static String persistenceUnitName = "HibernateMod32";
    private static EntityManagerFactory entityManagerFactory;

    private PostgreSQLConnection() {}

    public static EntityManagerFactory getConnection() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            entityManagerFactory = initConnection();
        }

        return entityManagerFactory;
    }

    public static void closeConnection() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    private static EntityManagerFactory initConnection() {
        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }
}
