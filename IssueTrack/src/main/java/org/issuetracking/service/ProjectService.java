package org.issuetracking.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.model.Project;
import org.issuetracking.dao.ProjectDAO;

@Stateless
public class ProjectService extends GenericService<Project, ProjectDAO> {

    @Inject
    protected ProjectDAO gDAO;

    @Override
    protected ProjectDAO getDAO() {
        return gDAO;
    }
}
