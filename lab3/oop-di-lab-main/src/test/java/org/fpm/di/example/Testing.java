package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.BinderClass;
import org.fpm.di.EnvironmentClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class Testing {
    private EnvironmentClass env;
    private Container container;
    private BinderClass binder;

    @Before
    public void setUp(){
        env = new EnvironmentClass();
    }

    @Test
    public void shouldBuildInjectionGraph1() {
        container = env.configure(new AnotherConfiguration());
        binder = env.get_binder();
        binder.bind(A.class, B.class);
        final C cAsSingleton = container.getComponent(C.class);
        assertSame(container.getComponent(A.class), cAsSingleton);
        assertSame(container.getComponent(B.class), cAsSingleton);
        assertSame(container.getComponent(C.class), cAsSingleton);
    }

    @Test
    public void shouldBuildInjectionGraph2() {
        container = env.configure(new AnotherConfiguration2());
        binder = env.get_binder();
        binder.bind(A.class, B.class);
        final D dAsSingleton = container.getComponent(D.class);
        assertSame(container.getComponent(A.class), dAsSingleton);
        assertSame(container.getComponent(B.class), dAsSingleton);
        assertSame(container.getComponent(D.class), dAsSingleton);
    }

    @Test
    public void check_with_numbers(){
        env = new EnvironmentClass();
        container = env.configure(new Configuration2());
        binder = env.get_binder();
        binder.bind(Number.class, Integer.class);
        binder.bind(Integer.class, 5);
        assertSame(container.getComponent(Number.class), 5);
    }
}
