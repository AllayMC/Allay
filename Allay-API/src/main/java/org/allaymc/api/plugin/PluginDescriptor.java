package org.allaymc.api.plugin;

import java.util.List;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 *
 * If the plugin does not provide certain information,
 * it will populate an empty object/value instead of leaving it null
 */
public interface PluginDescriptor {
    String name();

    // Plugins can leave this information unavailable
    String description();

    String version();

    List<String> authors();

    // Plugins can leave this information unavailable
    List<PluginDependency> dependencies();

    // Plugins can leave this information unavailable
    String website();
}
