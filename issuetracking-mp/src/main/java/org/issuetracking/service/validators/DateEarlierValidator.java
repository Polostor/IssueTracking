package org.issuetracking.service.validators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.InvocationContext;
import org.issuetracking.service.ValidationException;
import java.util.Date;
import org.issuetracking.service.SecurityIntercept;
import org.issuetracking.service.annotations.DateEarlier;

public class DateEarlierValidator {

    public static void validate(InvocationContext context, DateEarlier ann) throws ValidationException {
        Object[] params = context.getParameters();
        Object u = params[0];
        try {
            Method method = u.getClass().getMethod(ann.date());
            Date date = (Date) method.invoke(u);
            if (ann.date() == null || (new Date()).before(date)) {
                throw new ValidationException("You have to set date in past.");
            }
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

    }
}
