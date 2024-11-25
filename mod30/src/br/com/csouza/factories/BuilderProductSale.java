package br.com.csouza.factories;

import br.com.csouza.entities.ProductSale;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuilderProductSale {
    public static ProductSale build(ResultSet rs) throws SQLException {
        return new ProductSale(
                rs.getLong("product_id"),
                BuildProduct.build(rs),
                rs.getInt("product_sale_amount"),
                rs.getFloat("product_sale_price"),
                BuildSale.build(rs),
                rs.getTimestamp("product_created_at")
        );
    }
}
