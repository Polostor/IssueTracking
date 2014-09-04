package org.issuetracking.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.issuetracking.model.User;

/**
 *
 * @author peta
 */
@Stateless
public class UserDAO extends GenericDAO<User> {

    @Override
    public void create(User obj) {
        System.out.println(obj.toString());
        entityManager.persist(obj);
    }

    @Override
    public User find(int id) {
        Query userQuery = entityManager.createNamedQuery("User.findByIduser", User.class);
        userQuery.setParameter("iduser", id);
        return (User) userQuery.getSingleResult();
    }

    public User findByNick(String s) {
        Query userQuery = entityManager.createNamedQuery("User.findByNick", User.class);
        userQuery.setParameter("nick", s);
        return (User) userQuery.getSingleResult();
    }

    @Override
    public List<User> findAll() {
        Query query = entityManager.createQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Override
    public void update(User obj) {
        entityManager.merge(obj);
    }

    public void delete(User movie) throws Exception {
        entityManager.remove(movie);
    }

}
