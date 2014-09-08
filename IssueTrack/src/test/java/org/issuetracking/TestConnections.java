/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;
import org.issuetracking.service.base.UserService;
import org.junit.Test;

/**
 *
 * @author peta
 */
public class TestConnections {

    @Test
    public void testNewConnection() {
        System.out.println("New Connection Test");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("TEST: Where is MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        //System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/issuetrack", "root", "pass");

        } catch (SQLException e) {
            System.out.println("TEST: Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("TEST: Everything was done just well enought!");
        } else {
            System.out.println("TEST: Failed to make connection!");
        }
        System.out.println("---------------------------------------\n");
    }

    @Test
    public void testUserService() {
        System.out.println("User Service Test");
        UserService us = new UserService();
        // TODO - Proc the DB
        System.out.println("TEST: Everything was done just well enought!");
        System.out.println("---------------------------------------\n");
    }

    @Test
    public void testQueryUserService() {
        System.out.println("(User Service) getAll Test");
        UserService us;
        us = new UserService();
        try {
            // TODO - find the problem!
            // Comment Adding to change it to normal (run) mode
            
            // Added in UserService !public! gDAO
            us.gDAO = new UserDAO();
            System.out.println("UserDAO OKey");
            
            // Added in UserDAO !public! entityManager
            us.gDAO.entityManager = Persistence.createEntityManagerFactory(null).createEntityManager();
            System.out.println("EntityManager OKey");
            
            // If problem is there, it'll be most probably NamedQuery - SELECT u FROM User u
            List<User> l = us.getAll();
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            for (User u : l) {
                System.out.println(u.toString());
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("MY DB failed me! Check Hibernate.");
            System.out.println("---------------------------------------\n");
            return;
        }
        System.out.println("TEST: Everything was done just well enought!");
        System.out.println("---------------------------------------\n");
    }

    @Test
    public void testAllUsersService() {
        System.out.println("(Persistence) getAll Users Test");
        EntityManager entityManager = Persistence.createEntityManagerFactory(null).createEntityManager();

        Query query = entityManager.createQuery("SELECT u FROM User u", User.class);
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        List<User> l = query.getResultList();
        for (User u : l) {
            System.out.println(u.toString());
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++");

        entityManager.close();
        // TODO - Proc the DB
        System.out.println("TEST: Everything was done just well enought!");
        System.out.println("---------------------------------------\n");
    }

}
