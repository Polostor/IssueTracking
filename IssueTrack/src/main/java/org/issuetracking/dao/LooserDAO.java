package org.issuetracking.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.issuetracking.model.Looser;

/**
 *
 * @author peta
 */
@Stateless
public class LooserDAO extends GenericDAO<Looser> {

    @Override
    public Looser find(long id) {
        Looser lose = em.find(Looser.class, id);
        if (lose == null) {
            lose = new Looser();
        }
        return lose;
    }

    public Looser findByNick(String s) {
        Looser lose = em.find(Looser.class, s);
        if (lose == null) {
            lose = new Looser();
        }
        return lose;
    }

    @Override
    public List<Looser> findAll() {
        List<Looser> entries = em.createQuery("SELECT c FROM Looser c ORDER BY c.id ASC")
                .getResultList();
        if (entries == null) {
            entries = new ArrayList<Looser>();
        }
        return entries;
    }
}
