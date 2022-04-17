package com.argyriou.services;

import com.argyriou.beans.BeanExampleIf;
import com.argyriou.customannot.CustomMethodLevelAnnotation;

import javax.inject.Inject;

public class BeanService implements BeanServiceIf {
    @Inject
    BeanExampleIf beanExample;

    @Override
    @CustomMethodLevelAnnotation
    public void doBusiness() {
        beanExample.doSomething();
    }
}
