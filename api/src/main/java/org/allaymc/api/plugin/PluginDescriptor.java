package org.allaymc.api.plugin;

import java.util.List;

/**
 * PluginDescriptor contains the metadata of a plugin.
 *
 * @author daoge_cmd
 */
public interface PluginDescriptor {
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
