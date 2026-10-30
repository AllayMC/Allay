package org.allaymc.server.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.network.NetworkInterface;

/**
 * {@code NetworkInterfaceUnregisterEvent} represents a proposed removal of an
 * existing transport that listeners can cancel. The manager dispatches it
 * before removing and shutting down the transport. Cancellation prevents both
 * operations for this attempt; accepted removal can precede a shutdown failure.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class NetworkInterfaceUnregisterEvent extends NetworkEvent implements CancellableEvent {
    /**
     * {@code networkInterface} retains the registered transport proposed for
     * removal. The manager removes and shuts down this same object when the
     * event permits the operation.
     */
    private final NetworkInterface networkInterface;
}
