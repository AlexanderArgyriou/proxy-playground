package com.argyriou.app;

import com.argyriou.services.BeanServiceIf;

import javax.inject.Inject;

public class App {
    @Inject
    BeanServiceIf beanService;

    public void run(String[] args) {
        beanService.doBusiness();
    }
}
