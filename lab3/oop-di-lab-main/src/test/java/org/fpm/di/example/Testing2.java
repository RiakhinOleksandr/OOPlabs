package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.BinderClass;
import org.fpm.di.EnvironmentClass;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotNull;


public class Testing2 {
    private EnvironmentClass env;
    private Container container;
    private BinderClass binder;

    @Test
    public void check_interaction_with_null(){
        env = new EnvironmentClass();
        container = env.configure(new AnotherConfiguration());
        binder = env.get_binder();
        binder.bind(null);
        binder.bind(null, new C());
        final C cAsSingleton = container.getComponent(C.class);
        assertSame(container.getComponent(A.class), cAsSingleton);
        assertSame(container.getComponent(B.class), cAsSingleton);
    }

    @Test
    public void check_interaction_with_many_instances(){
        env = new EnvironmentClass();
        container = env.configure(new Configuration2());
        binder = env.get_binder();
        binder.bind(A.class, new A());
        binder.bind(B.class, new B());
        binder.bind(D.class, new D(new A(), new B()));
        final D dAsSingleton = container.getComponent(D.class);
        assertSame(container.getComponent(D.class), dAsSingleton);
    }

    @Test
    public void check_interaction_without_instances(){
        env = new EnvironmentClass();
        container = env.configure(new Configuration2());
        binder = env.get_binder();
        binder.bind(A.class, B.class);
        binder.bind(B.class, C.class);
        binder.bind(C.class);
        final C cAsSingleton = container.getComponent(C.class);
        assertNotNull(cAsSingleton);
    }

    @Test
    public void check_for_circle(){
        env = new EnvironmentClass();
        container = env.configure(new Configuration2());
        binder = env.get_binder();
        binder.bind(A.class, new A());
        binder.bind(C.class, E.class);
        binder.bind(E.class, new E(new A()));
        final E eAsSingleton = container.getComponent(E.class);
        assertSame(container.getComponent(C.class), eAsSingleton);
        assertSame(container.getComponent(E.class), eAsSingleton);
    }

    @Test
    public void check_for_circle2(){
        env = new EnvironmentClass();
        container = env.configure(new Configuration2());
        binder = env.get_binder();
        binder.bind(A.class, C.class);
        binder.bind(C.class, E.class);
        binder.bind(E.class);
        final E eAsSingleton = container.getComponent(E.class);
        assertSame(container.getComponent(E.class), eAsSingleton);
    }
}
