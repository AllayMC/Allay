package org.allaymc.server.network.protocol;

import org.allaymc.api.registry.Registries;
import org.allaymc.server.item.creative.AllayCreativeItemRegistry;
import org.allaymc.server.network.protocol.v1001.Protocol_v1001;
import org.allaymc.server.network.protocol.v766.Protocol_v766_NetEase;
import org.allaymc.server.network.protocol.v818.Protocol_v818;
import org.allaymc.server.network.protocol.v819.Protocol_v819;
import org.allaymc.server.network.protocol.v819.Protocol_v819_NetEase;
import org.allaymc.server.network.protocol.v827.Protocol_v827;
import org.allaymc.server.network.protocol.v844.Protocol_v844;
import org.allaymc.server.network.protocol.v859.Protocol_v859;
import org.allaymc.server.network.protocol.v860.Protocol_v860;
import org.allaymc.server.network.protocol.v898.Protocol_v898;
import org.allaymc.server.network.protocol.v924.Protocol_v924;
import org.allaymc.server.network.protocol.v944.Protocol_v944;
import org.allaymc.server.network.protocol.v975.Protocol_v975;
import org.allaymc.server.registry.InternalRegistries;

import java.util.*;

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

    public static ProtocolRegistry createDefault() {
        freezeSourceRegistries();

        var registry = new ProtocolRegistry();
        var supported = new ArrayList<Protocol>();
        supported.add(new Protocol_v766_NetEase());
        supported.add(new Protocol_v818());
        supported.add(new Protocol_v819());
        supported.add(new Protocol_v819_NetEase());
        supported.add(new Protocol_v827());
        supported.add(new Protocol_v844());
        supported.add(new Protocol_v859());
        supported.add(new Protocol_v860());
        supported.add(new Protocol_v898());
        supported.add(new Protocol_v924());
        supported.add(new Protocol_v944());
        supported.add(new Protocol_v975());
        supported.add(new Protocol_v1001());

        for (var protocol : supported) {
            protocol.initialize();
            registry.register(protocol);
        }
        registry.freeze();
        return registry;
    }

    private static void freezeSourceRegistries() {
        Registries.ITEMS.freeze();
        Registries.BLOCKS.freeze();
        Registries.BLOCK_STATE_PALETTE.freeze();
        Registries.RECIPES.freeze();
        Registries.ENTITIES.freeze();
        Registries.BIOMES.freeze();
        Registries.DIMENSIONS.freeze();
        Registries.PACKS.freeze();
        InternalRegistries.TRIM_PATTERNS.freeze();
        InternalRegistries.TRIM_MATERIALS.freeze();
        ((AllayCreativeItemRegistry) Registries.CREATIVE_ITEMS).freeze();
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
