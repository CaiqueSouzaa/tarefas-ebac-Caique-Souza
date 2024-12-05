package br.com.csouza.mod33.dao;

import br.com.csouza.mod33.domain.Acessorio;
import br.com.csouza.mod33.domain.Marca;
import br.com.csouza.mod33.interfaces.dao.IMarcaDAO;
import br.com.csouza.mod33.services.AcessorioService;

/**
 * DAO para controle da entidade Marca.
 * 
 * @author Caique Souza
 */
public class MarcaDAO extends GenericDAO<Marca> implements IMarcaDAO {
    private final AcessorioService acessorioService;

    public MarcaDAO() {
        this.acessorioService = new AcessorioService(new AcessorioDAO());
    }

    @Override
    protected Class<Marca> getEntityClass() {
        return Marca.class;
    }

    @Override
    public Acessorio createAcessorio(Marca marca, Acessorio acessorio) {
        acessorio.setMarca(marca);

        return this.acessorioService.create(acessorio);
    }
}
