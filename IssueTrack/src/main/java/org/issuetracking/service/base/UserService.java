package org.issuetracking.service.base;

import java.util.List;
import javax.ejb.Stateless;
import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

@Stateless
public class UserService extends GenericService<User> {

    public UserDAO gDAO;

    @Override
    public boolean create(User obj) throws Exception {
        gDAO.create(obj);
        // TODO change as it is updted or not
        return true;
    }

    @Override
    public User getObj(int id) throws Exception {
        return (User) gDAO.find(id);
    }

    @Override
    public List<User> getAll() throws Exception {
        return gDAO.findAll();
    }

    @Override
    public boolean update(User o) throws Exception {
        gDAO.update(o);
        // TODO change as it is updted or not
        return true;
    }
}
