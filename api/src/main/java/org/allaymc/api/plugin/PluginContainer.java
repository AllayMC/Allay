package org.allaymc.api.plugin;

import java.nio.file.Path;

/**
 * {@code PluginContainer} associates a plugin instance with its descriptor,
 * loader, and data directory.
 * Components are retained as supplied rather than copied. The record constructor
 * only stores them; {@link #createPluginContainer} additionally attaches the
 * resulting container to the plugin for lifecycle use.
 *
 * @param plugin live plugin instance
 * @param descriptor plugin metadata source
 * @param loader originating plugin loader
 * @param dataFolder plugin data directory
 * @author daoge_cmd
 */
public record PluginContainer(
        Plugin plugin,
        PluginDescriptor descriptor,
        PluginLoader loader,
        Path dataFolder
) {

    /**
     * Creates a container and attaches it to the supplied plugin before returning.
     * This invokes the plugin's container-assignment hook, which normally selects
     * its logger; the path is retained without creating its directory.
     */
    public static PluginContainer createPluginContainer(
            Plugin plugin,
            PluginDescriptor descriptor,
            PluginLoader loader,
            Path dataFolder
    ) {
        var pluginContainer = new PluginContainer(plugin, descriptor, loader, dataFolder);
        plugin.setPluginContainer(pluginContainer);
        return pluginContainer;
    }
}
