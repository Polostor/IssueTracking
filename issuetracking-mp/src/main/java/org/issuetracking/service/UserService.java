package org.issuetracking.service;

import org.issuetracking.service.annotations.LoggedIn;
import org.issuetracking.service.annotations.AllowedUser;
import org.issuetracking.service.annotations.Length;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

import org.issuetracking.service.generic.GenericUserServiceInterface;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Stateless
@Interceptors(SecurityIntercept.class)
public class UserService extends GenericUserServiceInterface{

    @Inject
    protected UserDAO gDAO;

    @Override
    protected UserDAO getDAO() {
        return gDAO;
    }

    @Override
    @LoggedIn(LoggedIn = false)
    @Length(min = 4, max = 20, param1 = "getNick()", input = "Nickname")
    @Length(min = 4, max = 20, param1 = "getPass()", input = "Password")
    @Length(min = 8, max = 32, param1 = "getEmail()", input = "Email")
    public void add(User user, PrincipalBeanInterface pb) throws ValidationException {
        create(user);
    }

    @Override
    @LoggedIn
    @AllowedUser(id1Method = "getId()")
    @Length(min = 4, max = 20, param1 = "getNick()", input = "Nickname")
    @Length(min = 4, max = 20, param1 = "getPass()", input = "Password")
    @Length(min = 8, max = 32, param1 = "getEmail()", input = "Email")
    public void edit(User user, PrincipalBeanInterface pb) throws ValidationException {
        update(user);
    }

    @Override
    public User getUserByNickname(String nick) {
        return gDAO.findByNick(nick);
    }

    @Override
    @LoggedIn
    @AllowedUser(id1Method = "getId()")
    @Length(min = 4, max = 20, input = "Password")
    public void editPassword(User user, PrincipalBeanInterface pb, String pass) throws ValidationException {
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
