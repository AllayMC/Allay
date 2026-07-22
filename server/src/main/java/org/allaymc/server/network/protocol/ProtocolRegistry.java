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
 * Stores exact protocol matches keyed by client variant and protocol version.
 *
 * <p>Registrations are mutable during startup. {@link #freeze()} publishes an immutable
 * snapshot that must exist before any lookup is performed.</p>
 */
public final class ProtocolRegistry {
    private static volatile ProtocolRegistry defaultRegistry;

    private Map<Key, Protocol> mutableProtocols = new HashMap<>();
    private volatile Snapshot frozenSnapshot;

    /**
     * Registers an initialized protocol under its variant and protocol version.
     *
     * @param protocol the protocol to register
     * @throws IllegalArgumentException if the protocol is uninitialized or duplicates an existing key
     * @throws IllegalStateException if this registry is frozen
     */
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

    /**
     * Publishes an immutable snapshot and prevents further registration.
     *
     * @throws IllegalStateException if the registry is empty or already frozen
     */
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

    /**
     * Resolves an exact protocol match.
     *
     * @param variant the client variant
     * @param protocolVersion the numeric protocol version
     * @return the matching protocol, or {@code null} when the exact combination is unsupported
     * @throws IllegalStateException if this registry has not been frozen
     */
    public Protocol resolve(ClientVariant variant, int protocolVersion) {
        var snapshot = getFrozenSnapshot();
        return snapshot.protocols().get(new Key(Objects.requireNonNull(variant, "variant"), protocolVersion));
    }

    /**
     * Returns the oldest supported protocol for a client variant.
     *
     * @param variant the client variant
     * @return the oldest protocol, or {@code null} when the variant has no registered protocols
     * @throws IllegalStateException if this registry has not been frozen
     */
    public Protocol getLowest(ClientVariant variant) {
        var supported = getSupported(variant);
        return supported.isEmpty() ? null : supported.getFirst();
    }

    /**
     * Returns the newest supported protocol for a client variant.
     *
     * @param variant the client variant
     * @return the newest protocol, or {@code null} when the variant has no registered protocols
     * @throws IllegalStateException if this registry has not been frozen
     */
    public Protocol getLatest(ClientVariant variant) {
        var supported = getSupported(variant);
        return supported.isEmpty() ? null : supported.getLast();
    }

    /**
     * Returns all supported protocols for a client variant in ascending version order.
     *
     * @param variant the client variant
     * @return an immutable list, which may be empty
     * @throws IllegalStateException if this registry has not been frozen
     */
    public List<Protocol> getSupported(ClientVariant variant) {
        var snapshot = getFrozenSnapshot();
        return snapshot.protocolsByVariant().get(Objects.requireNonNull(variant, "variant"));
    }

    /**
     * Returns every registered protocol without a defined iteration order.
     *
     * @return an immutable collection of protocols
     * @throws IllegalStateException if this registry has not been frozen
     */
    public Collection<Protocol> getProtocols() {
        return List.copyOf(getFrozenSnapshot().protocols().values());
    }

    /**
     * Checks whether the immutable lookup snapshot has been published.
     *
     * @return {@code true} when lookups are available and mutations are rejected
     */
    public boolean isFrozen() {
        return frozenSnapshot != null;
    }

    /**
     * Initializes the built-in protocols from frozen source registries.
     *
     * @return a frozen registry containing every built-in protocol
     */
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

    /**
     * Installs the process-wide default registry once.
     *
     * @param registry the frozen registry to install
     * @throws IllegalArgumentException if the registry is mutable
     * @throws IllegalStateException if a default registry is already installed
     */
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

    /**
     * Returns the process-wide default registry.
     *
     * @return the installed default registry
     * @throws IllegalStateException if no default registry has been installed
     */
    public static ProtocolRegistry getDefault() {
        var registry = defaultRegistry;
        if (registry == null) {
            throw new IllegalStateException("Default protocol registry has not been initialized");
        }
        return registry;
    }

    /**
     * Checks whether the process-wide default registry is installed.
     *
     * @return {@code true} when {@link #getDefault()} is available
     */
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
