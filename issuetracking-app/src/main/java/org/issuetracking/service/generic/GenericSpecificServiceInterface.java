package org.issuetracking.service.generic;

import java.util.List;

import org.issuetracking.dao.GenericDAO;
import org.issuetracking.model.Comment;
import org.issuetracking.service.ValidationException;

public interface GenericSpecificServiceInterface<E, TypeOfDAO extends GenericDAO> {    
    public void add(E e) throws ValidationException;    
    public void edit(E e) throws ValidationException;
    public E view(long id) throws ValidationException;
    public List<E> viewAll() throws ValidationException;
}
