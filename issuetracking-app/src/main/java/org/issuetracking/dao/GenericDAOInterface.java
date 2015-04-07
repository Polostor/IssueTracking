package org.issuetracking.dao;

import java.util.List;

public interface GenericDAOInterface <E>{
    public E create(E obj) throws Exception ;
    public E find(long id) throws Exception ;
    public List<E> findAll() throws Exception ;
    public E update(E obj) throws Exception ;
}
