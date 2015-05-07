/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.issuetracking.service.validators;

import javax.interceptor.InvocationContext;
import org.issuetracking.service.ValidationException;
import org.issuetracking.service.annotations.NotSame;

/**
 *
 * @author peta
 */
public class NotSameValidator {

    public static void validate(InvocationContext context, NotSame ann) throws ValidationException {
        Object[] params = context.getParameters();
        Object object = ann.realObjectString();
        if(ann.realObjectInt() != -1){
            object = params[ann.realObjectInt()];
        }
        if (object.equals(ann.object())) {
            throw new ValidationException("The " + ann.input() + " is already set to \"" + object.toString() + "\".");
        }

    }
}
