package org.issuetracking.service.validators;

import javax.interceptor.InvocationContext;
import org.issuetracking.service.ValidationException;
import org.issuetracking.service.annotations.LoggedIn;
import org.issuetracking.view.iface.PrincipalBeanInterface;

public class LoggedInValidator {

    public static void validate(InvocationContext context, LoggedIn ann) throws ValidationException {
        Object[] params = context.getParameters();
        PrincipalBeanInterface principal = PrincipalBeanInterface.class.cast(params[1]);
        if (ann.LoggedIn() != principal.isLogged()) {
            throw new ValidationException("You must be logged in.");
        }
    }
}
