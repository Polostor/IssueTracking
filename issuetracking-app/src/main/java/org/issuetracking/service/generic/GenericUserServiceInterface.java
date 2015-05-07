package org.issuetracking.service.generic;

import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

import org.issuetracking.service.ValidationException;
import org.issuetracking.view.iface.PrincipalBeanInterface;

public abstract class GenericUserServiceInterface extends GenericSpecificServiceInterface<User, UserDAO> {    
    public abstract void editPassword(User user, PrincipalBeanInterface pb, String pass) throws ValidationException;    
    public abstract User getUserByNickname(String nick);    
    
}
