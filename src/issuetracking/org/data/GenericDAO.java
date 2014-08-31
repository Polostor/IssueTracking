package issuetracking.org.data;

import java.util.List;

public interface GenericDAO <E>{
    public long create(E obj);
    public E find(int id);
    public List<E> findAll();
    public void update(E obj);
}
