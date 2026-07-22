package org.allaymc.server.network.protocol;

import org.allaymc.server.network.NetworkData;
import org.allaymc.server.network.protocol.v1001.ProtocolV1001;
import org.allaymc.server.network.protocol.v766.ProtocolV766NetEase;
import org.allaymc.server.network.protocol.v818.ProtocolV818;
import org.allaymc.server.network.protocol.v819.ProtocolV819;
import org.allaymc.server.network.protocol.v819.ProtocolV819NetEase;
import org.allaymc.server.network.protocol.v827.ProtocolV827;
import org.allaymc.server.network.protocol.v844.ProtocolV844;
import org.allaymc.server.network.protocol.v859.ProtocolV859;
import org.allaymc.server.network.protocol.v860.ProtocolV860;
import org.allaymc.server.network.protocol.v898.ProtocolV898;
import org.allaymc.server.network.protocol.v924.ProtocolV924;
import org.allaymc.server.network.protocol.v944.ProtocolV944;
import org.allaymc.server.network.protocol.v975.ProtocolV975;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Exact protocol lookup keyed by client variant and protocol version.
 */
public final class ProtocolRegistry {
    private static volatile ProtocolRegistry defaultRegistry;

    private Map<Key, Protocol> mutableProtocols = new HashMap<>();
    private volatile Snapshot frozenSnapshot;

    public synchronized void register(Protocol protocol) {
        Objects.requireNonNull(protocol, "protocol");
        ensureMutable();
        if (!protocol.isInitialized()) {
            throw new IllegalArgumentException("Cannot register an uninitialized protocol: " + protocol);
        }

        var key = new Key(protocol.getVariant(), protocol.getProtocolVersion());
        if (mutableProtocols.putIfAbsent(key, protocol) != null) {
            throw new IllegalArgumentException("Duplicate protocol registration: " + key);
        }
    }

    public synchronized void freeze() {
        ensureMutable();
        if (mutableProtocols.isEmpty()) {
            throw new IllegalStateException("Cannot freeze an empty protocol registry");
        }

        var protocols = Map.copyOf(mutableProtocols);
        var protocolsByVariant = new EnumMap<ClientVariant, List<Protocol>>(ClientVariant.class);
        for (var variant : ClientVariant.values()) {
            var supported = protocols.values().stream()
                    .filter(protocol -> protocol.getVariant() == variant)
                    .sorted(Comparator.comparingInt(Protocol::getProtocolVersion))
                    .toList();
            protocolsByVariant.put(variant, supported);
        }

        var snapshot = new Snapshot(protocols, Map.copyOf(protocolsByVariant));
        mutableProtocols = null;
        frozenSnapshot = snapshot;
    }

    public Protocol resolve(ClientVariant variant, int protocolVersion) {
        var snapshot = getFrozenSnapshot();
        return snapshot.protocols().get(new Key(Objects.requireNonNull(variant, "variant"), protocolVersion));
    }

    public Protocol getLowest(ClientVariant variant) {
        var supported = getSupported(variant);
        return supported.isEmpty() ? null : supported.getFirst();
    }

    public Protocol getLatest(ClientVariant variant) {
        var supported = getSupported(variant);
        return supported.isEmpty() ? null : supported.getLast();
    }

    public List<Protocol> getSupported(ClientVariant variant) {
        var snapshot = getFrozenSnapshot();
        return snapshot.protocolsByVariant().get(Objects.requireNonNull(variant, "variant"));
    }

    public Collection<Protocol> getProtocols() {
        return List.copyOf(getFrozenSnapshot().protocols().values());
    }

    public boolean isFrozen() {
        return frozenSnapshot != null;
    }

    public static ProtocolRegistry createDefault(NetworkData source) {
        var registry = new ProtocolRegistry();
        var supported = new ArrayList<Protocol>();
        supported.add(new ProtocolV818());
        supported.add(new ProtocolV819());
        supported.add(new ProtocolV827());
        supported.add(new ProtocolV844());
        supported.add(new ProtocolV859());
        supported.add(new ProtocolV860());
        supported.add(new ProtocolV898());
        supported.add(new ProtocolV924());
        supported.add(new ProtocolV944());
        supported.add(new ProtocolV975());
        supported.add(new ProtocolV1001());
        supported.add(new ProtocolV766NetEase());
        supported.add(new ProtocolV819NetEase());

        for (var protocol : supported) {
            protocol.initialize(source);
            registry.register(protocol);
        }
        registry.freeze();
        return registry;
    }

    public static synchronized void installDefault(ProtocolRegistry registry) {
        Objects.requireNonNull(registry, "registry");
        if (!registry.isFrozen()) {
            throw new IllegalArgumentException("Default protocol registry must be frozen");
        }
        if (defaultRegistry != null) {
            throw new IllegalStateException("Default protocol registry is already installed");
        }
        defaultRegistry = registry;
    }

    public static ProtocolRegistry getDefault() {
        var registry = defaultRegistry;
        if (registry == null) {
            throw new IllegalStateException("Default protocol registry has not been initialized");
        }
        return registry;
    }

    public static boolean hasDefault() {
        return defaultRegistry != null;
    }

    private void ensureMutable() {
        if (frozenSnapshot != null) {
            throw new IllegalStateException("Protocol registry is frozen");
        }
    }

    private Snapshot getFrozenSnapshot() {
        var snapshot = frozenSnapshot;
        if (snapshot == null) {
            throw new IllegalStateException("Protocol registry has not been frozen");
        }
        return snapshot;
    }

    private record Key(ClientVariant variant, int protocolVersion) {
    }

    private record Snapshot(
            Map<Key, Protocol> protocols,
            Map<ClientVariant, List<Protocol>> protocolsByVariant
    ) {
    }
}
