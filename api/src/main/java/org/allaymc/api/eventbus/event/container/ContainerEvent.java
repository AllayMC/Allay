package org.allaymc.api.eventbus.event.container;

import org.allaymc.api.eventbus.event.Event;

/**
 * {@code ContainerEvent} is the common event category for container windows, item
 * transfer, and workstation processing. Concrete subclasses carry the relevant
 * containers, viewers, or item values and declare cancellation support when their
 * dispatching operation allows listeners to prevent further effects.
 *
 * @author daoge_cmd
 */
public abstract class ContainerEvent extends Event {
}
