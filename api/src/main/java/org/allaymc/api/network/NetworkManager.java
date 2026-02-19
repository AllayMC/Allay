package org.allaymc.api.network;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;

/**
 * NetworkManager manages multiple {@link NetworkInterface} instances, allowing plugins
 * to register custom transport protocols (e.g. QUIC, TCP, WebSocket) alongside the
 * built-in RakNet interface.
 *
 * @author daoge_cmd
 */
public interface NetworkManager {

    /**
     * Register a new network interface.
     * <p>
     * Fires a cancellable {@code NetworkInterfaceRegisterEvent}. If the server
     * is already running, the interface will be started immediately.
     *
     * @param iface the network interface to register
     *
     * @return {@code true} if the interface was registered successfully
     */
    boolean registerInterface(NetworkInterface iface);

    /**
     * Unregister a network interface.
     * <p>
     * Fires a cancellable {@code NetworkInterfaceUnregisterEvent} and calls shutdown on the interface.
     *
     * @param iface the network interface to unregister
     *
     * @return {@code true} if the interface was unregistered successfully
     */
    boolean unregisterInterface(NetworkInterface iface);

    /**
     * Get all registered network interfaces.
     *
     * @return an unmodifiable collection of all registered interfaces
     */
    @UnmodifiableView
    Collection<NetworkInterface> getInterfaces();

    /**
     * Get the built-in default (RakNet) network interface.
     *
     * @return the default network interface
     */
    NetworkInterface getDefaultInterface();
}
