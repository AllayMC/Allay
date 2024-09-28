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

    String getName();

    String getEntrance();

    // Plugins can leave this information unavailable
    String getDescription();

    String getVersion();

    List<String> getAuthors();

    // Plugins can leave this information unavailable
    List<PluginDependency> getDependencies();

    // Plugins can leave this information unavailable
    String getWebsite();
}
