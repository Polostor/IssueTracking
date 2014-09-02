package issuetracking.org.service.iface;
import java.util.List;

public interface GenericServiceInterface<E> {
    public boolean create(E obj) throws Exception;
    public E find(int id) throws Exception;
    public List<E> findAll() throws Exception;
    public boolean update(E obj) throws Exception;
}
