package org.fpm.di.example;

import org.fpm.di.Binder;
import org.fpm.di.Configuration;

public class Configuration2 implements Configuration{
    @Override
    public void configure(Binder binder) {
        binder.bind(MySingleton.class);
        binder.bind(MyPrototype.class);

        binder.bind(UseA.class);
    }
}
