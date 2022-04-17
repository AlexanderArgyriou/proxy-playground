package com.argyriou.proxies;

import com.argyriou.customannot.CustomMethodLevelAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyCreator {
    public static Object getProxyClass(Class<?> clazz, Object impl) {
        return Proxy.newProxyInstance( clazz.getClassLoader(), new Class[]{ clazz }, (proxy, method, args) -> {
            for ( Method m : impl.getClass().getMethods() ) {
                if ( m.getName().equals( method.getName() ) && m.getParameterCount() == method.getParameterCount() ) {
                    try {
                        for ( Annotation annotation : m.getAnnotations() ) {
                            if ( annotation instanceof CustomMethodLevelAnnotation ) {
                                System.out.println( "I am invoking a method with a custom method level annotation" );
                            }
                        }
                        System.out.println( "invoked from proxy" );
                        return m.invoke( impl, args );
                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        } );
    }
}
