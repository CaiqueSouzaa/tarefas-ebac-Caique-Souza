package br.com.csouza.dao.mocks;

import br.com.csouza.entities.Client;
import br.com.csouza.exceptions.EntityWithoutTableNameException;
import br.com.csouza.interfaces.IClientDAO;
import br.com.csouza.utils.GetTableName;

public class ClientDAOMock extends GenericDAOMock<Client> implements IClientDAO {
    @Override
    protected String getTableName() throws EntityWithoutTableNameException {
        return GetTableName.getTableNameClass(Client.class);
    }
}
