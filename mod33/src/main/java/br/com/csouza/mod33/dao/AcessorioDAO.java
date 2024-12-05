package br.com.csouza.mod33.dao;

import br.com.csouza.mod33.domain.Acessorio;
import br.com.csouza.mod33.interfaces.dao.IAcessorioDAO;
import br.com.csouza.mod33.jdbc.PostgreSQL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * DAO para controle da entidade Acessorio.
 * 
 * @author Caique Souza
 */
public class AcessorioDAO extends GenericDAO<Acessorio> implements IAcessorioDAO {
    @Override
    protected Class<Acessorio> getEntityClass() {
        return Acessorio.class;
    }

    @Override
    public Acessorio getByCode(String code) {
        try (final EntityManager entityManager = PostgreSQL.getConnection()) {
            String st = "SELECT a FROM Acessorio AS a " +
                "WHERE a.code = :code";
            
            TypedQuery<Acessorio> query = entityManager.createQuery(st, this.getEntityClass());
            query.setParameter("code", code);
    
            return query.getSingleResult();
        }
    }
}
