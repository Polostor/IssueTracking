/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking.service.validators;

import org.issuetracking.service.annotations.Length;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.InvocationContext;
import org.issuetracking.service.SecurityIntercept;
import org.issuetracking.service.ValidationException;

/**
 *
 * @author peta
 */
public class LengthValidator {

    public static void validate(InvocationContext context, Length ann) throws ValidationException {
        Object[] params = context.getParameters();
        Object u = params[ann.param()];
        int text = 0;
        try {
            Method method = u.getClass().getMethod(ann.param1());
            text = ((String) method.invoke(u)).length();
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
        if ( !(text >= ann.min() && text <= ann.max()) ) {
            throw new ValidationException(ann.input() + " length has to be between "
                    + ann.min() + " and " + ann.max() + ".");
        }

    }
}
