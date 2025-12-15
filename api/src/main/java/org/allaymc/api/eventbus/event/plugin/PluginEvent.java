package org.allaymc.api.eventbus.event.plugin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.plugin.Plugin;

/**
 * The base class for all plugin events.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public abstract class PluginEvent extends Event {
    protected Plugin plugin;
}
