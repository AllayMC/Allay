package org.allaymc.api.plugin;

import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;

/**
 * {@code PluginLoader} loads one plugin candidate's descriptor and runtime
 * container.
 * The manager obtains a loader from a matching factory, reads its descriptor
 * during discovery, and loads the plugin later in dependency order. Lifecycle
 * callbacks remain the manager's responsibility after loading returns.
 *
 * @author daoge_cmd
 */
public interface PluginLoader {

    /**
     * Returns the source path associated with this plugin loader.
     */
    Path getPluginPath();

    /**
     * Reads and validates metadata for this loader's plugin candidate.
     * The manager invokes this before loading the plugin, logs failures from
     * this phase, and excludes failed candidates from that discovery pass.
     */
    @ApiStatus.OverrideOnly
    PluginDescriptor loadDescriptor();

    /**
     * Constructs a plugin container after its descriptor has been loaded.
     * Loader implementations attach the container to the plugin before returning
     * it; the manager invokes
     * {@link Plugin#onLoad()} separately and publishes only successful loads.
     */
    @ApiStatus.OverrideOnly
    PluginContainer loadPlugin();

    /**
     * {@code Factory} matches candidate paths and creates loaders for one
     * supported plugin format.
     */
    @ApiStatus.OverrideOnly
    interface Factory {

        /**
         * Returns whether this factory accepts the supplied plugin candidate path.
         */
        boolean canLoad(Path pluginPath);

        /**
         * Creates a loader for a path accepted by this factory.
         * The manager calls this after a successful match; construction failures
         * may abort discovery before descriptor failure handling is entered.
         */
        PluginLoader create(Path pluginPath);
    }
}
