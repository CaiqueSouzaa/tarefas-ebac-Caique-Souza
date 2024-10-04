package br.com.csouza.services;

import br.com.csouza.entity.Client;
import br.com.csouza.interfaces.IClientDAO;

public class ClientService extends GenericService<Client> {
    public ClientService(IClientDAO clientDAO) {
        super(clientDAO);
    }
}
