package org.issuetracking.dao;

import java.util.List;

public interface GenericDAOInterface <E>{
    public void create(E obj) throws Exception ;
    public E find(int id) throws Exception ;
    public List<E> findAll() throws Exception ;
    public void update(E obj) throws Exception ;
}
