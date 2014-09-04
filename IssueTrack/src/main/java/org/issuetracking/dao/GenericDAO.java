package org.issuetracking.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

public class GenericDAO<E> implements GenericDAOInterface<E> {

    @PersistenceContext(unitName = "IssueTrackingPU", type = PersistenceContextType.EXTENDED)
    protected EntityManager entityManager;

    @Override
    public void create(E obj) {
        entityManager.persist(obj);
    }

    @Override
    public E find(int id) {
        Query query = entityManager.createNamedQuery("SELECT c FROM E c WHERE c.id = :id");
        query.setParameter("id", id);
        return (E) query.getSingleResult();
    }

    @Override
    public List<E> findAll() {
        Query query = entityManager.createQuery("SELECT c FROM E c");
        return query.getResultList();
    }

    @Override
    public void update(E obj) {
        entityManager.merge(obj);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
