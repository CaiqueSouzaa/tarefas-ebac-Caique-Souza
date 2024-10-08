package br.com.csouza.dao.mocks;

import br.com.csouza.entities.Product;
import br.com.csouza.exceptions.EntityWithoutTableNameException;
import br.com.csouza.interfaces.IProductDAO;
import br.com.csouza.utils.GetTableName;

public class ProductDAOMock extends GenericDAOMock<Product> implements IProductDAO {
    @Override
    protected String getTableName() throws EntityWithoutTableNameException {
        return GetTableName.getTableNameClass(Product.class);
    }
}
