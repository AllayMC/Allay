package org.allaymc.api.eventbus.event.plugin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.plugin.Plugin;

/**
 * {@code PluginEvent} carries the plugin instance associated with a plugin
 * lifecycle notification.
 * Concrete event classes identify the transition and its timing; listeners
 * register those concrete classes because event dispatch matches exact event
 * classes rather than this shared base type.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public abstract class PluginEvent extends Event {
    /**
     * {@code plugin} holds the live plugin involved in the transition. The
     * generated getter returns that instance rather than a lifecycle-state snapshot.
     */
    protected Plugin plugin;
}
