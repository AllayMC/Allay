package cn.allay;

import cn.allay.server.AllayServer;
import cn.allay.server.Server;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Allay {

    private static Server INSTANCE;

    public static void main(String[] args) {
        log.info("Starting Allay...");

        INSTANCE = new AllayServer();
        INSTANCE.initServer();
        INSTANCE.startMainLoop();
    }
}