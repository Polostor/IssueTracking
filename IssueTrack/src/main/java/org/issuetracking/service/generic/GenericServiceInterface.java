package org.issuetracking.service.generic;

import java.util.List;

import org.issuetracking.dao.GenericDAO;

public interface GenericServiceInterface<E, TypeOfDAO extends GenericDAO> {
    public void create(E obj) throws Exception;
    public E getObj(long id) throws Exception;
    public List<E> getAllObjs() throws Exception;
    public void update(E obj) throws Exception;
}
