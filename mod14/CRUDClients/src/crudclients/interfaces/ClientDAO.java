/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package crudclients.interfaces;

import java.util.Collection;
import crudclients.objects.Client;

/**
 *
 * @author caique
 */

public interface ClientDAO {
    
    /**
     * Método para registrar um novo cliente.
     * @param client
     * @return boolean - Status de registro.
     */
    boolean register(Client client);
    
    /**
     * Método para obter todos os clientes.
     * @return Collection Client - Lista de clientes.
     */
    Collection<Client> index();
    
    /**
     * Método para obter um único cliente.
     * @return Client
     */
    Client show(String cpf);
    
    /**
     * Método para atualizar um cliente.
     * @param cpf CPF do cliente a ser atualizado.
     * @param newClientData Novas informações cliente a serem atualizadas.
     * @return boolean - Status de atualização.
     */
    boolean update(String cpf, Client newClientData);
    
    /**
     * Método para excluir um cliente.
     * @param cpf CPF do cliente a ser excludo.
     * @return boolean - Status de exclusão.ã
     */
    boolean destroy(String cpf);
}