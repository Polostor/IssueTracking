package org.issuetracking.service.generic;

import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;
import org.issuetracking.model.Priority;

import org.issuetracking.service.ValidationException;

public abstract class GenericIssueServiceInterface extends GenericSpecificServiceInterface<Issue, IssueDAO> {   
    public abstract void setInProgress(Issue issue) throws ValidationException;     
    public abstract void setResolved(Issue issue) throws ValidationException;    
    public abstract void setPriority(Issue issue, Priority priority) throws ValidationException;
}
