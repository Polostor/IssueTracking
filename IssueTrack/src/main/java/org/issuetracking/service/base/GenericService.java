package org.issuetracking.service.base;

import java.io.Serializable;
import java.util.List;
import org.issuetracking.dao.GenericDAO;
import org.issuetracking.service.GenericServiceInterface;

public abstract class GenericService<E> implements GenericServiceInterface<E>, Serializable {

    protected E obj;

    GenericDAO gDAO;

    @Override
    public boolean create(E obj) throws Exception {
        gDAO.create(obj);
        // TODO change as it is updted or not
        return true;
    }

    @Override
    public E getObj(int id) throws Exception {
        return (E) gDAO.find(id);
    }

    @Override
    public List<E> getAll() throws Exception {
        return gDAO.findAll();
    }

    @Override
     public boolean update(E o) throws Exception {
     gDAO.update(o);
     // TODO change as it is updted or not
     return true;
     }
}
