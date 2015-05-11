package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.guvnor.annotation.RequiredRules;
import org.guvnor.annotation.StandardValidation;
import org.guvnor.annotation.Validate;

import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

import org.issuetracking.service.generic.GenericUserServiceInterface;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Stateless
public class UserService extends GenericUserServiceInterface {

    @Inject
    protected UserDAO gDAO;

    @Override
    protected UserDAO getDAO() {
        return gDAO;
    }

    @Override
    @Validate
    @StandardValidation
    @RequiredRules( "You mustn't be logged in" )
    /*@RequiredRules( "Email length has to be between 8 and 32" )
    @RequiredRules( "Nickname length has to be between 4 and 20" )
    @RequiredRules( "Password length has to be between 4 and 20" ) */
    public void add(User user, PrincipalBeanInterface pb) throws ValidationException {
        if (user.getEmail() == null || user.getEmail().length() < 8 || user.getEmail().length() > 32) {
            throw new ValidationException("Email length has to be between 8 and 32.");
        }
        if (user.getNick() == null || user.getNick().length() < 4 || user.getNick().length() > 20) {
            throw new ValidationException("Nickname length has to be between 4 and 20.");
        }
        if (user.getPass() == null || user.getPass().length() < 4 || user.getPass().length() > 20) {
            throw new ValidationException("Password length has to be between 4 and 20.");
        }
        create(user);
    }

    @Override
    @Validate
    @StandardValidation
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "Email length has to be between 8 and 32" )
    @RequiredRules( "Nickname length has to be between 4 and 20" )
    @RequiredRules( "Password length has to be between 4 and 20" ) */
    @RequiredRules( "You are not allowed" )
    public void edit(User user, PrincipalBeanInterface pb) throws ValidationException {
        String s = "edit user";
        update(user);
    }

    @Override
    @Validate
    @StandardValidation
    /*@RequiredRules( "You must be logged in" )
    @RequiredRules( "Email length has to be between 8 and 32" )
    @RequiredRules( "Nickname length has to be between 4 and 20" )
    @RequiredRules( "Password length has to be between 4 and 20" ) */
    @RequiredRules( {"New password length has to be between 4 and 20", "You are not allowed"} )
    public void editPassword(User user, PrincipalBeanInterface pb, String pass) throws ValidationException {
        user.setPass(pass);
        update(user);
    }

    @Override
    public User getUserByNickname(String nick) {
        return gDAO.findByNick(nick);
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
