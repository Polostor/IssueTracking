package org.issuetracking.service.base;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

@ManagedBean
@ViewScoped
public class UserService extends GenericService<User> implements Serializable {

    @EJB
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
