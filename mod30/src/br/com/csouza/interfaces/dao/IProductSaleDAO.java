package br.com.csouza.interfaces.dao;

import br.com.csouza.entities.ProductSale;
import br.com.csouza.interfaces.database.IIndex;
import br.com.csouza.interfaces.database.IShow;
import br.com.csouza.interfaces.database.IStore;

public interface IProductSaleDAO extends IStore<ProductSale>, IShow<ProductSale>, IIndex<ProductSale> {
}
