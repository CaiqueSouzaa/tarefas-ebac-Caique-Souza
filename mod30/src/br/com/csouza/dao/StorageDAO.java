package br.com.csouza.dao;

import br.com.csouza.entities.Storage;
import br.com.csouza.exceptions.WithoutTableNameException;
import br.com.csouza.interfaces.dao.IStorageDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StorageDAO extends GenericDAO<Storage> implements IStorageDAO {
    @Override
    protected Class<Storage> getEntityClass() {
        return null;
    }

    @Override
    protected String getInsertSQL() throws WithoutTableNameException {
        return "";
    }

    @Override
    protected void preparedInsertSQL(PreparedStatement stm, Storage entity) throws SQLException {

    }

    @Override
    protected String getUpdateSQL() throws WithoutTableNameException {
        return "";
    }

    @Override
    protected void prepareUpdateSQL(PreparedStatement stm, Storage entity) throws SQLException {

    }

    @Override
    public Storage getByCode(String code) {
        return null;
    }
}
