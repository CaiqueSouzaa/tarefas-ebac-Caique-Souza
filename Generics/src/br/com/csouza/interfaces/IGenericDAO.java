package br.com.csouza.interfaces;

import br.com.csouza.abstracts.Persistence;

import java.util.Collection;

/**
 * @author Caique Souza
 * @version 1.0
 * @param <T> Tipo do item que será tratado no DAO.
 */
public interface IGenericDAO<T extends Persistence> {

    /**
     * Método responsável por obter um único registro com base no código fornecido.
     * @param code Código do item a ser buscado.
     * @return Item buscado
     */
    T show(long code);

    /**
     * Método responsável por obter todos os registros.
     * @return Lista de itens.
     */
    Collection<T> index();

    /**
     * Método responsável por registrar um item.
     * @param entity Item a ser registrado.
     * @return Status de registro. true -> Registrado, false -> Não registrado.
     */
    boolean store(T entity);

    /**
     * Método para atualizar um registro com base no código fornecido.
     * @param code Código do item a ser atualizado.
     * @param entity Novas informações do item.
     * @return Status de atualização. true -> Atualizado, false -> Não atualizado.
     */
    boolean update(long code, T entity);

    /**
     * Método para excluir um registro.
     * @param code Código do item a ser excluído.
     * @return Status de exclusão. true -> Excluido, false -> Não excluido.
     */
    boolean destroy(long code);

}
