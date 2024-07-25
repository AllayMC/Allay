package org.allaymc.exampleplugin;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.allaymc.api.plugin.LogHandler;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.plugin.PluginLoadOrder;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.generator.WorldGenerator;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Slf4j
@Getter
public final class ExamplePlugin extends Plugin {

    public static ExamplePlugin INSTANCE;

    private final ServerEventListener serverEventListener = new ServerEventListener();
    private final WorldEventListener worldEventListener = new WorldEventListener();

    public ExamplePlugin() {
        INSTANCE = this;
    }

    @LogHandler(order = PluginLoadOrder.POST_WORLD)
    public void onEnablePostWorld() {
        log.info("ExamplePlugin enable post world!");
        Registries.WORLD_GENERATOR_FACTORIES.register("RANDOM_BLOCK", preset -> WorldGenerator.builder().name("RANDOM_BLOCK").noisers(new RandomBlockNoiser()).build());
    }

    @LogHandler
    public void onEnableStartUp() {
        log.info("ExamplePlugin enable start up!");
        log.info(getPluginI18n().tr("ep:example_plugin_i18n_test"));
        var server = Server.getInstance();
        server.getEventBus().registerListener(serverEventListener);
        server.getEventBus().registerListener(worldEventListener);
        Registries.COMMANDS.register(new ExampleCommand());
    }

    @Override
    public void onDisable() {
        log.info("ExamplePlugin disabled!");
        var server = Server.getInstance();
        server.getEventBus().unregisterListener(serverEventListener);
        server.getEventBus().unregisterListener(worldEventListener);
        Registries.COMMANDS.unregister("example-cmd");
    }
}
