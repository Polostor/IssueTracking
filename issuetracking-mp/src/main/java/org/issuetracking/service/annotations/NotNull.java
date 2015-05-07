package org.issuetracking.service.annotations;

import org.issuetracking.service.annotations.multi.NotNulls;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = NotNulls.class)
public @interface NotNull {

    String object() default "";

    String input() default "";
}
