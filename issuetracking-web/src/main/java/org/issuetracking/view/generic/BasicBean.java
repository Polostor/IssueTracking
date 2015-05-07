package org.issuetracking.view.generic;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BasicBean {

    protected void navigate(String where) {
        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();

        nav.performNavigation(where);
    }
    
    protected void showException(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Validation Error - " + ex.getMessage(), ex.toString()));    
    }
}
