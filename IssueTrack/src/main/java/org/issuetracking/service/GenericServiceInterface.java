package org.issuetracking.service;
import java.util.List;

public interface GenericServiceInterface<E> {
    public boolean create(E obj) throws Exception;
    public E getObj(int id) throws Exception;
    public List<E> getAll() throws Exception;
    public boolean update(E obj) throws Exception;
}
