package org.issuetracking.service.base;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.issuetracking.dao.IssueDAO;
import org.issuetracking.model.Issue;

@ManagedBean
@ViewScoped
public class IssueService extends GenericService<Issue> implements Serializable {

    @EJB
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
