/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking.view;

import java.security.Principal;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.Role;
import org.issuetracking.model.User;
import org.issuetracking.service.UserService;
import org.issuetracking.view.iface.PrincipalBeanInterface;

@Named
@RequestScoped
public class PrincipalBean implements PrincipalBeanInterface {

    private User user;

    @Inject
    private UserService userService;

    @Override
    public long getId() {
        if (isLogged()) {
            return user.getId();
        }
        return 0;
    }

    @Override
    public Role getRole() {
        if (isLogged()) {
            return user.getRoles();
        }
        return null;
    }

    @Override
    public String getUsername() {
        if (isLogged()) {
            return user.getNick();
        }
        return "none";
    }

    @Override
    public boolean isLogged() {

        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = userService.getUserByNickname(principal.getName());
            }
        }

        return user != null;
    }

    @Override
    public boolean hasRole(Role role) {
        return user.getRoles().equals(role);
    }

    @Override
    public void init() {
        isLogged();
    }

    @Override
    public User getUser() {
        if (isLogged()) {
            return user;
        }
        return null;
    }

}
