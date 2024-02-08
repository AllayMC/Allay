package org.allaymc.api.plugin;

import java.util.Map;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public interface PluginManager {
    void registerLoaderFactory(PluginLoader.PluginLoaderFactory loaderFactory);

    Map<String, PluginContainer> getPlugins();

    PluginContainer getPlugin(String name);
}
