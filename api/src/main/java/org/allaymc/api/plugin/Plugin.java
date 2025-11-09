package org.allaymc.api.plugin;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.server.Server;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author daoge_cmd
 */
@Slf4j
public abstract class Plugin implements TaskCreator {

    protected PluginContainer pluginContainer;
    protected Logger pluginLogger;

    /**
     * Called when the plugin is loaded.
     */
    @ApiStatus.OverrideOnly
    public void onLoad() {
    }

    /**
     * Called when the plugin is enabled.
     */
    @ApiStatus.OverrideOnly
    public void onEnable() {
    }

    /**
     * Called when the plugin is disabled.
     */
    @ApiStatus.OverrideOnly
    public void onDisable() {
    }

    /**
     * @return {@code true} if the plugin is reloadable, otherwise {@code false}
     */
    public boolean isReloadable() {
        return false;
    }

    /**
     * Reload the plugin.
     */
    public void reload() {
        if (!isReloadable()) {
            throw new UnsupportedOperationException("This plugin is not a reloadable plugin!");
        } else {
            log.warn("Plugin {} is marked as reloadable but do nothing in reload() method!", this.pluginContainer.descriptor().getName());
        }
    }

    /**
     * Get the plugin's logger.
     *
     * @return the plugin's logger
     */
    public Logger getPluginLogger() {
        return this.pluginLogger;
    }

    /**
     * Get the plugin container.
     *
     * @return the plugin container
     */
    public PluginContainer getPluginContainer() {
        return this.pluginContainer;
    }

    /**
     * Set the plugin container.
     *
     * @param pluginContainer the plugin container
     */
    @ApiStatus.OverrideOnly
    public void setPluginContainer(PluginContainer pluginContainer) {
        this.pluginContainer = pluginContainer;
        this.pluginLogger = LoggerFactory.getLogger(pluginContainer.descriptor().getName());
    }

    /**
     * Check if the plugin is valid.
     *
     * @return {@code true} if the plugin is valid, otherwise {@code false}
     */
    @Override
    public boolean isValid() {
        return Server.getInstance().getPluginManager().isPluginEnabled(this.pluginContainer.descriptor().getName());
    }
}
