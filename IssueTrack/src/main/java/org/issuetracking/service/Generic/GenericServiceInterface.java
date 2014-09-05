package org.issuetracking.service.Generic;
import java.util.List;

public interface GenericServiceInterface<E> {
    public boolean createObj(E obj) throws Exception;
    public E getObj(int id) throws Exception;
    public List<E> getAllObjs() throws Exception;
    public boolean updateObj(E obj) throws Exception;
}
