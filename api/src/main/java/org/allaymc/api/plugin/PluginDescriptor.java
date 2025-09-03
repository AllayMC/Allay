package org.allaymc.api.plugin;

import org.cloudburstmc.protocol.common.util.Preconditions;
import org.semver4j.Semver;

import java.util.List;

/**
 * PluginDescriptor contains the metadata of a plugin.
 *
 * @author daoge_cmd
 */
public interface PluginDescriptor {

    /**
     * Checks if a descriptor is valid.
     *
     * @param descriptor the descriptor to check
     */
    @SuppressWarnings("DataFlowIssue")
    static void checkDescriptorValid(PluginDescriptor descriptor) {
        Preconditions.checkNotNull(descriptor.getName(), "Plugin name cannot be null");
        Preconditions.checkNotNull(descriptor.getEntrance(), "Plugin entrance cannot be null");
        Preconditions.checkNotNull(descriptor.getVersion(), "Plugin version cannot be null");
        Preconditions.checkNotNull(descriptor.getAuthors(), "Plugin authors cannot be null");
        Preconditions.checkNotNull(descriptor.getDescription(), "Plugin description cannot be null");
        Preconditions.checkNotNull(descriptor.getAPIVersion(), "Plugin api version cannot be null");
        Preconditions.checkNotNull(descriptor.getDependencies(), "Plugin dependencies cannot be null");
        for (var dependency : descriptor.getDependencies()) {
            Preconditions.checkNotNull(dependency.name(), "Dependency name cannot be null");
            Preconditions.checkArgument(!dependency.name().isBlank(), "Dependency name cannot be blank");
        }
        Preconditions.checkNotNull(descriptor.getWebsite(), "Plugin website cannot be null");
        Preconditions.checkNotNull(Semver.coerce(descriptor.getVersion()), "Plugin version cannot be coerced (Use https://semver.org/)");
    }

    /**
     * Gets the name of the plugin.
     *
     * @return the name of the plugin
     */
    String getName();

    /**
     * Gets the entrance of the plugin.
     *
     * @return the entrance of the plugin
     */
    String getEntrance();

    /**
     * Gets the version of the plugin.
     *
     * @return the version of the plugin
     */
    String getVersion();

    /**
     * Gets the authors of the plugin.
     *
     * @return the authors of the plugin
     */
    List<String> getAuthors();

    /**
     * Gets the description of the plugin.
     *
     * @return the description of the plugin, or a blank string if is not specified
     */
    String getDescription();

    /**
     * Gets the api version of the plugin.
     *
     * @return the api version of the plugin, or a blank string if is not specified
     */
    String getAPIVersion();

    /**
     * Gets the dependencies of the plugin.
     *
     * @return the dependencies of the plugin, or an empty list if is not specified
     */
    List<PluginDependency> getDependencies();

    /**
     * Gets the website of the plugin.
     *
     * @return the website of the plugin, or a blank string if is not specified
     */
    String getWebsite();
}
