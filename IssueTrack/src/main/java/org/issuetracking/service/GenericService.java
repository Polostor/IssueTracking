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
public abstract class GenericService<E> implements GenericServiceInterface<E>, Serializable {

    GenericDAO gDAO;

    @Override
    public void create(E obj) {
        gDAO.create(obj);
    }

    @Override
    public E getObj(long id) {
        return (E) gDAO.find(id);
    }

    @Override
    public List<E> getAllObjs() {
        return gDAO.findAll();
    }

    @Override
    public void update(E o) {
        gDAO.update(o);
    }
}
