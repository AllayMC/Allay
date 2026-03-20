package org.allaymc.server.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.network.NetworkInterface;

/**
 * Fired when a {@link NetworkInterface} is about to be unregistered.
 * <p>
 * This event is cancellable. If cancelled, the interface will not be unregistered.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class NetworkInterfaceUnregisterEvent extends NetworkEvent implements CancellableEvent {
    private final NetworkInterface networkInterface;
}
