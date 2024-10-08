package br.com.csouza.dao.mocks;

import br.com.csouza.entities.DatabaseObject;

import java.util.*;

public class SingletonDatabaseMock {
    private static Map<String, List<Object>> tables;

    private SingletonDatabaseMock() {}

    public static void init() {
        if (tables == null) {
            tables = new HashMap<>();
        }
    }

    public static Map<String, List<Object>> getTables() {
        return tables;
    }

    public static boolean newTable(String table) {
        tables.put(table, new ArrayList<>());

        return tables.containsKey(table);
    }

    public static void add(String table, Object entity) {
        tables.get(table).add(entity);
    }
}
