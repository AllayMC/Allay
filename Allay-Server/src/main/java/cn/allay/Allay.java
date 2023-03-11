package cn.allay;

import cn.allay.api.AllayAPI;
import cn.allay.api.MissingBindingException;
import cn.allay.component.injector.SimpleComponentInjector;
import cn.allay.component.interfaces.ComponentInjector;
import cn.allay.server.AllayServer;
import cn.allay.server.Server;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Allay {

    public static void main(String[] args) {
        log.info("Starting Allay...");
        try {
            initAllayAPI();
        } catch (MissingBindingException e) {
            log.error("Cannot init Allay API!");
            e.printStackTrace();
            System.exit(1);
        }
        Server.getInstance().initServer();
        Server.getInstance().startMainLoop();
    }

    private static void initAllayAPI() throws MissingBindingException {
        var api = AllayAPI.getInstance();
        api.bind(ComponentInjector.ComponentInjectorFactory.class, SimpleComponentInjector::new);
        api.bind(Server.class, new AllayServer());
        api.implement();
    }
}