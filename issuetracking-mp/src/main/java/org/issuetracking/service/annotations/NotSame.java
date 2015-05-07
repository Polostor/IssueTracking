package org.issuetracking.service.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotSame {

    int object();
    
    /**
     * In case real object is parameter in method
     * @return real objects parameter place
     */
    int realObjectInt() default -1;
    
    /**
     * In case real object is String text
     * @return real objects String text
     */
    String realObjectString() default "";

    String input() default "";
}
