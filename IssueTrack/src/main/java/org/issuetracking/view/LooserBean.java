package org.issuetracking.view;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.issuetracking.dao.LooserDAO;
import org.issuetracking.model.Looser;

@Named(value = "looserBean")
@RequestScoped
public class LooserBean {
    private long id;
    
    public long getId(){
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    @Inject
    private LooserDAO gDAO;

    private Looser looser = new Looser();

    public List<Looser> getLoosers() {
        return gDAO.findAll();
    }
    
    public Looser getLooserById() {
        looser = gDAO.find(id);
        return looser;
    }

    public Looser getLooser() {
        return looser;
    }
    
    public void setLooser(Looser looser) {
        this.looser = looser;
    }

    public String saveLooser() {
        gDAO.create(looser);
        return "list.xhtml";
    }
    
    public void  init(){
        System.out.println("id - " +id);
        looser = gDAO.find(id);
    }

}
