package org.issuetracking.service.base;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.issuetracking.dao.ProjectDAO;
import org.issuetracking.model.Project;

@Stateless
public class ProjectService extends GenericService<Project> {
    
    @Inject
    ProjectDAO gDAO;

    @Override
    public boolean createObj(Project obj) throws Exception {
        gDAO.create(obj);
        // TODO change as it is updted or not
        return true;
    }

    @Override
    public Project getObj(int id) throws Exception {
        return (Project) gDAO.find(id);
    }

    @Override
    public List<Project> getAllObjs() throws Exception {
        return gDAO.findAll();
    }

    @Override
    public boolean updateObj(Project o) throws Exception {
        gDAO.update(o);
        // TODO change as it is updted or not
        return true;
    }

//    @Override
//    public void setUp() throws Exception {
//        gDAO = new ProjectDAO();
//        super.setUp();
//    }
}
