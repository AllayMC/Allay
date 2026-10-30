package org.allaymc.api.eventbus.event.plugin;

import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.plugin.Plugin;

/**
 * {@code PluginDisableEvent} represents the notification preceding an enabled
 * plugin's disable callback during shutdown.
 * The plugin manager dispatches this event before invoking the plugin's
 * {@code onDisable} method, so observing it does not establish that cleanup has
 * completed. The event cannot cancel plugin shutdown.
 *
 * @author IWareQ
 */
@CallerThread(ThreadType.SERVER)
public class PluginDisableEvent extends PluginEvent {
    /**
     * Creates a disable notification retaining the supplied plugin instance.
     */
    public PluginDisableEvent(Plugin plugin) {
        super(plugin);
    }
}
