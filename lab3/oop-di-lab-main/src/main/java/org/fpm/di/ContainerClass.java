package org.fpm.di;

import java.util.Map;
import java.lang.reflect.Constructor;
import javax.inject.Inject;
import javax.inject.Singleton;

public class ContainerClass implements Container{
    private final Map<Class<?>, Class<?>> map_of_classes;
    private final Map<Class<?>, Object> map_of_instances;
    private int counter = 0;

    public ContainerClass(Map<Class<?>, Class<?>> map_of_classes, Map<Class<?>, Object> map_of_instances){
        this.map_of_classes = map_of_classes;
        this.map_of_instances = map_of_instances;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        if(this.map_of_instances.containsKey(clazz)){
            return (T) this.map_of_instances.get(clazz);
        } else if(this.map_of_classes.containsKey(clazz)){
            Class<T> clazzz = (Class<T>) this.map_of_classes.get(clazz);
            if(!clazzz.equals(clazz) &&
               (this.map_of_instances.containsKey(clazzz) || this.map_of_classes.containsKey(clazzz))){
                return this.getComponent(clazzz);
            }
            counter++;
            return this.getInstance(clazzz);
        }
        return null;
    }

    public <T> T getInstance(Class<T> clazz){
        T instance = null;
        try{
            instance = this.createInstance(clazz);
            if(clazz.getAnnotation(Singleton.class) != null){
                this.map_of_instances.put(clazz, instance);
            }
        }catch(Exception e){
        }
        return instance;
    }

    public <T> T createInstance(Class<T> clazz){
        try{
            Constructor[] constructors = clazz.getDeclaredConstructors();
            for(Constructor<?> constructor : constructors){
                if(constructor.getAnnotation(Inject.class) != null){
                    Class<?>[] type_of_parameters = constructor.getParameterTypes();
                    int len = type_of_parameters.length;
                    Object[] arguments = new Object[len];
                    for(int i = 0; i < len; i++){
                        if(counter > 15){
                            throw new CircleInjectionError("There is cycle in injection");
                        }
                        arguments[i] = this.getComponent(type_of_parameters[i]);
                    }
                    constructor.setAccessible(true);
                    T instance = (T) constructor.newInstance(arguments);
                    return instance;
                }
            }
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

class CircleInjectionError extends Exception{
    public CircleInjectionError(String msg){
        super(msg);
    }
}