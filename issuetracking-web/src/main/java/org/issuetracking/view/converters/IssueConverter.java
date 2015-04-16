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

import org.issuetracking.model.Issue;
import org.issuetracking.view.IssueBean;

@FacesConverter(value = "issueConverter")
@Named("issueConverterBean")
public class IssueConverter implements Converter{    

    @PersistenceContext(unitName = "IssueTrackingPU")
    private transient EntityManager em;

    @Override
    public Issue getAsObject(FacesContext context, UIComponent component, String value) {
        IssueBean issueBean = context.getApplication().evaluateExpressionGet(context, "#{issueBean}", IssueBean.class);
        for (Issue type : issueBean.getIssues()) {
            if (value.equals(type.getName())) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Issue) {
            return ((Issue) value).getName();
        } else {
            return "";
        }
    }
    
}
