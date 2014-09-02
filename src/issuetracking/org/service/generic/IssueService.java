package issuetracking.org.service.generic;

import issuetracking.org.dao.IssueDAO;
import issuetracking.org.model.Issue;

public class IssueService extends GenericService<Issue> {

    public IssueService() {
        this.gDAO = new IssueDAO();
    }

    public boolean setStatus(int issueId, int status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
