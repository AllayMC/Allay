package org.allaymc.api.eventbus.event.plugin;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.plugin.Plugin;

/**
 * {@code PluginEnableEvent} represents the notification preceding a loaded
 * plugin's enable callback during startup.
 * The plugin manager dispatches this event before invoking the plugin's
 * {@code onEnable} method, so observing it does not establish successful
 * enablement. The event cannot cancel the enable attempt.
 *
 * @author IWareQ
 */
@CallerThread(ThreadType.SERVER)
public class PluginEnableEvent extends PluginEvent {
    /**
     * Creates an enable notification retaining the supplied plugin instance.
     */
    public PluginEnableEvent(Plugin plugin) {
        super(plugin);
    }
}
