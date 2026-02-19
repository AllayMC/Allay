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
 * @author daoge_cmd
 */
@Slf4j
public class AllayNetworkManager implements NetworkManager {

    protected final Set<AllayNetworkInterface> interfaces = ConcurrentHashMap.newKeySet();
    protected final AllayNetworkInterface defaultInterface;

    protected volatile boolean started;

    public AllayNetworkManager(AllayNetworkInterface defaultInterface) {
        this.defaultInterface = defaultInterface;
        this.interfaces.add(defaultInterface);
    }

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
                log.error("Failed to start network interface: {}", iface, e);
                interfaces.remove(iface);
                return false;
            }

            // Sync current player/max counts to the newly registered interface
            var playerManager = Server.getInstance().getPlayerManager();
            ani.setPlayerCount(playerManager.getPlayerCount());
            ani.setMaxPlayerCount(playerManager.getMaxPlayerCount());
        }

        return true;
    }

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

        interfaces.remove(iface);
        ani.shutdown();

        return true;
    }

    @Override
    @UnmodifiableView
    public Collection<NetworkInterface> getInterfaces() {
        return Collections.unmodifiableCollection(interfaces);
    }

    @Override
    public NetworkInterface getDefaultInterface() {
        return defaultInterface;
    }

    public void startAll() {
        interfaces.forEach(AllayNetworkInterface::start);
        started = true;
    }

    public void shutdownAll() {
        interfaces.forEach(AllayNetworkInterface::shutdown);
        started = false;
    }
}
