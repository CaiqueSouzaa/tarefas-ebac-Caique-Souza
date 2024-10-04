package br.com.csouza.interfaces;

import br.com.csouza.entity.DatabaseObject;

import java.util.Map;

public interface IGenericService <T extends DatabaseObject> {
    /**
     * Método para obter um único registro.
     * @param id ID do resgistro a ser buscado.
     * @return Registro buscado.
     */
    public T show(String id);

    /**
     * Método para obter um Map contendo todos os registros.
     * @return Map
     */
    public Map<String, T> index();

    /**
     * Método para realizar um novo registro.
     * @param object - Objeto a ser registrado.
     * @return Status de registro. "true" -> Registrado; "false" -> Não registrado.
     */
    public boolean store(T object);

    /**
     * Método para atualizar um registro.
     * @param id ID do registro a ser atualizado.
     * @param object Novas informações do objeto.
     * @return Status de atualização. "true" -> Atualizado; "false" -> Não atualizado.
     */
    public boolean update(String id, T object);

    /**
     * Método para excluir um registro.
     * @param id ID do registro a ser excluído.
     * @return Status de exclusão. "true" -> Excluído; "false" -> Não excluído.
     */
    public boolean destroy(String id);

    /**
     * Método para verificar se o id de registro informado existe sem que seja retornado o objeto.
     * @param id ID do registro a ser buscado.
     * @return Status de existência. "true" -> Existe; "false" -> Não existe.
     */
    public boolean hasRegister(String id);
}
