package issuetracking.org.service.generic;

import issuetracking.org.dao.UserDAO;
import issuetracking.org.model.User;

public class UserService extends GenericService<User> {

    public UserService() {
        this.gDAO = new UserDAO();
    }

    public boolean authenticate(String name, String pass) {
        User u = (User) ((UserDAO) gDAO).findByNick(name);
        if (u == null) {
            return false;
        }
        return u.getPass().equals(pass);
    }

}
