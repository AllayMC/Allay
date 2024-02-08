package org.allaymc.api.plugin;

import org.jetbrains.annotations.ApiStatus;

import java.util.Map;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public interface PluginManager {

    @ApiStatus.Internal
    void loadPlugins();

    void enablePlugins();

    void disablePlugins();

    void registerLoaderFactory(PluginLoader.PluginLoaderFactory loaderFactory);

    void registerSource(PluginSource pluginSource);

    Map<String, PluginContainer> getPluginContainers();

    PluginContainer getPluginContainer(String name);
}
