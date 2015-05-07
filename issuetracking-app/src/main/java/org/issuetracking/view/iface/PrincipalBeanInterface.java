package org.issuetracking.view.iface;

import javax.annotation.PostConstruct;
import org.issuetracking.model.Role;
import org.issuetracking.model.User;

public interface PrincipalBeanInterface {

    public long getId();

    public User getUser();

    public Role getRole();

    public String getUsername();

    public boolean isLogged();

    public boolean hasRole(Role role);

    @PostConstruct
    public void init();
}
