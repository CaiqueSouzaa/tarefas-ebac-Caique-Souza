package br.com.csouza.mod33.dao;

import br.com.csouza.mod33.domain.Carro;
import br.com.csouza.mod33.interfaces.dao.ICarroDAO;

/**
 * DAO para controle da entidade Carro.
 * 
 * @author Caique Souza
 */
public class CarroDAO extends GenericDAO<Carro> implements ICarroDAO {
    @Override
    protected Class<Carro> getEntityClass() {
        return Carro.class;
    }
}
