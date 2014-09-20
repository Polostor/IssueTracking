/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.issuetracking.service.LooserService;
import org.junit.Test;

/**
 *
 * @author peta
 */
public class TestConnections {

    @Test
    public void testNewConnection() {
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
                    .getConnection("jdbc:postgresql://localhost:5432/issuetrack", "peta", "pass");
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
    public void testLooserService() {
        LooserService us;
        try {
            us = new LooserService();
        } catch (Exception e) {
            us = new LooserService();
            System.out.println("MY Services fail me! Check output console for Hibernate.");
            e.printStackTrace();
        }
        System.out.println("TEST: Everything was done just well enought!");
        System.out.println("---------------------------------------\n");
    }

    @Test
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
        } catch (Exception e) {
            System.out.println("MY Services fail me! Check output console for Hibernate.");
            e.printStackTrace();
        }
        System.out.println("TEST: Everything was done just well enought!");
        System.out.println("---------------------------------------\n");
    }

    @Test
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
        }
        System.out.println("TEST: Everything was done just well enought!");
        System.out.println("---------------------------------------\n");
    }

}
