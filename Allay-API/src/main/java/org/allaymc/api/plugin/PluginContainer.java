package org.allaymc.api.plugin;

import java.nio.file.Path;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public record PluginContainer(
        Plugin plugin,
        PluginDescriptor descriptor,
        PluginLoader loader,
        Path dataFolder
) {

    public static PluginContainer createPluginContainer(Plugin plugin, PluginDescriptor descriptor, PluginLoader loader, Path dataFolder) {
        var pluginContainer = new PluginContainer(plugin, descriptor, loader, dataFolder);
        plugin.setPluginContainer(pluginContainer);
        return pluginContainer;
    }

}
