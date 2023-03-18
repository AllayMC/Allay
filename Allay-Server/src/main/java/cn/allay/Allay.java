package cn.allay;

import cn.allay.api.AllayAPI;
import cn.allay.api.MissingImplementationException;
import cn.allay.component.injector.AllayComponentInjector;
import cn.allay.component.interfaces.ComponentInjector;
import cn.allay.entity.type.AllayEntityTypeBuilder;
import cn.allay.entity.type.EntityTypeBuilder;
import cn.allay.scheduler.AllayScheduler;
import cn.allay.scheduler.Scheduler;
import cn.allay.server.AllayServer;
import cn.allay.server.Server;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Allay {

    public static void main(String[] args) {
        log.info("Starting Allay...");
        try {
            initAllayAPI();
        } catch (MissingImplementationException e) {
            log.error("Cannot init Allay API!");
            e.printStackTrace();
            System.exit(1);
        }
        Server.getInstance().initServer();
        Server.getInstance().startMainLoop();
    }

    private static void initAllayAPI() throws MissingImplementationException {
        var api = AllayAPI.getInstance();
        api.bind(ComponentInjector.ComponentInjectorFactory.class, AllayComponentInjector::new);
        api.bind(Server.class, new AllayServer());
        api.bind(Scheduler.SchedulerFactory.class, AllayScheduler::new);
        api.bind(EntityTypeBuilder.class, new AllayEntityTypeBuilder());
        api.implement();
    }
}