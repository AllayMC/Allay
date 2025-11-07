package org.allaymc.api.eventbus.event.plugin;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.plugin.Plugin;

/**
 * @author IWareQ
 */
@CallerThread(ThreadType.SERVER)
public class PluginDisableEvent extends PluginEvent {
    public PluginDisableEvent(Plugin plugin) {
        super(plugin);
    }
}
