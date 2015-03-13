/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
<<<<<<< HEAD
import org.issuetracking.service.LooserService;
=======
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.issuetracking.dao.UserDAO;
import org.issuetracking.model.User;
import org.issuetracking.service.base.UserService;
>>>>>>> origin/master
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
            Class.forName("org.postgresql.Driver");
            System.out.println("TEST: We have JDBC Driver for PGSQL.");
        } catch (ClassNotFoundException e) {
            System.out.println("TEST: Where is PGSQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        //System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/issuetrack", "postgres", "apassword");
            System.out.println("TEST: We have have connected to /issuetrack database.");
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
<<<<<<< HEAD
    public void testLooserService() {
        LooserService us;
        try {
            us = new LooserService();
        } catch (Exception e) {
            us = new LooserService();
            System.out.println("MY Services fail me! Check output console for Hibernate.");
            e.printStackTrace();
        }
=======
    public void testUserService() {
        System.out.println("User Service Test");
        UserService us = new UserService();
        // TODO - Proc the DB
>>>>>>> origin/master
        System.out.println("TEST: Everything was done just well enought!");
        System.out.println("---------------------------------------\n");
    }

    @Test
<<<<<<< HEAD
    public void testQueryLooserService() {
        LooserService us;
        try {
            us = new LooserService();
            try {
                System.out.println("\n" + us.getObj(1).toString() + "\n");
            } catch (Exception e) {
                System.out.println("MY DB fail me! Check output console for Hibernate.");
                e.printStackTrace();
            }
=======
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
>>>>>>> origin/master
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
<<<<<<< HEAD
    public void testMultipleQueryLooserService() {
        LooserService us;
        try {
            us = new LooserService();
            try {
                System.out.println("\n" + us.getAllObjs().toString() + "\n");
            } catch (Exception e) {
                System.out.println("MY DB fail me! Check output console for Hibernate.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("MY Services fail me! Check output console for Hibernate.");
            e.printStackTrace();
=======
    public void testAllUsersService() {
        System.out.println("(Persistence) getAll Users Test");
        EntityManager entityManager = Persistence.createEntityManagerFactory(null).createEntityManager();

        Query query = entityManager.createQuery("SELECT u FROM User u", User.class);
        System.out.println("+++++++++++++++++++++++++++++++++++++++");
        List<User> l = query.getResultList();
        for (User u : l) {
            System.out.println(u.toString());
>>>>>>> origin/master
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++");

        entityManager.close();
        // TODO - Proc the DB
        System.out.println("TEST: Everything was done just well enought!");
        System.out.println("---------------------------------------\n");
    }

}
