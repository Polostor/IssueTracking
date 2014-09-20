package org.issuetracking.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
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
        Looser loos;
        try {
            loos = em.createQuery("SELECT c FROM Looser c WHERE c.id = :id", Looser.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            loos = new Looser();
        }
        return loos;
    }

    public Looser findByNick(String s) {
        Query query = em.createQuery("SELECT c FROM Looser c WHERE c.nick = :nick")
                .setParameter("nick", s);
        Looser loos = (Looser) query.getSingleResult();
        if (loos == null) {
            loos = new Looser();
        }
        return loos;
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
