package org.allaymc.api.plugin;

import java.util.Set;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public interface PluginManager {
    void registerLoader(PluginLoader loader);

    Set<Plugin> getPlugins();

    Plugin getPlugin(String name);
}
