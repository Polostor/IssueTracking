package org.issuetracking.service.annotations.multi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.issuetracking.service.annotations.NotNull;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNulls {

    NotNull[] value();
}
