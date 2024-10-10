package br.com.csouza.dao.mocks;

import br.com.csouza.exceptions.EntityWithoutTableNameException;
import br.com.csouza.interfaces.IGenericDAO;
import br.com.csouza.entities.DatabaseObject;
import br.com.csouza.utils.GetTableName;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class GenericDAOMock<T extends DatabaseObject> implements IGenericDAO<T> {

    public GenericDAOMock() {
        SingletonDatabaseMock.init();
    }

    protected abstract String getTableName() throws EntityWithoutTableNameException;

    @Override
    public T show(Long id) throws Exception {
        Optional<Object> item = SingletonDatabaseMock.getItem(this.getTableName(), id);
        return (T) item.orElse(null);

    }

    @Override
    public boolean store(T entity) throws Exception {
        String tableName = GetTableName.getTableName(entity);
        try {
            SingletonDatabaseMock.getTable(tableName);
        } catch (Exception err) {
            SingletonDatabaseMock.newTable(tableName);
        }

        return SingletonDatabaseMock.add(tableName, entity);
    }

    @Override
    public Collection<T> index() throws Exception {
        Collection<Object> table = SingletonDatabaseMock.getTable(this.getTableName());
        Stream<T> objectStream = table.stream()
                .map((Object o) -> (T) o);
        return objectStream.toList();
    }

    @Override
    public boolean update(Long id, T entity) throws Exception {
        return SingletonDatabaseMock.update(this.getTableName(), id, entity);
    }

    @Override
    public boolean destroy(Long id) throws Exception {
        try {
            return SingletonDatabaseMock.delete(this.getTableName(), id);
        } catch (Exception err) {
            return false;
        }
    }
}
