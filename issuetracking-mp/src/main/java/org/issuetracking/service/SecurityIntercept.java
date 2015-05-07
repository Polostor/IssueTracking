package org.issuetracking.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.issuetracking.service.annotations.AllowedUser;
import org.issuetracking.service.annotations.DateEarlier;
import org.issuetracking.service.annotations.InRole;
import org.issuetracking.service.annotations.Length;
import org.issuetracking.service.annotations.LoggedIn;
import org.issuetracking.service.annotations.NotNull;
import org.issuetracking.service.annotations.NotSame;
import org.issuetracking.service.annotations.OneOfAllowedUsers;
import org.issuetracking.service.annotations.multi.Lengths;
import org.issuetracking.service.annotations.multi.NotNulls;

import org.issuetracking.service.validators.AllowedUserValidator;
import org.issuetracking.service.validators.DateEarlierValidator;
import org.issuetracking.service.validators.InRoleValidator;
import org.issuetracking.service.validators.LengthValidator;
import org.issuetracking.service.validators.LoggedInValidator;
import org.issuetracking.service.validators.NotNullValidator;
import org.issuetracking.service.validators.NotSameValidator;
import org.issuetracking.service.validators.OneOfAllowedUsersValidator;

public class SecurityIntercept {

    @AroundInvoke
    private Object doSecurityCheck(InvocationContext context) throws ValidationException, Exception {
        Method m = context.getMethod();
        Object[] params = context.getParameters();
        Object u = params[0];
        Logger logger = Logger.getLogger(getClass().getName());
        for (Annotation ann : m.getAnnotations()) {
            logger.warning(ann.toString());
            parseAnnotation(ann, context);
        }
        logger.warning("OK");
        return context.proceed();
    }

    private void parseAnnotation(Annotation ann, InvocationContext context) throws ValidationException {
        if (AllowedUser.class.isInstance(ann)) {
            AllowedUserValidator.validate(context, (AllowedUser) ann);
        }
        if (DateEarlier.class.isInstance(ann)) {
            DateEarlierValidator.validate(context, (DateEarlier) ann);
        }
        if (InRole.class.isInstance(ann)) {
            InRoleValidator.validate(context, (InRole) ann);
        }
        if (Lengths.class.isInstance(ann)) {
            for (Length length : ((Lengths) ann).value()) {
                LengthValidator.validate(context, length);
            }
        }
        if (LoggedIn.class.isInstance(ann)) {
            LoggedInValidator.validate(context, (LoggedIn) ann);
        }
        if (NotNulls.class.isInstance(ann)) {
            for (NotNull notNull : ((NotNulls) ann).value()) {
                NotNullValidator.validate(context, notNull);
            }
        }
        if (NotSame.class.isInstance(ann)) {
            NotSameValidator.validate(context, (NotSame) ann);
        }
        if (OneOfAllowedUsers.class.isInstance(ann)) {
            OneOfAllowedUsersValidator.validate(context, (OneOfAllowedUsers) ann);
        }

    }
}
