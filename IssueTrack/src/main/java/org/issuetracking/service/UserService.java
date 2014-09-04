package org.issuetracking.service;

import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

public class UserService extends GenericService<User> {

    public String saveUser() {
        gDAO.create(this.o);
        //addFlashMessage("User " + this.o.getIduser() + " saved");

        return "users.xhtml?faces-redirect=true";
    }

    public String editUser() {
        gDAO.update(this.o);
        //addFlashMessage("User " + this.o.getIduser() + " saved");

        return "users.xhtml?faces-redirect=true";
    }

    public void setUser(User user) {
        this.o = user;
    }

    public User getUser() {
        return o;
    }

    @Override
    public void setUp() throws Exception {
        gDAO = new UserDAO();
        super.setUp(); 
    }
}
