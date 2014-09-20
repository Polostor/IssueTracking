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
    public Looser create(Looser obj) {
        em.persist(obj);
        return obj;
    }

    @Override
    public Looser find(long id) {
        final Query query = em.createQuery("SELECT b FROM Looser b WHERE b.id = :id")
                .setParameter("id", id);
        Looser lose = (Looser) query.getSingleResult();
        if (lose == null) {
            lose = new Looser();
        }
        return lose;
    }

    public Looser findByNick(String s) {
        Query query = em.createQuery("SELECT c FROM Looser c WHERE c.nick = :nick")
                .setParameter("nick", s);
        Looser lose = (Looser) query.getSingleResult();
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

    @Override
    public Looser update(Looser obj) {
        em.merge(obj);
        return obj;
    }

}
