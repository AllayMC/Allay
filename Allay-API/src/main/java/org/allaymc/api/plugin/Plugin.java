package org.allaymc.api.plugin;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.TaskCreator;
import org.allaymc.api.server.Server;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public abstract class Plugin implements TaskCreator {

    protected PluginContainer pluginContainer;
    // This value shouldn't be edited by the plugin itself!
    // Instead, it should only be edited by the plugin manager
    protected boolean enabled = false;

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
     * When the plugin is unloaded, call
     */
    public void onUnload() {}

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
        onUnload();
        onLoad();
        onEnable();
    }

    public Server getServer() {
        return Server.getInstance();
    }

    public Scheduler getServerScheduler() {
        return this.getServer().getScheduler();
    }

    public PluginManager getPluginManager() {
        return this.getServer().getPluginManager();
    }

    public CommandRegistry getCommandRegistry() {
        return this.getServer().getCommandRegistry();
    }

    @Override
    public boolean isValid() {
        return enabled;
    }
}
