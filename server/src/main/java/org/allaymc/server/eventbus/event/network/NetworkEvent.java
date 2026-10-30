package org.allaymc.server.eventbus.event.network;

import org.allaymc.api.eventbus.event.Event;

/**
 * {@code NetworkEvent} represents the common event category for server network
 * lifecycle and packet hooks. It inherits dispatch behavior from {@link Event};
 * concrete network events supply listener data, and their dispatchers determine
 * whether cancellation prevents a network operation.
 *
 * @author daoge_cmd
 */
public abstract class NetworkEvent extends Event {
}
