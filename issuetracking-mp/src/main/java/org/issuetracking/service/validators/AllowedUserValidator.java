package org.issuetracking.service.validators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.InvocationContext;

import org.issuetracking.model.User;
import org.issuetracking.service.SecurityIntercept;
import org.issuetracking.service.ValidationException;
import org.issuetracking.service.annotations.AllowedUser;
import org.issuetracking.view.iface.PrincipalBeanInterface;

public class AllowedUserValidator {

    public static void validate(InvocationContext context, AllowedUser ann) throws ValidationException {
        Object[] params = context.getParameters();
        Object u = params[0];
        User user = null;
        if (!"".equals(ann.id1Method())) {
            try {
                Method method = u.getClass().getMethod(ann.id1Method());
                user = (User) method.invoke(u);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(SecurityIntercept.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(SecurityIntercept.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SecurityIntercept.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(SecurityIntercept.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(SecurityIntercept.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            user = (User) u;
        }
        PrincipalBeanInterface principal = PrincipalBeanInterface.class.cast(params[1]);
        if (user == null || !user.getId().equals(principal.getId())) {
            throw new ValidationException("You are not allowed.");
        }
    }
}
