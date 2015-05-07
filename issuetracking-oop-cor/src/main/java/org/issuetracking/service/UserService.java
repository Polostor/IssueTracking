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
        BusinessValidator.validate(user);
        
        create(user);
    }

    @Override
    public void edit(User user, PrincipalBeanInterface pb) throws ValidationException {
        Validator.isLoggedIn(pb);
        Validator.isAllowedUser(user, pb);
        BusinessValidator.validate(user);
        
        update(user);
    }

    @Override
    public User getUserByNickname(String nick) {
        return gDAO.findByNick(nick);
    }

    @Override
    public void editPassword(User user, PrincipalBeanInterface pb, String pass) throws ValidationException {
        Validator.isLoggedIn(pb);
        Validator.isAllowedUser(user, pb);
        Validator.isBetween(pass, 4, 20, "Password");
        
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
