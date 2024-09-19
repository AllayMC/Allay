package org.allaymc.api.plugin;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.server.Server;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
@Slf4j
public abstract class Plugin implements TaskCreator {

    protected PluginContainer pluginContainer;

    /**
     * Called when the plugin is loaded.
     */
    @ApiStatus.OverrideOnly
    public void onLoad() {}

    /**
     * Called when the plugin is enabled.
     */
    @ApiStatus.OverrideOnly
    public void onEnable() {}

    /**
     * Called when the plugin is disabled.
     */
    @ApiStatus.OverrideOnly
    public void onDisable() {}

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
        if (!isReloadable()) throw new UnsupportedOperationException("This plugin is not a reloadable plugin!");
        else log.warn("Plugin {} is marked as reloadable but do nothing in reload() method!", pluginContainer.descriptor().getName());
    }

    /**
     * Get the plugin's I18n instance.
     *
     * @return the plugin's I18n instance
     */
    public I18n getPluginI18n() {
        return pluginContainer.i18n();
    }

    /**
     * Check if the plugin is valid.
     *
     * @return {@code true} if the plugin is valid, otherwise {@code false}
     */
    @Override
    public boolean isValid() {
        return Server.getInstance().getPluginManager().isPluginEnabled(pluginContainer.descriptor().getName());
    }
}
