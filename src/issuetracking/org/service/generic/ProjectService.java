package issuetracking.org.service.generic;

import issuetracking.org.dao.ProjectDAO;
import issuetracking.org.model.Project;

public class ProjectService extends GenericService<Project> {

    public ProjectService() {
        this.gDAO = new ProjectDAO();
    }
}
