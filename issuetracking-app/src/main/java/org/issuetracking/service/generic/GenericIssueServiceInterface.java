package org.issuetracking.service.generic;

import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;
import org.issuetracking.model.Priority;

import org.issuetracking.service.ValidationException;
import org.issuetracking.view.iface.PrincipalBeanInterface;

public abstract class GenericIssueServiceInterface extends GenericSpecificServiceInterface<Issue, IssueDAO> {   
    public abstract void setInProgress(Issue issue, PrincipalBeanInterface pb) throws ValidationException;     
    public abstract void setResolved(Issue issue, PrincipalBeanInterface pb) throws ValidationException;    
    public abstract void setPriority(Issue issue, PrincipalBeanInterface pb, Priority priority) throws ValidationException;
}
