/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking.service;

import org.issuetracking.model.User;

/**
 *
 * @author peta
 */
public class Validator {

    private static User principal = null;

    public static void isLoggedIn(String thing) throws ValidationException {
        if (principal == null) {
            throw new ValidationException("You must be logged in to " + thing + ".");
        }
    }

    public static void isNotLoggedIn(String thing) throws ValidationException {
        if (principal != null) {
            throw new ValidationException("You can't be logged in to " + thing + ".");
        }
    }

    public static void isAllowedUser(User user, String thing) throws ValidationException {
        if (!principal.equals(user)) {
            throw new ValidationException("You are not allowed to " + thing +".");
        }
    }

    public static void isOneOfAllowedUsers(User user1, User user2, String thing) throws ValidationException {
        if (!(principal.equals(user1) || principal.equals(user2))) {
            throw new ValidationException("You are not allowed to " + thing +".");
        }
    }
}
