package org.issuetracking.service.generic;

import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

import org.issuetracking.service.ValidationException;

public interface GenericUserServiceInterface extends GenericSpecificServiceInterface<User, UserDAO> {    
    public void editPassword(String pass, User user) throws ValidationException;    
    @Deprecated
    public User login(String nick, String pass) throws ValidationException;
    
}
