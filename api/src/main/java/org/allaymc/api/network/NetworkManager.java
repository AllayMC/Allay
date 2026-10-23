package org.allaymc.api.network;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;

/**
 * {@code NetworkManager} manages the network interfaces serving client
 * connections, including the built-in RakNet transport. Registration and
 * removal expose cancellable events, and an interface registered after
 * networking starts is started immediately. The server implementation requires
 * custom transports to extend its network-interface base class so lifecycle
 * operations are available.
 *
 * @author daoge_cmd
 */
public interface NetworkManager {

    /**
     * Registers a transport after its registration event permits the change.
     * Already registered interfaces and cancellation return {@code false}, and
     * the server implementation rejects transports that do not extend its
     * transport base class.
     *
     * <p> When networking has started, a caught {@code Exception} from startup
     * removes the new registration and returns {@code false}. An {@code Error}
     * from startup or a failure during the following player-count
     * synchronization propagates after insertion, so a thrown call can leave
     * the interface registered.
     */
    boolean registerInterface(NetworkInterface iface);

    /**
     * Removes an existing transport after its removal event permits the change,
     * then shuts it down. Missing registrations and cancellation return
     * {@code false}; shutdown exceptions propagate after removal, and the
     * server implementation rejects unsupported interface implementations.
     */
    boolean unregisterInterface(NetworkInterface iface);

    /**
     * Returns an unmodifiable view of the registered network interfaces.
     * Membership reflects subsequent registrations and removals rather than a
     * fixed snapshot; callers cannot mutate membership through the returned
     * collection.
     */
    @UnmodifiableView
    Collection<NetworkInterface> getInterfaces();

    /**
     * Returns the built-in RakNet interface supplied when networking was
     * created. This identifies the default transport independently of current
     * membership, so removing it does not select another interface as the
     * default.
     */
    NetworkInterface getDefaultInterface();
}
