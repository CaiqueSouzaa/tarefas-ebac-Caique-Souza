package br.com.csouza.services;

import br.com.csouza.entities.Client;
import br.com.csouza.interfaces.IClientDAO;
import br.com.csouza.interfaces.IClientService;

/**
 * Serviço para a entidade "Client".
 *
 * @author Caique Souza
 * @version 1.0
 */
public class ClientService extends GenericService<Client> implements IClientService {
    /**
     * Método construtor.
     * Necessário informar a base de dados que será manipulada pelo serviço.
     * @param dao Base de dados a ser manipulada.
     */
    public ClientService(IClientDAO dao) {
        super(dao);
    }
}
