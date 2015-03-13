package org.issuetracking.model;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.issuetracking.dao.UserDAO;

@FacesConverter("userConverter")
public class UserConverter implements Converter {

    @EJB
    private UserDAO users;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }
        int id = Integer.parseInt(value);

        return users.find(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Integer id = ((User) value).getIduser();

        return (id != null) ? id.toString() : null;
    }

}