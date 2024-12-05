package br.com.csouza.mod33.interfaces.dao;

import java.util.Collection;

/**
 * Interface generica para implementação na DAO generica.
 * 
 * @author Caique Souza
 */
public interface IGenericDAO<T> {
    /**
     * Método para criar um novo registro.
     * @param entity Objeto a ser registrado.
     * @return Ojeto contendo seu ID de registro.
     */
    public T create(T entity);

    /**
     * Método para buscar um registro com base em seu ID.
     * @param id ID de registro a ser buscado.
     * @return Objeto contendo as inforamções do registro.
     */
    public T getById(Long id);

    /**
     * Método para buscar por todos os registros.
     * @return Coleção contendo todos os registros.
     */
    public Collection<T> getAll();

    /**
     * Método para atualizar um registro.
     * @param entity Objeto contendo as informações a serem atualizadas.
     * @return Objeto com as informações atualizadas.
     */
    public T update(T entity);

    /**
     * Método para excluir um registro.
     * @param id ID do registro a ser excluído.
     * @return Status de exclusão. true -> Excluído; false -> Não excluído.
     */
    public Boolean delete(Long id);
}
