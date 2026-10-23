package org.allaymc.api.eventbus.event.command;

import org.allaymc.api.eventbus.event.Event;

/**
 * {@code CommandEvent} is the shared event base for command-related
 * notifications. Concrete subclasses carry the operation's sender and input;
 * listeners choose the concrete event when they need its payload or
 * cancellation behavior rather than relying on this empty common type.
 *
 * @author daoge_cmd
 */
public abstract class CommandEvent extends Event {
}
