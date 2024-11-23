package br.com.csouza.factories;

import br.com.csouza.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildProduct {
    public static Product build(ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong("product_id"),
                rs.getString("code"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getFloat("price"),
                rs.getTimestamp("product_created_at")
        );
    }
}
