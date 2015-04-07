package org.issuetracking.service.generic;

import org.issuetracking.model.Project;
import org.issuetracking.dao.ProjectDAO;

public interface GenericProjectServiceInterface extends GenericSpecificServiceInterface<Project, ProjectDAO> {
}
