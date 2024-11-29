package br.com.csouza.mod32.dao;

import br.com.csouza.mod32.domain.Product;
import br.com.csouza.mod32.interfaces.dao.IProductDAO;

public class ProductDAO extends GenericDAO<Product> implements IProductDAO {
    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }
}
