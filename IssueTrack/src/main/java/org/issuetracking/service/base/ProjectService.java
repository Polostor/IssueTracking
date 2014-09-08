package org.issuetracking.service.base;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.issuetracking.dao.ProjectDAO;
import org.issuetracking.model.Project;

@ManagedBean
@ViewScoped
public class ProjectService extends GenericService<Project> implements Serializable {

    @EJB
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
