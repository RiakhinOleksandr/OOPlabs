package org.fpm.di.example;

import org.fpm.di.Container;
import org.fpm.di.BinderClass;
import org.fpm.di.EnvironmentClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class Example {

    private Container container;
    private BinderClass binder;

    @Before
    public void setUp() {
        EnvironmentClass env = new EnvironmentClass();
        container = env.configure(new MyConfiguration());
        binder = env.get_binder();
    }

    @Test
    public void shouldInjectSingleton() {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() {
        binder.bind(A.class, B.class);
        binder.bind(B.class, new B());
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() {
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }
}