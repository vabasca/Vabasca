package com.vabasca.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Vinh & Tri on 04/15/2019
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}