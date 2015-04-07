package org.issuetracking.service.generic;

import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;
import org.issuetracking.model.Priority;

import org.issuetracking.service.ValidationException;

public interface GenericIssueServiceInterface extends GenericSpecificServiceInterface<Issue, IssueDAO> {   
    public void setInProgress(Issue issue) throws ValidationException;     
    public void setResolved(Issue issue) throws ValidationException;    
    public void setPriority(Issue issue, Priority priority) throws ValidationException;
}
