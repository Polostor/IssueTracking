package org.issuetracking.service.Generic;
import java.util.List;

public interface GenericServiceInterface<E> {
    public boolean createO(E obj) throws Exception;
    public E getO(int id) throws Exception;
    public List<E> getAllOs() throws Exception;
    public boolean updateO(E obj) throws Exception;
}
