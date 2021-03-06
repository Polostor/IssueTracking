package org.issuetracking.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.issuetracking.model.User;

@Stateless
public class UserDAO extends GenericDAO<User> {

    @Override
    public User find(long id) {
        User user = em.find(User.class, id);
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public User findByNick(String s) {
        Query q = em.createQuery("SELECT c FROM Looser c WHERE c.nick = :nick", User.class);
        q.setParameter("nick", s);
        User user = (User) q.getSingleResult();
        if (user == null) {
            user = new User();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> entries = em.createQuery("SELECT c FROM Looser c ORDER BY c.id ASC", User.class)
                .getResultList();
        if (entries == null) {
            entries = new ArrayList<User>();
        }
        return entries;
    }
}
