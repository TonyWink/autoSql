package com.wink.sql.annonations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface ID {
	  boolean identity() default false;
}
