package br.com.csouza.abstracts;

import br.com.csouza.interfaces.IGenericDAO;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericDAO<T extends Persistence> implements IGenericDAO<T> {
    private Map<Class<T>, Map<Long, T>> entities;

    public GenericDAO() {
        this.entities = new HashMap<>();
        Map<Long, T> localEntities = this.entities.get(this.getClassType());
        if (localEntities == null) {
            this.entities.put(this.getClassType(), new HashMap<>());
        }
    }

    protected abstract Class<T> getClassType();

    @Override
    public T show(long code) {
        return this.getLocalEntities().get(code);
    }

    @Override
    public Collection<T> index() {
        return this.getLocalEntities().values();
    }

    @Override
    public boolean store(T entity) {
        if (this.isRegistered(entity.getCode())) return false;
        entity.setCode(this.getLocalEntities().size() + 1);
        this.getLocalEntities().put(entity.getCode(), entity);
        return true;
    }

    @Override
    public boolean update(long code, T entity) {
        if (!this.isRegistered(code) || code != entity.getCode()) return false;
        this.getLocalEntities().replace(code, entity);
        return true;
    }

    @Override
    public boolean destroy(long code) {
        if (!this.isRegistered(code)) return false;
        this.getLocalEntities().remove(code);
        return true;
    }

    /**
     * Método responsável por buscar, dentro de "entities" as entidades da classe atual.
     * @return Mapa contendo os as entidades.
     */
    private Map<Long, T> getLocalEntities() {
        return this.entities.get(this.getClassType());
    }

    private T getEntity(long code) {
        return this.getLocalEntities().get(code);
    }

    /**
     * Método para verificar se o código de uma entidade já está registrada.
     * @param code Código de entidade a ser verificado.
     * @return Status de registro. true -> Entidade existe, false -> Entidade não existe.
     */
    private boolean isRegistered(long code) {
        return this.getEntity(code) != null;
    }
}
