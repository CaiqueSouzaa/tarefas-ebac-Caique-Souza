package br.com.csouza.factories;

import br.com.csouza.entities.Product;
import br.com.csouza.entities.Storage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildStorage {
    public static Storage build(ResultSet rs, Product product) throws SQLException {
        return new Storage(
                rs.getLong("storage_id"),
                product,
                rs.getInt("amount"),
                rs.getTimestamp("storage_created_at")
        );
    }
}
