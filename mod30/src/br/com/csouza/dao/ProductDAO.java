package br.com.csouza.dao;

import br.com.csouza.annotations.use.TableUse;
import br.com.csouza.entities.Product;
import br.com.csouza.exceptions.WithoutTableNameException;
import br.com.csouza.interfaces.dao.IProductDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAO extends GenericDAO<Product> implements IProductDAO {
    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    protected String getInsertSQL() throws WithoutTableNameException {
        return "INSERT INTO " + TableUse.getTableNameClass(this.getEntityClass())
                + " (id, code, name, description, price)"
                + " VALUES (NEXTVAL('sq_id_products'), ?, ?, ?, ?)";
    }

    @Override
    protected void preparedInsertSQL(PreparedStatement stm, Product entity) throws SQLException {
        stm.setString(1, entity.getCode());
        stm.setString(2, entity.getName());
        stm.setString(3, entity.getDescription());
        stm.setFloat(4, entity.getPrice());
    }

    @Override
    protected String getUpdateSQL() throws WithoutTableNameException {
        return "UPDATE " + TableUse.getTableNameClass(this.getEntityClass())
                + " SET code = ?, name = ?, description = ?, price = ? WHERE id = ?";
    }

    @Override
    protected void prepareUpdateSQL(PreparedStatement stm, Product entity) throws SQLException {
        stm.setString(1, entity.getCode());
        stm.setString(2, entity.getName());
        stm.setString(3, entity.getDescription());
        stm.setFloat(4, entity.getPrice());
        stm.setLong(5, entity.getId());
    }
}
