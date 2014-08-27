package issuetracking.org.datalayer;

import java.util.List;

public interface GenericDAO <E>{
    public boolean create(E obj);
    public E find(int id);
    public List<E> findAll();
    public boolean update(E obj);
}
