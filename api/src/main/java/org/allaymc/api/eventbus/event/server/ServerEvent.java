package org.allaymc.api.eventbus.event.server;

import org.allaymc.api.eventbus.event.Event;

/**
 * {@code ServerEvent} groups events concerning server operation and player
 * session lifecycle.
 * Concrete subclasses provide the event payload and determine whether
 * cancellation is supported. Listeners register those concrete classes because
 * event dispatch does not deliver subclass events to a handler for this base.
 *
 * @author daoge_cmd
 */
public abstract class ServerEvent extends Event {
}
