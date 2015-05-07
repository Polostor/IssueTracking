package org.issuetracking.service.generic;

import java.io.Serializable;
import java.util.List;
import org.issuetracking.dao.GenericDAO;
import org.issuetracking.service.ValidationException;
import org.issuetracking.view.iface.PrincipalBeanInterface;

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

    public abstract void add(E e, PrincipalBeanInterface pb) throws ValidationException;

    public abstract void edit(E e, PrincipalBeanInterface pb) throws ValidationException;

    public abstract E view(long id) throws ValidationException;

    public abstract List<E> viewAll() throws ValidationException;
}
