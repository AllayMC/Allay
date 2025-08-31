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

    static void checkDescriptorValid(PluginDescriptor descriptor) {
        Preconditions.checkNotNull(descriptor.getName(), "Plugin name cannot be null");
        Preconditions.checkNotNull(descriptor.getEntrance(), "Plugin entrance cannot be null");
        Preconditions.checkNotNull(descriptor.getVersion(), "Plugin version cannot be null");
        Preconditions.checkNotNull(descriptor.getAuthors(), "Plugin authors cannot be null");
        // noinspection DataFlowIssue
        Preconditions.checkNotNull(Semver.coerce(descriptor.getVersion()), "Plugin version cannot be coerced (Use https://semver.org/)");
    }

    /**
     * Get the name of the plugin.
     *
     * @return the name of the plugin
     */
    String getName();

    /**
     * Get the entrance of the plugin.
     *
     * @return the entrance of the plugin
     */
    String getEntrance();

    /**
     * Get the description of the plugin.
     *
     * @return the description of the plugin
     */
    String getDescription();

    /**
     * Get the version of the plugin.
     *
     * @return the version of the plugin
     */
    String getVersion();

    /**
     * Get the authors of the plugin.
     *
     * @return the authors of the plugin
     */
    List<String> getAuthors();

    /**
     * Get the dependencies of the plugin.
     *
     * @return the dependencies of the plugin
     */
    List<PluginDependency> getDependencies();

    /**
     * Get the website of the plugin.
     *
     * @return the website of the plugin
     */
    String getWebsite();
}
