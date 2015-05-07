/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking.service.Validation;

import java.util.Date;
import org.issuetracking.model.User;
import org.issuetracking.service.ValidationException;
import org.issuetracking.view.iface.PrincipalBeanInterface;

/**
 *
 * @author peta
 */
public class Validator {

    public static void isLoggedIn(PrincipalBeanInterface pb) throws ValidationException {
        if (!pb.isLogged()) {
            throw new ValidationException("You must be logged in.");
        }
    }

    public static void isNotLoggedIn(PrincipalBeanInterface pb) throws ValidationException {
        if (pb.isLogged()) {
            throw new ValidationException("You can't be logged in.");
        }
    }

    public static void isAllowedUser(User user, PrincipalBeanInterface pb) throws ValidationException {
        if (!user.getId().equals(pb.getId())) {
            throw new ValidationException("You are not allowed.");
        }
    }

    public static void isOneOfAllowedUsers(User user1, User user2, PrincipalBeanInterface pb) throws ValidationException {
        if (!(user1.getId().equals(pb.getId()) || user2.getId().equals(pb.getId()))) {
            throw new ValidationException("You are not allowed.");
        }
    }

    public static void isBetween(String text, int min, int max, String input) throws ValidationException {
        if (text == null || text.length() < min || text.length() > max) {
            throw new ValidationException("The " + input + "'s length has to be between " + min + " and " + max + ".");
        }
    }

    public static void isNotNull(Object obj, String input) throws ValidationException {
        if (obj == null) {
            throw new ValidationException("You have to select " + input + ".");
        }
    }

    public static void isNotSame(Object obj1, Object obj2, String input) throws ValidationException {
        if (obj2.equals(obj1)) {
            throw new ValidationException("The " + input + " is already set to \"" + obj2.toString() + "\".");
        }
    }

    public static void isDateEarlier(Date date) throws ValidationException {
        if (date == null || date.before(new Date())) {
            throw new ValidationException("You have to set date in past.");
        }
    }
}
