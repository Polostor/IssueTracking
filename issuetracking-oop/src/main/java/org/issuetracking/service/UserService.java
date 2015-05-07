package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

import org.issuetracking.service.generic.GenericUserServiceInterface;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Stateless
public class UserService extends GenericUserServiceInterface{

    @Inject
    protected UserDAO gDAO;

    @Override
    protected UserDAO getDAO() {
        return gDAO;
    }

    @Override
    public void add(User user, PrincipalBeanInterface pb) throws ValidationException {
        Validator.isNotLoggedIn(pb);
        Validator.isBetween(user.getNick(), 4, 20, "Nickname");
        Validator.isBetween(user.getPass(), 4, 20, "Password");
        Validator.isBetween(user.getEmail(), 8, 32, "Email");
        
        create(user);
    }

    @Override
    public void edit(User user, PrincipalBeanInterface pb) throws ValidationException {
        String s = "edit user";
        
        Validator.isLoggedIn(pb);
        Validator.isAllowedUser(user, pb);
        Validator.isBetween(user.getNick(), 4, 20, "Nickname");
        Validator.isBetween(user.getPass(), 4, 20, "Password");
        Validator.isBetween(user.getEmail(), 8, 32, "Email");
        
        update(user);
    }

    @Override
    public User getUserByNickname(String nick) {
        return gDAO.findByNick(nick);
    }

    @Override
    public void editPassword(User user, PrincipalBeanInterface pb, String pass) throws ValidationException {
        String s = "edit user password";
        
        Validator.isLoggedIn(pb);
        Validator.isAllowedUser(user, pb);
        
        user.setPass(pass);
        update(user);
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
