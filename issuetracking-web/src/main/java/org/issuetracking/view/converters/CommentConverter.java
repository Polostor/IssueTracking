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

import org.issuetracking.model.Comment;
import org.issuetracking.view.CommentBean;

@FacesConverter(value = "commentConverter")
@Named("commentConverterBean")
public class CommentConverter implements Converter{    

    @PersistenceContext(unitName = "IssueTrackingPU")
    private transient EntityManager em;

    @Override
    public Comment getAsObject(FacesContext context, UIComponent component, String value) {
        CommentBean commentBean = context.getApplication().evaluateExpressionGet(context, "#{commentBean}", CommentBean.class);
        for (Comment type : commentBean.getComments()) {
            if (value.equals(type.getName())) {
                return type;
            }
        }
        return null;
        //return em.find(Comment.class, new Long(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Comment) {
            return ((Comment) value).getName();
        } else {
            return "";
        }
    }
    
}
