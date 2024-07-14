package crudclients.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import crudclients.interfaces.ClientDAO;
import crudclients.objects.Client;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author caique
 */
public class ClientMapDAO implements ClientDAO {
    private Map<String, Client> clientsMap;
    
    public ClientMapDAO() {
        this.clientsMap = new HashMap<>();
    }

    @Override
    public boolean register(Client client) {
        if (isRegistered(client.getCPF())) {
            return false;
        }
        this.clientsMap.put(client.getCPF(), client);
        return true;
    }

    @Override
    public Collection<Client> index() {
        return this.clientsMap.values();
    }

    @Override
    public Client show(String cpf) {
        return this.clientsMap.get(cpf);
    }

    @Override
    public boolean update(String cpf, Client newClientData) {
        if (!this.isRegistered(cpf)) return false;
        this.clientsMap.replace(cpf, newClientData);
        return true;
    }

    @Override
    public boolean destroy(String cpf) {
        if (!this.isRegistered(cpf)) return false;
        this.clientsMap.remove(cpf);
        return true;
    }
    
    public boolean isRegistered(String cpf) {
        return this.clientsMap.containsKey(cpf);
    }
    
}
