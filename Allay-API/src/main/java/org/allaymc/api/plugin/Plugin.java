package org.allaymc.api.plugin;

import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Setter
@Getter
public abstract class Plugin {

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
     * Reload the plugin and load it again.
     * This is different from simply calling onEnable()/onDisable().
     * The default implementation of this method calls the onLoad() method to simulate the entire process of the plugin being read and enabled.
     * <p>
     * We do not allow this method to be overridden because for a plugin with good code quality, there should be no need to override this method.
     */
    public final void reload() {
        if (!isReloadable()) throw new UnsupportedOperationException("This plugin is not a reloadable plugin!");
        onDisable();
        onLoad();
        onEnable();
    }
}
