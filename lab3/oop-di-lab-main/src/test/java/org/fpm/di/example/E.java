package org.fpm.di.example;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class E extends C{
    private A a;

    @Inject
    public E(A a){
        this.a = a;
    }
}
