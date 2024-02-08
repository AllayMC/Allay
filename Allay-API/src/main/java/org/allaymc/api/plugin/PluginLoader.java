package org.allaymc.api.plugin;

import java.nio.file.Path;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public interface PluginLoader {
    boolean canLoad(Path path);

    PluginDescriptor loadDescriptor();

    Plugin loadPlugin();
}
