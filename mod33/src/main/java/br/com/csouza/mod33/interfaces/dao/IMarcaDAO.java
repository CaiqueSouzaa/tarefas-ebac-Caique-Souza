package br.com.csouza.mod33.interfaces.dao;

import br.com.csouza.mod33.domain.Acessorio;
import br.com.csouza.mod33.domain.Marca;

/**
 * Interface para o manuseio da DAO MarcaDAO.
 * 
 * @author Caique Souza
 */
public interface IMarcaDAO extends IGenericDAO<Marca> {
    /**
     * Método para registrar um novo acessorio na marca.
     * @param marca Marca a ter um novo acessorio registrado.
     * @param acessorio Acessorio a ser registrado.
     * @return Acessorio registrado contendo seu ID de registro.
     */
    public Acessorio createAcessorio(Marca marca, Acessorio acessorio);
}
