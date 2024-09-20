package org.allaymc.api.plugin;

import java.util.Map;

/**
 * PluginManager is responsible for managing plugins.
 *
 * @author daoge_cmd
 */
public interface PluginManager {

    /**
     * Register a plugin loader factory.
     *
     * @param loaderFactory the plugin loader factory.
     */
    void registerLoaderFactory(PluginLoader.PluginLoaderFactory loaderFactory);

    /**
     * Register a plugin source.
     *
     * @param pluginSource the plugin source.
     */
    void registerSource(PluginSource pluginSource);

    /**
     * Get all discovered plugins.
     *
     * @return the discovered plugins
     */
    Map<String, PluginContainer> getPlugins();

    /**
     * Get a discovered plugin by name.
     *
     * @param name the name of the plugin.
     * @return the plugin container.
     */
    PluginContainer getPlugin(String name);

    /**
     * Get all enabled plugins.
     *
     * @return the enabled plugins.
     */
    Map<String, PluginContainer> getEnabledPlugins();

    /**
     * Check if a plugin is enabled.
     *
     * @param name the name of the plugin.
     * @return {@code true} if the plugin is enabled, {@code false} otherwise.
     */
    boolean isPluginEnabled(String name);
}
