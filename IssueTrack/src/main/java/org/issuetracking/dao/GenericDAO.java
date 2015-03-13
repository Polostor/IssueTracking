package org.issuetracking.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class GenericDAO<E> implements GenericDAOInterface<E> {

<<<<<<< HEAD
    @PersistenceContext(unitName = "IssueTrackingPU")
    protected EntityManager em;
=======
    @PersistenceContext(unitName = "IssueTrackingPU", type = PersistenceContextType.EXTENDED)
    public EntityManager entityManager;
>>>>>>> origin/master

    @Override
    public E create(E obj) {
        em.persist(obj);
        return obj;
    }

    @Override
    public E find(long id) {
        Query query = em.createNamedQuery("SELECT c FROM E c WHERE c.id = :id");
        query.setParameter("id", id);
        return (E) query.getSingleResult();
    }

    @Override
    public List<E> findAll() {
        Query query = em.createQuery("SELECT c FROM E c");
        return query.getResultList();
    }

    @Override
    public E update(E obj) {
        em.merge(obj);
        return obj;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
}
