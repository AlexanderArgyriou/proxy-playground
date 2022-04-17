package com.argyriou.server;

import com.argyriou.app.App;
import com.argyriou.proxies.Injector;

public class AppServer {
    public static void main(String[] args) {
        App app = new App();
        Injector injector = new Injector();
        injector.doInjections(app);
        app.run(args);
    }
}
