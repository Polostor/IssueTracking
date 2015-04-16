/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking.view.converters;

import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.issuetracking.model.User;
import org.issuetracking.view.UserBean;

@FacesConverter(value = "userConverter")
@Named("userConverterBean")
public class UserConverter implements Converter {

    @PersistenceContext(unitName = "IssueTrackingPU")
    private transient EntityManager em;

    @Override
    public User getAsObject(FacesContext context, UIComponent component, String value) {
        UserBean userBean = context.getApplication().evaluateExpressionGet(context, "#{userBean}", UserBean.class);
        for (User type : userBean.getUsers()) {
            if (value.equals(type.getNick())) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof User) {
            return ((User) value).getNick();
        } else {
            return "";
        }
    }

}
