package org.fpm.di;

import java.util.Map;
import java.util.HashMap;

public class EnvironmentClass implements Environment{
    private final Map<Class<?>, Class<?>> map_of_classes = new HashMap<>();
    private final Map<Class<?>, Object> map_of_instances = new HashMap<>();

    @Override
    public Container configure(Configuration configuration) {
        BinderClass binder = new BinderClass(map_of_classes, map_of_instances);
        configuration.configure(binder);
        return new ContainerClass(map_of_classes, map_of_instances);
    }
}