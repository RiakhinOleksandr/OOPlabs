package org.fpm.di;

import java.util.Map;

public class BinderClass implements Binder {
    private final Map<Class<?>, Class<?>> map_of_classes;
    private final Map<Class<?>, Object> map_of_instances;

    public BinderClass(Map<Class<?>, Class<?>> map_of_classes, Map<Class<?>, Object> map_of_instances){
        this.map_of_classes = map_of_classes;
        this.map_of_instances = map_of_instances;
    }

    @Override
    public <T> void bind(Class<T> clazz) {
        this.bind(clazz, clazz);
    }
    
    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        try{
            if(clazz != null && implementation != null) {
                if (!this.map_of_classes.containsKey(clazz) && !this.map_of_instances.containsKey(clazz)) {
                    this.map_of_classes.put(clazz, implementation);
                } else {
                    throw new AlreadyBinded("You have already binded " + clazz);
                }
            }
        } catch(AlreadyBinded e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        try{
            if(clazz != null && instance != null) {
                if (!this.map_of_classes.containsKey(clazz) && !this.map_of_instances.containsKey(clazz)) {
                    this.map_of_instances.put(clazz, instance);
                } else {
                    throw new AlreadyBinded("You have already binded " + clazz);
                }
            }
        } catch(AlreadyBinded e){
            System.out.println(e.getMessage());
        }
    }
}

class AlreadyBinded extends Exception{
    public AlreadyBinded(String msg){
        super(msg);
    }
}