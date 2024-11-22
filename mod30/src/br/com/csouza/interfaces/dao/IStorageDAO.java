package br.com.csouza.interfaces.dao;

import br.com.csouza.entities.Product;
import br.com.csouza.entities.Storage;
import br.com.csouza.interfaces.database.IIndex;
import br.com.csouza.interfaces.database.IShow;

public interface IStorageDAO extends IShow<Storage>, IIndex<Storage> {
    /**
     * Método para obter o estoque de um produto pelo seu código.
     * @param code Código de produto a ser buscado.
     * @return Estoque do produto buscado.
     */
    public Storage getByCode(String code);
}
