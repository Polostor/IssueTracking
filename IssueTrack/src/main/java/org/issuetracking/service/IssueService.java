package org.issuetracking.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;

@Stateless
public class IssueService extends GenericService<Issue, IssueDAO> {

    @Inject
    protected IssueDAO gDAO;

    @Override
    protected IssueDAO getDAO() {
        return gDAO;
    }
}
