package br.com.csouza.services;

import br.com.csouza.entities.Product;
import br.com.csouza.interfaces.IProductDAO;
import br.com.csouza.interfaces.IProductService;

/**
 * Serviço para a entidade "Product".
 *
 * @author Caique Souza
 * @version 1.0
 */
public class ProductService extends GenericService<Product> implements IProductService {
    /**
     * Método construtor.
     * Necessário informar a base de dados que será manipulada pelo serviço.
     * @param dao Base de dados a ser manipulada.
     */
    public ProductService(IProductDAO dao) {
        super(dao);
    }
}
