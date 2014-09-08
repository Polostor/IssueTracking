package org.issuetracking.service.base;

import java.util.List;
import javax.ejb.Stateless;
import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;

@Stateless
public class IssueService extends GenericService<Issue> {

    IssueDAO gDAO;

    @Override
    public boolean create(Issue obj) throws Exception {
        gDAO.create(obj);
        // TODO change as it is updted or not
        return true;
    }

    @Override
    public Issue getObj(int id) throws Exception {
        return (Issue) gDAO.find(id);
    }

    @Override
    public List<Issue> getAll() throws Exception {
        return gDAO.findAll();
    }

    @Override
    public boolean update(Issue o) throws Exception {
        gDAO.update(o);
        // TODO change as it is updted or not
        return true;
    }
}
