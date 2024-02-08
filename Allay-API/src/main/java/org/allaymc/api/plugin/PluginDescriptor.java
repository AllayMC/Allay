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
    String getName();

    // Plugins can leave this information unavailable
    String getDescription();

    String getVersion();

    List<String> getAuthors();

    // Plugins can leave this information unavailable
    List<PluginDependency> getDependencies();

    // Plugins can leave this information unavailable
    String getWebsite();
}
