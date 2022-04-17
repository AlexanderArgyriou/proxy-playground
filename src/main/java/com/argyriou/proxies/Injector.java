package com.argyriou.proxies;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class Injector {
    ClassFinder classFinder = new ClassFinder();

    public void doInjections(Object object) {
        Set<Class<?>> classes = classFinder.loadAllClassesInClassPath("./target/classes" );
        doInjections( object, classes );
    }

    public void doInjections(Object object, Set<Class<?>> classes) {
        for ( Field field : object.getClass().getDeclaredFields() ) {
            Annotation[] annotations = field.getAnnotations();
            for ( Annotation annotation : annotations ) {
                if ( annotation instanceof Inject ) {
                    try {
                        Class<?> impl = findimpl( classes, field.getType() );
                        Constructor<?> constr = impl.getConstructor();
                        Object instance = constr.newInstance();
                        field.setAccessible( true );
                        field.set( object, ProxyCreator.getProxyClass(field.getType(), instance) );
                        doInjections( instance, classes );
                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Class<?> findimpl(Set<Class<?>> classes, Class<?> c) {
        for ( Class<?> clazz : classes ) {
            Class<?>[] interfaces = clazz.getInterfaces();
            for ( Class<?> intrf : interfaces ) {
                if ( intrf.isAssignableFrom( c ) ) {
                    return clazz;
                }
            }
        }
        return null;
    }
}
