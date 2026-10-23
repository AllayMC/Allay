package org.allaymc.server.eventbus.event.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.network.NetworkInterface;

/**
 * {@code NetworkInterfaceRegisterEvent} represents a proposed transport
 * registration that listeners can cancel before the network manager adds it.
 * Cancellation prevents this registration attempt and its startup. Acceptance
 * still permits a later startup failure to remove the new registration.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class NetworkInterfaceRegisterEvent extends NetworkEvent implements CancellableEvent {
    /**
     * {@code networkInterface} retains the proposed transport. Listeners inspect
     * the same object that the manager adds when registration proceeds.
     */
    private final NetworkInterface networkInterface;
}
