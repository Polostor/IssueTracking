package org.issuetracking.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.issuetracking.dao.LooserDAO;
import org.issuetracking.model.Looser;

@Stateless
public class LooserService extends GenericService<Looser> {
    
    @Inject
    LooserDAO gDAO;

    @Override
    public void create(Looser obj) {
        gDAO.create(obj);
    }

    @Override
    public void update(Looser obj) {
        gDAO.update(obj);
    }
    
    public Looser getObjById(long id) {
        return gDAO.find(id);
    }

    @Override
    public List<Looser> getAllObjs() {
        return gDAO.findAll();
    }
}
