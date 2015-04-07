package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

import org.issuetracking.service.generic.GenericService;
import org.issuetracking.service.generic.GenericUserServiceInterface;

@Stateless
public class UserService extends GenericService<User, UserDAO>  implements GenericUserServiceInterface{

    @Inject
    protected UserDAO gDAO;

    @Override
    protected UserDAO getDAO() {
        return gDAO;
    }

    @Override
    public void add(User user) throws ValidationException {
        String s = "create user";
        
        Validator.isNotLoggedIn(s);
        
        create(user);
    }

    @Override
    public void edit(User user) throws ValidationException {
        String s = "edit user";
        
        Validator.isLoggedIn(s);
        Validator.isAllowedUser(user, s);
        
        update(user);
    }

    @Override
    public void editPassword(String pass, User user) throws ValidationException {
        String s = "edit user password";
        
        Validator.isLoggedIn(s);
        Validator.isAllowedUser(user, s);
        
        user.setPass(pass);
        update(user);
    }

    @Override
    public User login(String nick, String pass) throws ValidationException {
        return null;
    }

    @Override
    public User view(long id) throws ValidationException {
        // this content should be available to anyone
        return getObj(id);
    }

    @Override
    public List<User> viewAll() throws ValidationException {
        // this content should be available to anyone
        return getAllObjs();
    }
}
