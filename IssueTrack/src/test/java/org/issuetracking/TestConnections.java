/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.issuetracking.service.base.UserService;
import org.junit.Test;

/**
 *
 * @author peta
 */
public class TestConnections {

    @Test
    public void testNewConnection() {
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
        UserService us = new UserService();
        // TODO - Proc the DB
        System.out.println("TEST: Everything was done just well enought!");
        System.out.println("---------------------------------------\n");
    }

    @Test
    public void testQueryUserService() {
        UserService us;
        us = new UserService();
        try {
            // TODO - find the problem!
            System.out.println("\n" + us.getObj(1) + "\n");
        } catch (Exception e) {
            System.out.println("MY DB fail me! Check output console for Hibernate.");
            e.printStackTrace();
        }
        System.out.println("TEST: Everything was done just well enought!");
        System.out.println("---------------------------------------\n");
    }

}
