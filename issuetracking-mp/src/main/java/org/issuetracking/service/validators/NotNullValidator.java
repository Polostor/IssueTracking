package org.issuetracking.service.validators;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.InvocationContext;
import org.issuetracking.service.ValidationException;
import org.issuetracking.service.annotations.NotNull;

public class NotNullValidator {

    public static void validate(InvocationContext context, NotNull ann) throws ValidationException {
        Object[] params = context.getParameters();
        Object obj = params[0];
        if (ann.object() != null && !ann.object().isEmpty()) {
            try {
                Method method = obj.getClass().getMethod(ann.object());
                obj = method.invoke(obj);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(NotNullValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(NotNullValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(NotNullValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(NotNullValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(NotNullValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (obj == null) {
            throw new ValidationException("You have to select " + ann.input() + ".");
        }
    }
}
