package org.allaymc.api.plugin;

import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;

/**
 * Represents a plugin loader that loads a plugin.
 *
 * @author daoge_cmd
 */
public interface PluginLoader {

    /**
     * Gets the path of the plugin.
     *
     * @return the path of the plugin
     */
    Path getPluginPath();

    /**
     * Loads the plugin descriptor.
     *
     * @return the plugin descriptor
     */
    @ApiStatus.OverrideOnly
    PluginDescriptor loadDescriptor();

    /**
     * Loads the plugin.
     *
     * @return the plugin container
     */
    @ApiStatus.OverrideOnly
    PluginContainer loadPlugin();

    /**
     * PluginLoaderFactory is responsible for finding potential plugin file
     * and creating suitable plugin loader for it.
     * <p>
     * Each plugin loader factory will be responsible for loading a specific type of plugin.
     */
    @ApiStatus.OverrideOnly
    interface Factory {

        /**
         * Check if the plugin loader factory can load the plugin file.
         *
         * @param pluginPath the path of the plugin file
         *
         * @return {@code true} if the plugin loader factory can load the plugin file, {@code false} otherwise.
         */
        boolean canLoad(Path pluginPath);

        /**
         * Create a plugin loader for the plugin file.
         *
         * @param pluginPath the path of the plugin file
         *
         * @return the plugin loader
         */
        PluginLoader create(Path pluginPath);
    }
}
