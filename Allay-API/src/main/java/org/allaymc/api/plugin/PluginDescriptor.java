package org.allaymc.api.plugin;

import java.util.List;
import java.util.Optional;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public interface PluginDescriptor {
    String getName();

    Optional<String> getDescription();

    String getVersion();

    List<String> getAuthors();

    List<PluginDependency> getDependencies();

    /**
     * Plugin's website specified in the plugin.yml.
     *
     * @return website url
     */
    Optional<String> getWebsite();
}
