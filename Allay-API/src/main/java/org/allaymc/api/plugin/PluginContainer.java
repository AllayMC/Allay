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

}
