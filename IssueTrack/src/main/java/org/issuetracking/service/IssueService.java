package org.issuetracking.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;

@Stateless
public class IssueService extends GenericService<Issue> {
    
    @Inject
    IssueDAO gDAO;

    @Override
    public void create(Issue obj) {
        gDAO.create(obj);
    }

    @Override
    public void update(Issue obj) {
        gDAO.update(obj);
    }
    
    public Issue getObjById(long id) {
        return gDAO.find(id);
    }

    @Override
    public List<Issue> getAllObjs() {
        return ((IssueDAO) gDAO).findAll();
    }
}
