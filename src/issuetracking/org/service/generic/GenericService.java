package issuetracking.org.service.generic;

import java.util.List;

public interface GenericService<E> {
    public boolean create(E obj);
    public E find(int id);
    public List<E> findAll();
    public boolean update(E obj);
}
