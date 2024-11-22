package br.com.csouza.dao;

import br.com.csouza.annotations.use.TableUse;
import br.com.csouza.entities.Client;
import br.com.csouza.exceptions.WithoutTableNameException;
import br.com.csouza.interfaces.dao.IClientDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDAO extends GenericDAO<Client> implements IClientDAO {
    @Override
    protected Class<Client> getEntityClass() {
        return Client.class;
    }

    @Override
    protected String getInsertSQL() throws WithoutTableNameException {
        return "INSERT INTO " + TableUse.getTableNameClass(Client.class)
                + " (id, name, surname, cpf, telephone, email, age)"
                + " VALUES (NEXTVAL('sq_id_clients'), ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void preparedInsertSQL(PreparedStatement stm, Client entity) throws SQLException {
        stm.setString(1, entity.getName());
        stm.setString(2, entity.getSurname());
        stm.setString(3, entity.getCpf());
        stm.setString(4, entity.getTelephone());
        stm.setString(5, entity.getEmail());
        stm.setInt(6, entity.getAge());
    }

    @Override
    protected String getUpdateSQL() throws WithoutTableNameException {
        return "UPDATE " + TableUse.getTableNameClass(this.getEntityClass())
                + " SET name = ?, surname = ?, cpf = ?, telephone = ?, email = ?, age = ? WHERE id = ?";
    }

    @Override
    protected void prepareUpdateSQL(PreparedStatement stm, Client entity) throws SQLException {
        stm.setString(1, entity.getName());
        stm.setString(2, entity.getSurname());
        stm.setString(3, entity.getCpf());
        stm.setString(4, entity.getTelephone());
        stm.setString(5, entity.getEmail());
        stm.setInt(6, entity.getAge());
        stm.setLong(7, entity.getId());
    }
}
