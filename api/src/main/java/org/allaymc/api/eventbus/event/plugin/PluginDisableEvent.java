package org.allaymc.api.eventbus.event.plugin;

import org.allaymc.api.plugin.Plugin;

/**
 * @author IWareQ
 */
public class PluginDisableEvent extends PluginEvent {
    public PluginDisableEvent(Plugin plugin) {
        super(plugin);
    }
}
