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

import org.issuetracking.model.Project;
import org.issuetracking.view.ProjectBean;

@FacesConverter(value = "projectConverter")
@Named("projectConverterBean")
public class ProjectConverter implements Converter {

    @PersistenceContext(unitName = "IssueTrackingPU")
    private transient EntityManager em;

    @Override
    public Project getAsObject(FacesContext context, UIComponent component, String value) {
        ProjectBean projectBean = context.getApplication().evaluateExpressionGet(context, "#{projectBean}", ProjectBean.class);
        for (Project type : projectBean.getProjects()) {
            if (value.equals(type.getName())) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Project) {
            return ((Project) value).getName();
        } else {
            return "";
        }
    }

}
