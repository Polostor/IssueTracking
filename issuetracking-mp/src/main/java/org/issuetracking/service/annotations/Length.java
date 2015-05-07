package org.issuetracking.service.annotations;

import org.issuetracking.service.annotations.multi.Lengths;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = Lengths.class)
public @interface Length {
    
    int param() default 0;

    String param1() default "";

    int min();

    int max();

    String input() default "";
}
