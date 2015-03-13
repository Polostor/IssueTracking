package org.issuetracking.service;

import java.io.Serializable;
import java.util.List;
import org.issuetracking.dao.GenericDAO;
import org.issuetracking.service.generic.GenericServiceInterface;

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
