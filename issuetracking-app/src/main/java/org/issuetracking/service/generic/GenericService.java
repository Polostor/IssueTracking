package org.issuetracking.service.generic;

import java.io.Serializable;
import java.util.List;
import org.issuetracking.dao.GenericDAO;

public abstract class GenericService<E, TypeOfDAO extends GenericDAO> implements Serializable {

    protected abstract TypeOfDAO getDAO();

    protected void create(E obj) {
        getDAO().create(obj);
    }

    protected E getObj(long id) {
        return (E) getDAO().find(id);
    }

    protected List<E> getAllObjs() {
        return getDAO().findAll();
    }

    protected void update(E o) {
        getDAO().update(o);
    }
}
