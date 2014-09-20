package org.issuetracking.service;

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
    public void create(Project obj) {
        gDAO.create(obj);
    }

    @Override
    public void update(Project obj) {
        gDAO.update(obj);
    }
    
    public Project getObjById(long id) {
        return gDAO.find(id);
    }

    @Override
    public List<Project> getAllObjs() {
        return ((ProjectDAO) gDAO).findAll();
    }
}
