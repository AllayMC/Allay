package org.allaymc.api.eventbus.event.plugin;

import org.allaymc.api.plugin.Plugin;

/**
 * @author IWareQ
 */
public class PluginEnableEvent extends PluginEvent {
    public PluginEnableEvent(Plugin plugin) {
        super(plugin);
    }
}
