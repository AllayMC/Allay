package org.allaymc.server.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.network.NetworkInterface;

/**
 * Fired when a {@link NetworkInterface} is about to be registered.
 * <p>
 * This event is cancellable. If cancelled, the interface will not be registered.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class NetworkInterfaceRegisterEvent extends NetworkEvent implements CancellableEvent {
    private final NetworkInterface networkInterface;
}
