package org.issuetracking.service;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.issuetracking.dao.GenericDAO;
import org.issuetracking.service.Generic.GenericServiceInterface;

@ManagedBean
@SessionScoped
public class GenericService<E> implements GenericServiceInterface<E>, Serializable {

    protected E obj;

    @EJB
    GenericDAO gDAO;

    protected void setUp() throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("IssueTrackingPU");
        gDAO.setEntityManager(entityManagerFactory.createEntityManager());
    }

    @Override
    public boolean createObj(E obj) throws Exception {
        gDAO.create(obj);
        // TODO change as it is updted or not
        return true;
    }

    @Override
    public E getObj(int id) throws Exception {
        return (E) gDAO.find(id);
    }

    @Override
    public List<E> getAllObjs() throws Exception {
        return gDAO.findAll();
    }

    @Override
     public boolean updateObj(E o) throws Exception {
     gDAO.update(o);
     // TODO change as it is updted or not
     return true;
     }
}
