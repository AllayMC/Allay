package org.allaymc.exampleplugin;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.server.Server;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Slf4j
public class ExamplePlugin extends Plugin {

    protected ServerEventListener serverEventListener = new ServerEventListener();

    @Override
    public void onLoad() {
        log.info("ExamplePlugin loaded!");
    }

    @Override
    public void onEnable() {
        log.info("ExamplePlugin enabled!");
        Server.getInstance().getEventBus().registerListener(serverEventListener);
    }

    @Override
    public void onDisable() {
        log.info("ExamplePlugin disabled!");
        Server.getInstance().getEventBus().unregisterListener(serverEventListener);
    }
}
