package br.com.csouza.mod33.interfaces.dao;

import br.com.csouza.mod33.domain.Acessorio;

/**
 * Interface para o manuseio da DAO AcessorioDAO.
 * 
 * @author Caique Souza
 */
public interface IAcessorioDAO extends IGenericDAO<Acessorio> {
    /**
     * Método para obter um acessorio com base em seu código.
     * @param code Código de acessorio a ser buscado.
     * @return Acessorio encontrado.
     */
    public Acessorio getByCode(String code);
}
