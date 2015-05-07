package org.issuetracking.service.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoggedIn {

    String input() default "";
    
    /**
     * Determine if user should be Logged in (true) or not (false)
     * @return boolean value to control
     */
    boolean LoggedIn() default true;
}
