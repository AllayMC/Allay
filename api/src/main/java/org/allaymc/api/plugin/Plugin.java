package org.allaymc.api.plugin;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.server.Server;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@code Plugin} provides lifecycle callbacks and scheduler ownership for a
 * loaded plugin.
 * The loader attaches {@code pluginContainer} and derives {@code pluginLogger}
 * from its descriptor before the manager invokes callbacks. Plugin constructors
 * must not assume either reference is available. The
 * default callbacks do nothing; task validity follows the manager's enabled
 * lookup, and reload requires an explicit implementation by the plugin.
 *
 * @author daoge_cmd
 */
@Slf4j
public abstract class Plugin implements TaskCreator {

    /**
     * {@code pluginContainer} holds the container assigned by the loader before
     * lifecycle callbacks. It remains {@code null} until
     * {@link #setPluginContainer(PluginContainer)} is called.
     */
    protected PluginContainer pluginContainer;
    /**
     * {@code pluginLogger} uses the assigned container's descriptor name. A
     * successful {@code setPluginContainer} call replaces it; a logger-lookup
     * failure can retain the earlier logger after the container changes.
     */
    protected Logger pluginLogger;

    /**
     * Initializes plugin resources after its loader attaches the container.
     * The manager invokes this callback before publishing the loaded plugin,
     * allowing registration of custom sources and loader factories for its
     * later discovery pass. The default implementation does nothing.
     */
    @ApiStatus.OverrideOnly
    public void onLoad() {
    }

    /**
     * Starts plugin functionality after loaded plugins have been discovered.
     * The manager publishes successful enablement only after this callback
     * returns, so {@link #isValid()} is still {@code false} during initial enablement.
     * The default implementation does nothing.
     */
    @ApiStatus.OverrideOnly
    public void onEnable() {
    }

    /**
     * Releases plugin resources during reverse-order server shutdown.
     * The default implementation does nothing, and overrides must perform their
     * own cleanup. The current manager retains enabled lookup entries during
     * and after this callback rather than invalidating them here.
     */
    @ApiStatus.OverrideOnly
    public void onDisable() {
    }

    /**
     * Returns whether this plugin advertises support for explicit reload requests.
     * The default is {@code false}; overriding this result alone does not implement
     * reloading, so reloadable plugins also override {@link #reload()}.
     */
    public boolean isReloadable() {
        return false;
    }

    /**
     * Handles an explicit request to reload this plugin's state.
     * The default implementation throws when reloadability is {@code false} and only
     * logs a warning otherwise; subclasses provide any actual resource or
     * configuration refresh.
     */
    public void reload() {
        if (!isReloadable()) {
            throw new UnsupportedOperationException("This plugin is not a reloadable plugin!");
        } else {
            log.warn("Plugin {} is marked as reloadable but do nothing in reload() method!", this.pluginContainer.descriptor().getName());
        }
    }

    /**
     * Returns the logger retained from the last successful logger lookup.
     * Before a loader attaches the container this value is {@code null}, so plugin
     * constructors cannot assume the logger is available.
     */
    public Logger getPluginLogger() {
        return this.pluginLogger;
    }

    /**
     * Returns the loader-assigned container holding this plugin's metadata and
     * resources. The result is {@code null} before attachment; lifecycle callbacks may
     * use it after the loader completes container creation.
     */
    public PluginContainer getPluginContainer() {
        return this.pluginContainer;
    }

    /**
     * Attaches a container and selects a logger using its descriptor name.
     * Loaders call this before lifecycle callbacks. Callers supply a non-null
     * container and descriptor with a usable name; the container reference changes
     * before logger lookup, so lookup failure can retain the earlier logger.
     */
    @ApiStatus.OverrideOnly
    public void setPluginContainer(PluginContainer pluginContainer) {
        this.pluginContainer = pluginContainer;
        this.pluginLogger = LoggerFactory.getLogger(pluginContainer.descriptor().getName());
    }

    /**
     * Returns whether the server manager's enabled lookup contains this plugin's
     * descriptor name. A container and initialized server are required; the
     * result reflects that lookup, which the current manager retains after
     * disable callbacks.
     */
    @Override
    public boolean isValid() {
        return Server.getInstance().getPluginManager().isPluginEnabled(this.pluginContainer.descriptor().getName());
    }
}
