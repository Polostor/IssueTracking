package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.model.Looser;
import org.issuetracking.service.LooserService;

@Named(value = "looserBean")
@RequestScoped
public class LooserBean {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Inject
    private LooserService gServ;

    private Looser looser = new Looser();

    public List<Looser> getLoosers() {
        //System.out.println("here!");
        return gServ.getAllObjs();
    }

    public Looser getLooserById() {
        looser = gServ.getLooserById(id);
        return looser;
    }

    public Looser getLooser() {
        return looser;
    }

    public void setLooser(Looser looser) {
        this.looser = looser;
    }

    public String saveLooser() {
        gServ.create(looser);
        return "list.xhtml";
    }

    public String updateLooser() {
        gServ.update(looser);
        return "list.xhtml";
    }

    public String init() {
        //System.out.println("id - " + id);
        if (id == 0) {
            return "list.xhtml";
        }
        looser = gServ.getLooserById(id);
        return "";
    }

}
