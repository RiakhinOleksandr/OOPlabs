package org.fpm.di.example;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;

public class AnotherConfiguration implements Configuration {
    @Override
    public void configure(Binder binder) {
        binder.bind(MySingleton.class);
        binder.bind(MyPrototype.class);

        binder.bind(UseA.class);

        binder.bind(A.class, B.class);
        binder.bind(B.class, C.class);
        binder.bind(C.class, new C());
    }
}
