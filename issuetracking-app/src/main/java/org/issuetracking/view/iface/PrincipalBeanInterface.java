package org.issuetracking.view.iface;

import javax.annotation.PostConstruct;
import org.issuetracking.model.Role;

public interface PrincipalBeanInterface {

    public long getId();

    public Role getRole();

    public String getUsername();

    public boolean isLogged();

    public boolean hasRole(Role role);

    @PostConstruct
    public void init();
}
