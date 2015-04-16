package org.issuetracking.service.generic;

import org.issuetracking.dao.GenericDAO;

public abstract class GenericSpecificServiceInterface<E, TypeOfDAO extends GenericDAO> extends GenericService<E, TypeOfDAO>{    
}
