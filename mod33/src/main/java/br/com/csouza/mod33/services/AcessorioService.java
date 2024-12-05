package br.com.csouza.mod33.services;

import java.util.Collection;

import br.com.csouza.mod33.domain.Acessorio;
import br.com.csouza.mod33.interfaces.dao.IAcessorioDAO;

public class AcessorioService implements IAcessorioDAO {
    private final IAcessorioDAO acessorioDAO;

    public AcessorioService(IAcessorioDAO acessorioDAO) {
        this.acessorioDAO = acessorioDAO;
    }

    @Override
    public Acessorio create(Acessorio entity) {
        return this.acessorioDAO.create(entity);
    }

    @Override
    public Acessorio getById(Long id) {
        return this.acessorioDAO.getById(id);
    }

    @Override
    public Collection<Acessorio> getAll() {
        return this.acessorioDAO.getAll();
    }

    @Override
    public Acessorio update(Acessorio entity) {
        return this.acessorioDAO.update(entity);
    }

    @Override
    public Boolean delete(Long id) {
        return this.acessorioDAO.delete(id);
    }

    @Override
    public Acessorio getByCode(String code) {
        return this.acessorioDAO.getByCode(code);
    }    
}
