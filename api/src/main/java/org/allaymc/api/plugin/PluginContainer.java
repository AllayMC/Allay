package org.allaymc.api.plugin;

import java.nio.file.Path;

/**
 * PluginContainer contains the related stuffs of a plugin.
 *
 * @author daoge_cmd
 */
public record PluginContainer(
        Plugin plugin,
        PluginDescriptor descriptor,
        PluginLoader loader,
        Path dataFolder
) {

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
