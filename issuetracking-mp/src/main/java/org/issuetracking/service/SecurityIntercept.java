package org.issuetracking.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.issuetracking.service.annotations.AllowedUser;
import org.issuetracking.service.annotations.DateEarlier;
import org.issuetracking.service.annotations.InRole;
import org.issuetracking.service.annotations.Length;
import org.issuetracking.service.annotations.LoggedIn;
import org.issuetracking.service.annotations.NotNull;
import org.issuetracking.service.annotations.NotSame;

import org.issuetracking.service.validators.AllowedUserValidator;
import org.issuetracking.service.validators.DateEarlierValidator;
import org.issuetracking.service.validators.InRoleValidator;
import org.issuetracking.service.validators.LengthValidator;
import org.issuetracking.service.validators.LoggedInValidator;
import org.issuetracking.service.validators.NotNullValidator;
import org.issuetracking.service.validators.NotSameValidator;

public class SecurityIntercept {

    @AroundInvoke
    private void doSecurityCheck(InvocationContext context) throws ValidationException {
        Method m = context.getMethod();
        Object[] params = context.getParameters();
        Object u = params[0];
        for (Annotation ann : m.getAnnotations()) {
            parseAnnotation(ann, context);
        }
    }

    private void parseAnnotation(Annotation ann, InvocationContext context) throws ValidationException {
        if (AllowedUser.class.cast(ann) != null) {
            AllowedUserValidator.validate(context, (AllowedUser) ann);
        }
        if (DateEarlier.class.cast(ann) != null) {
            DateEarlierValidator.validate(context, (DateEarlier) ann);
        }
        if (InRole.class.cast(ann) != null) {
            InRoleValidator.validate(context, (InRole) ann);
        }
        if (Length.class.cast(ann) != null) {
            LengthValidator.validate(context, (Length) ann);
        }
        if (LoggedIn.class.cast(ann) != null) {
            LoggedInValidator.validate(context, (LoggedIn) ann);
        }
        if (NotNull.class.cast(ann) != null) {
            NotNullValidator.validate(context, (NotNull) ann);
        }
        if (NotSame.class.cast(ann) != null) {
            NotSameValidator.validate(context, (NotSame) ann);
        }

    }
}
