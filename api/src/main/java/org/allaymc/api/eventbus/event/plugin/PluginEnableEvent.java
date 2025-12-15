package org.allaymc.api.eventbus.event.plugin;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.plugin.Plugin;

/**
 * Called when a plugin is enabled.
 *
 * @author IWareQ
 */
@CallerThread(ThreadType.SERVER)
public class PluginEnableEvent extends PluginEvent {
    public PluginEnableEvent(Plugin plugin) {
        super(plugin);
    }
}
