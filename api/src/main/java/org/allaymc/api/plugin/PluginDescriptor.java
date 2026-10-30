package org.allaymc.api.plugin;

import java.util.List;

/**
 * {@code PluginDescriptor} supplies metadata used to identify, load, and order a
 * plugin.
 * Loader implementations validate required values before the manager checks API
 * compatibility and dependencies. Optional text uses blank strings and optional
 * dependency lists use an empty list rather than {@code null}.
 *
 * @author daoge_cmd
 */
public interface PluginDescriptor {
    /**
     * Returns the plugin name used as its manager lookup key.
     */
    String getName();

    /**
     * Returns the fully qualified name of the plugin entry class.
     */
    String getEntrance();

    /**
     * Returns the plugin version interpreted by dependency compatibility checks.
     */
    String getVersion();

    /**
     * Returns the author names supplied by the plugin metadata. The server's
     * JAR descriptor exposes its stored list directly, so callers must copy it
     * before making independent edits.
     */
    List<String> getAuthors();

    /**
     * Returns the plugin's descriptive text for metadata consumers.
     * Descriptors without a description return a blank string, allowing callers
     * to handle absence without requiring a separate {@code null} case.
     */
    String getDescription();

    /**
     * Returns the accepted server API version range for this plugin.
     * A blank value skips the manager's API compatibility check; a nonblank
     * value is interpreted as a semantic version range.
     */
    String getAPIVersion();

    /**
     * Returns dependency declarations used for loading order and compatibility.
     * Plugins without dependencies return an empty list. Callers must not assume
     * that every listed dependency is required or has a version constraint.
     * The server's JAR descriptor can expose its stored list directly, so callers
     * must copy it before making independent edits.
     */
    List<PluginDependency> getDependencies();

    /**
     * Returns the plugin's website text for metadata consumers.
     * Descriptors without a website return a blank string; this value is metadata
     * and does not itself open or validate a network resource.
     */
    String getWebsite();
}
