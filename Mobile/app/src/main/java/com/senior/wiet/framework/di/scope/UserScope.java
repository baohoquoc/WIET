package com.senior.wiet.framework.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by lance on 18/February/2020.
 */
@Scope
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface UserScope {
}
