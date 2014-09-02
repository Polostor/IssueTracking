package issuetracking.org.service.generic;

import issuetracking.org.dao.GenericDAO;
import issuetracking.org.service.iface.GenericServiceInterface;
import java.util.List;

public class GenericService<E> implements GenericServiceInterface<E> {
    
    GenericDAO gDAO;

    public GenericService() {}
    
    @Override
    public boolean create(E obj) throws Exception {        
        gDAO.create(obj);
        // TODO change as it is updted or not
        return true;
    }

    @Override
    public E find(int id) throws Exception {
        return (E) gDAO.find(id);
    }

    @Override
    public List<E> findAll() throws Exception {
        return gDAO.findAll();
    }

    @Override
    public boolean update(E obj) throws Exception {
        gDAO.update(obj);
        // TODO change as it is updted or not
        return true;
    }
    
}
