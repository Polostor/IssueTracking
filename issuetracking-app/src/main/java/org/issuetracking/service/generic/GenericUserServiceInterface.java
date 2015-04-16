package org.issuetracking.service.generic;

import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

import org.issuetracking.service.ValidationException;

public abstract class GenericUserServiceInterface extends GenericSpecificServiceInterface<User, UserDAO> {    
    public abstract void editPassword(String pass, User user) throws ValidationException;    
    @Deprecated
    public abstract User login(String nick, String pass) throws ValidationException;
    
}
