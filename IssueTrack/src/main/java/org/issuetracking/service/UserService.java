package org.issuetracking.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;

@Stateless
public class UserService extends GenericService<User, UserDAO> {

    @Inject
    protected UserDAO gDAO;

    @Override
    protected UserDAO getDAO() {
        return gDAO;
    }
}
