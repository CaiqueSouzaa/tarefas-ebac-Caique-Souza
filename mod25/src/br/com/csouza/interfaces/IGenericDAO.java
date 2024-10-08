package br.com.csouza.interfaces;

import br.com.csouza.entities.DatabaseObject;

import java.util.Collection;

/**
 * Interface resposável por conter os métodos CRUD usados nos DAO.
 * @param <T> Tipo do objeto a ser manipulado.
 *
 * @author Caique Souza
 * @version 1.0
 */
public interface IGenericDAO<T extends DatabaseObject> {
    /**
     * Método para obter um único registro com base no id pesquisado.
     * @param id - ID do resgistro a ser pesquisado;
     * @return Registro pesquisado.
     */
    public T show(Long id);

    /**
     * Método para criar um novo resgistro.
     * @param entity Objeto a ser registrado
     * @return Status de registro. true: Registrado, false: Não registrado.
     */
    public boolean store(T entity);

    /**
     * Método para obter uma coleção contendo todos os registros.
     * @return Coleção de registros.
     */
    public Collection<T> index();

    /**
     * Método para atualizar um registro.
     * @param id ID do registro a ser atualizado.
     * @param entity Informações a serem atualizadas.
     * @return Status de atualização. true: Atualizado, false: Não atualizado.
     */
    public boolean update(Long id, T entity);

    /**
     * Método para excluir um registro.
     * @param id ID do registro a ser excluído.
     * @return Status de exclusão. true: Excluído, false: Não excluído.
     */
    public boolean destroy(Long id);
}
