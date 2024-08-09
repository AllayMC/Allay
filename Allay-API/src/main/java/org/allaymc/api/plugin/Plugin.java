package org.allaymc.api.plugin;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.i18n.I18n;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.server.Server;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@Slf4j
public abstract class Plugin implements TaskCreator {

    protected PluginContainer pluginContainer;

    /**
     * When the plugin is loaded, call
     */
    public void onLoad() {}

    /**
     * When the plugin is enabled, call
     */
    public void onEnable() {}

    /**
     * When the plugin is disabled, call
     */
    public void onDisable() {}

    /**
     * @return Whether the plugin can be reloaded
     */
    public boolean isReloadable() {
        return false;
    }

    /**
     * When the plugin reloading, call
     */
    public void reload() {
        if (!isReloadable()) throw new UnsupportedOperationException("This plugin is not a reloadable plugin!");
        else log.warn("Plugin {} is marked as reloadable but do nothing in reload() method!", pluginContainer.descriptor().getName());
    }

    public I18n getPluginI18n() {
        return pluginContainer.i18n();
    }

    @Override
    public boolean isValid() {
        return Server.getInstance().getPluginManager().isPluginEnabled(pluginContainer.descriptor().getName());
    }
}
