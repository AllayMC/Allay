package org.allaymc.server.network;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.network.NetworkInterface;
import org.allaymc.api.network.NetworkManager;
import org.allaymc.api.server.Server;
import org.allaymc.server.eventbus.event.network.NetworkInterfaceRegisterEvent;
import org.allaymc.server.eventbus.event.network.NetworkInterfaceUnregisterEvent;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code AllayNetworkManager} manages registered server transports and forwards
 * startup and shutdown operations to them. It publishes the default interface
 * during construction and dispatches cancellable events for later membership
 * changes. A registration made after {@code started} becomes {@code true}
 * starts its transport before synchronizing the current advertised player
 * counts.
 *
 * <p>
 * Membership and transport lifetime are separate: a startup exception removes a
 * new registration without shutting down partially started resources, while
 * unregistration removes membership before shutdown. {@code interfaces} permits
 * concurrent membership access and {@code started} is volatile, but checks,
 * events, membership changes, and lifecycle calls do not form one atomic
 * operation. Callers needing serialized lifecycle transitions must coordinate
 * those operations externally.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayNetworkManager implements NetworkManager {

    /**
     * {@code interfaces} holds current membership and backs the live view from
     * {@code getInterfaces}; removing membership does not itself close a transport.
     */
    protected final Set<AllayNetworkInterface> interfaces = ConcurrentHashMap.newKeySet();
    /**
     * {@code defaultInterface} retains the original default transport even if
     * later unregistration removes that transport from {@code interfaces}.
     */
    protected final AllayNetworkInterface defaultInterface;

    /**
     * {@code started} selects immediate startup for new registrations. Bulk
     * lifecycle calls update it only after every visited interface returns normally.
     */
    protected volatile boolean started;

    /**
     * Creates a manager containing the supplied default interface without starting it.
     * The default reference remains fixed for this manager's lifetime, while
     * later registration and unregistration can change its collection membership.
     */
    public AllayNetworkManager(AllayNetworkInterface defaultInterface) {
        this.defaultInterface = defaultInterface;
        this.interfaces.add(defaultInterface);
    }

    /**
     * Registers an accepted transport and starts it when networking has started.
     * Unsupported implementations throw {@code IllegalArgumentException}; existing
     * membership and event cancellation return {@code false}.
     *
     * <p>
     * Membership is added before startup. A caught startup exception removes that
     * membership and returns {@code false}, but it does not call shutdown for
     * partially created resources. Successful startup precedes player-count
     * synchronization; failures during synchronization propagate with membership
     * retained. These steps are not serialized against other lifecycle calls.
     */
    @Override
    public boolean registerInterface(NetworkInterface iface) {
        if (!(iface instanceof AllayNetworkInterface ani)) {
            throw new IllegalArgumentException("Custom network interface must extend AllayNetworkInterface");
        }

        if (interfaces.contains(iface)) {
            return false;
        }

        var event = new NetworkInterfaceRegisterEvent(iface);
        if (!event.call()) {
            return false;
        }

        interfaces.add(ani);

        if (started) {
            try {
                ani.start();
            } catch (Exception e) {
                // Withdraw membership after startup fails, but leave transport
                // cleanup to its owner because this path never invokes shutdown.
                log.error("Failed to start network interface: {}", iface, e);
                interfaces.remove(iface);
                return false;
            }

            // Transports can create advertisement state during startup. Update
            // counts afterward so transports requiring that state can accept
            // the player manager's current values.
            var playerManager = Server.getInstance().getPlayerManager();
            ani.setPlayerCount(playerManager.getPlayerCount());
            ani.setMaxPlayerCount(playerManager.getMaxPlayerCount());
        }

        return true;
    }

    /**
     * Removes an accepted registered transport before invoking its shutdown hook.
     * Missing membership or event cancellation returns {@code false}; unsupported
     * implementations throw {@code IllegalArgumentException}, and shutdown failures
     * propagate after membership has already been removed.
     */
    @Override
    public boolean unregisterInterface(NetworkInterface iface) {
        if (!(iface instanceof AllayNetworkInterface ani)) {
            throw new IllegalArgumentException("Custom network interface must extend AllayNetworkInterface");
        }

        if (!interfaces.contains(iface)) {
            return false;
        }

        var event = new NetworkInterfaceUnregisterEvent(iface);
        if (!event.call()) {
            return false;
        }

        // Remove membership before closing resources so subsequent collection
        // readers no longer discover this transport while shutdown is in progress.
        interfaces.remove(iface);
        ani.shutdown();

        return true;
    }

    /**
     * Returns an unmodifiable live view of registered transport membership.
     * Later registrations and removals appear in the same view; callers cannot
     * change membership through the returned collection or treat it as a snapshot.
     */
    @Override
    @UnmodifiableView
    public Collection<NetworkInterface> getInterfaces() {
        return Collections.unmodifiableCollection(interfaces);
    }

    /**
     * Returns the default transport retained when this manager was created.
     * The result does not depend on current membership, so removing that
     * transport does not cause another registration to become the default.
     */
    @Override
    public NetworkInterface getDefaultInterface() {
        return defaultInterface;
    }

    /**
     * Starts registered transports and marks networking started after normal completion.
     * An exception stops the traversal without undoing earlier startup or changing
     * {@code started}; callers must handle resources left by a partial traversal.
     */
    public void startAll() {
        // Publish started only after the traversal succeeds; an exception can
        // leave earlier transports running while the previous flag value remains.
        interfaces.forEach(AllayNetworkInterface::start);
        started = true;
    }

    /**
     * Shuts down registered transports and clears the startup flag after completion.
     * An exception stops the traversal and leaves {@code started} unchanged;
     * this method neither clears membership nor retries transports skipped afterward.
     */
    public void shutdownAll() {
        // Preserve the previous flag when a shutdown throws; membership remains
        // available for owners to inspect or retry after partial cleanup.
        interfaces.forEach(AllayNetworkInterface::shutdown);
        started = false;
    }
}
