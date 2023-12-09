package org.fpm.di.example;

import javax.inject.Inject;

public class D extends B{
    private A a;
    private B b;

    @Inject
    public D(A a, B b){
        this.a = a;
        this.b = b;
    }
}
