package org.allaymc.api.plugin;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public interface PluginManager {

    void registerLoaderFactory(PluginLoader.PluginLoaderFactory loaderFactory);

    void registerSource(PluginSource pluginSource);

    Map<String, PluginContainer> getPlugins();

    PluginContainer getPlugin(String name);

    Map<String, PluginContainer> getEnabledPlugins();

    boolean isPluginEnabled(String name);
}
