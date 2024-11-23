package br.com.csouza.factories;

import br.com.csouza.entities.Sale;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildSale {
    public static Sale build(ResultSet rs) throws SQLException {
        return new Sale(
                rs.getLong("sale_id"),
                BuildClient.build(rs),
                BuildStatus.build(rs),
                rs.getTimestamp("sale_created_at")
        );
    }
}
