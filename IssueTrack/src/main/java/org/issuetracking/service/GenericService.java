package org.issuetracking.service;

import java.io.Serializable;
import java.util.List;
import org.issuetracking.dao.GenericDAO;
import org.issuetracking.service.generic.GenericServiceInterface;

public abstract class GenericService<E, TypeOfDAO extends GenericDAO> implements GenericServiceInterface<E, TypeOfDAO>, Serializable {
    
    protected abstract TypeOfDAO getDAO();

    @Override
    public void create(E obj) {
        getDAO().create(obj);
    }

    @Override
    public E getObj(long id) {
        return (E) getDAO().find(id);
    }

    @Override
    public List<E> getAllObjs() {
        return getDAO().findAll();
    }

    @Override
    public void update(E o) {
        getDAO().update(o);
    }
}
