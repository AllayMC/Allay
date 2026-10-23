package org.allaymc.api.plugin;

import java.util.Map;

/**
 * {@code PluginManager} exposes loaded plugins and accepts extensions to plugin
 * discovery.
 * Loaded and enabled lookups serve different lifecycle stages; successfully
 * loading a plugin does not imply successful enablement. Custom discovery
 * providers can be registered during loading for the subsequent custom pass.
 *
 * @author daoge_cmd
 */
public interface PluginManager {
    /**
     * Returns the loaded-plugin index keyed by exact descriptor names.
     * The server implementation exposes an unmodifiable live view, so retained
     * views reflect later publications and containers still reference live plugins.
     */
    Map<String, PluginContainer> getPlugins();

    /**
     * Returns the loaded container registered under the exact supplied name.
     * A {@code null} result means no successful load is currently indexed under that
     * name, rather than merely that enablement failed.
     */
    PluginContainer getPlugin(String name);

    /**
     * Returns the container recorded as successfully enabled under this name.
     * Missing entries return {@code null}; the current server retains enabled records
     * after disable callbacks, so this lookup is not a shutdown-state check.
     */
    PluginContainer getEnabledPlugin(String name);

    /**
     * Returns the index of successfully enabled plugins by descriptor name.
     * The server exposes an unmodifiable live view and retains these entries
     * after disable callbacks rather than using this map to track shutdown.
     */
    Map<String, PluginContainer> getEnabledPlugins();

    /**
     * Returns whether the enabled index contains the supplied plugin name.
     * The current server records successful enable callbacks here but does not
     * remove entries during disable, which also affects plugin task validity.
     */
    boolean isPluginEnabled(String name);

    /**
     * Registers a source for the manager's custom discovery pass.
     * Plugins normally call this from {@link Plugin#onLoad()}; registration after
     * the manager advances beyond its loaded state throws {@code IllegalStateException},
     * and registration alone does not initiate another pass.
     */
    void registerCustomSource(PluginSource customPluginSource);

    /**
     * Registers a loader factory for the manager's custom discovery pass.
     * Plugins normally call this from {@link Plugin#onLoad()}; registration after
     * the loaded state throws {@code IllegalStateException}. The custom pass matches only
     * custom factories, including against paths found by default sources.
     */
    void registerCustomLoaderFactory(PluginLoader.Factory customLoaderFactory);
}
