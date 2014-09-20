package org.issuetracking.service;

import java.util.List;
import javax.inject.Inject;
import org.issuetracking.dao.LooserDAO;
import org.issuetracking.model.Looser;

public class LooserService extends GenericService<Looser> {

    Looser obj = new Looser();
    
    @Inject
    LooserDAO gDAO;

    public String saveLooser() {
        gDAO.create(this.obj);
        return "list.xhtml?faces-redirect=true";
    }

    public String editLooser() {
        gDAO.update(this.obj);
        return "list.xhtml?faces-redirect=true";
    }

    public void setLooser(Looser looser) {
        this.obj = looser;
    }

    public Looser getLooser() {
        return obj;
    }

    @Override
    public List<Looser> getAllObjs() throws Exception {
        setUp();
        System.out.println("HOORAY!");
        return ((LooserDAO) gDAO).findAll();
    }
}
