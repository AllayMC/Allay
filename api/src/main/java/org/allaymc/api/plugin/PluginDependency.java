package org.allaymc.api.plugin;

/**
 * PluginDependency represents a dependency of a plugin.
 *
 * @author daoge_cmd
 */
public record PluginDependency(String name, String version, boolean optional) {
}
