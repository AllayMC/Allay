package org.allaymc.api.plugin;

/**
 * PluginDependency represents a dependency of a plugin.
 *
 * @param name     the name of this dependency
 * @param version  the version of this dependency, can be {@code null}
 * @param optional whether this dependency is optional
 * @author daoge_cmd
 */
public record PluginDependency(String name, String version, boolean optional) {
}
