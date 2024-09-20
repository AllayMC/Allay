package org.allaymc.api.plugin;

import org.allaymc.api.i18n.I18n;

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
        Path dataFolder,
        I18n i18n
) {

    public static PluginContainer createPluginContainer(
            Plugin plugin,
            PluginDescriptor descriptor,
            PluginLoader loader,
            Path dataFolder,
            I18n i18n
    ) {
        var pluginContainer = new PluginContainer(plugin, descriptor, loader, dataFolder, i18n);
        plugin.setPluginContainer(pluginContainer);
        return pluginContainer;
    }
}
