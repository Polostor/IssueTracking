package org.issuetracking.service.validators;

import javax.interceptor.InvocationContext;
import org.issuetracking.service.ValidationException;
import org.issuetracking.service.annotations.InRole;
import org.issuetracking.view.iface.PrincipalBeanInterface;

public class InRoleValidator {

    public static void validate(InvocationContext context, InRole ann) throws ValidationException {
        Object[] params = context.getParameters();
        PrincipalBeanInterface principal = PrincipalBeanInterface.class.cast(params[1]);
        for (String role : ann.role()) {
            if (role.equals(principal.getRole().toString())) {
                return;
            }
        }
        throw new ValidationException("You are not in allowed role.");

    }
}
