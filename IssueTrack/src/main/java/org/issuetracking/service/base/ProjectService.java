package org.issuetracking.service.base;

import java.util.List;
import javax.ejb.Stateless;
import org.issuetracking.dao.ProjectDAO;
import org.issuetracking.model.Project;

@Stateless
public class ProjectService extends GenericService<Project> {

    ProjectDAO gDAO;

    @Override
    public boolean create(Project obj) throws Exception {
        gDAO.create(obj);
        // TODO change as it is updted or not
        return true;
    }

    @Override
    public Project getObj(int id) throws Exception {
        return (Project) gDAO.find(id);
    }

    @Override
    public List<Project> getAll() throws Exception {
        return gDAO.findAll();
    }

    @Override
    public boolean update(Project o) throws Exception {
        gDAO.update(o);
        // TODO change as it is updted or not
        return true;
    }
}
