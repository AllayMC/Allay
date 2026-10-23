package org.allaymc.server.network.protocol;

import org.allaymc.api.registry.Registries;
import org.allaymc.server.item.creative.AllayCreativeItemRegistry;
import org.allaymc.server.network.protocol.v1001.Protocol_v1001;
import org.allaymc.server.network.protocol.v2168.Protocol_v2168;
import org.allaymc.server.network.protocol.v2169.Protocol_v2169;
import org.allaymc.server.network.protocol.v2193.Protocol_v2193;
import org.allaymc.server.network.protocol.v766.Protocol_v766_NetEase;
import org.allaymc.server.network.protocol.v818.Protocol_v818;
import org.allaymc.server.network.protocol.v819.Protocol_v819;
import org.allaymc.server.network.protocol.v819.Protocol_v819_NetEase;
import org.allaymc.server.network.protocol.v827.Protocol_v827;
import org.allaymc.server.network.protocol.v844.Protocol_v844;
import org.allaymc.server.network.protocol.v859.Protocol_v859;
import org.allaymc.server.network.protocol.v860.Protocol_v860;
import org.allaymc.server.network.protocol.v860.Protocol_v860_NetEase;
import org.allaymc.server.network.protocol.v898.Protocol_v898;
import org.allaymc.server.network.protocol.v924.Protocol_v924;
import org.allaymc.server.network.protocol.v944.Protocol_v944;
import org.allaymc.server.network.protocol.v975.Protocol_v975;
import org.allaymc.server.registry.InternalRegistries;

import java.util.*;

/**
 * {@code ProtocolRegistry} stores supported protocols by the exact combination
 * of client distribution and wire version. Startup registration accepts
 * initialized protocols, while all lookups require a frozen registry. No
 * nearest-version search or fallback crosses a missing key, even when related
 * protocol classes share implementation through inheritance.
 *
 * <p> Freezing builds both the exact lookup map and sorted per-variant lists
 * before publishing one volatile snapshot. Readers therefore obtain indexes
 * derived from the same registration set after publication. Synchronized
 * mutation prevents registration from racing with snapshot construction, and
 * publication discards the mutable map. A separate volatile reference exposes
 * the process-wide default only after an already frozen registry has been
 * installed.
 */
public final class ProtocolRegistry {
    /**
     * {@code defaultRegistry} holds the process-wide frozen registry after
     * synchronized installation. Volatile publication exposes the installed
     * reference to readers without requiring the installation monitor.
     */
    private static volatile ProtocolRegistry defaultRegistry;

    /**
     * {@code mutableProtocols} contains startup registrations before freezing.
     * Synchronized {@code register} and {@code freeze} operations own this map;
     * {@code freeze} replaces it with {@code null} after constructing both
     * lookup indexes.
     */
    private Map<Key, Protocol> mutableProtocols = new HashMap<>();
    /**
     * {@code frozenSnapshot} publishes both lookup indexes together. A
     * {@code null} value makes lookups unavailable; a nonnull volatile value
     * prevents further startup registration through {@code ensureMutable}.
     */
    private volatile Snapshot frozenSnapshot;

    /**
     * Registers a nonnull initialized protocol under its exact variant and
     * version. Frozen registries reject mutation, and duplicate keys throw
     * without replacing the earlier protocol; callers must invoke
     * {@code initialize} each protocol before registration.
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
     * Publishes lookup indexes for the current nonempty registration set and
     * rejects subsequent mutation. Empty or already frozen registries throw
     * {@code IllegalStateException} instead of producing another snapshot.
     *
     * <p> Exact matches and per-variant lists are derived from the same copied
     * map, with each list sorted by increasing wire version. The synchronized
     * method constructs all indexes before discarding mutable storage and
     * writing the volatile snapshot reference. Readers that observe that
     * reference can therefore use either index without seeing partially
     * constructed registration data.
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
        // Publish only after both indexes are complete; the volatile write
        // makes the whole snapshot visible to lookup readers.
        mutableProtocols = null;
        frozenSnapshot = snapshot;
    }

    /**
     * Resolves a nonnull client variant and wire version by exact key. The
     * registry must be frozen; unsupported combinations return {@code null}
     * rather than selecting a nearby version or another client distribution.
     */
    public Protocol resolve(ClientVariant variant, int protocolVersion) {
        var snapshot = getFrozenSnapshot();
        return snapshot.protocols().get(new Key(Objects.requireNonNull(variant, "variant"), protocolVersion));
    }

    /**
     * Returns the lowest registered wire version for a nonnull client variant.
     * The registry must be frozen; a variant without registrations returns
     * {@code null}, regardless of versions registered for other distributions.
     */
    public Protocol getLowest(ClientVariant variant) {
        var supported = getSupported(variant);
        return supported.isEmpty() ? null : supported.getFirst();
    }

    /**
     * Returns the highest registered wire version for a nonnull client variant.
     * The registry must be frozen; a variant without registrations returns
     * {@code null} instead of borrowing another distribution's newest protocol.
     */
    public Protocol getLatest(ClientVariant variant) {
        var supported = getSupported(variant);
        return supported.isEmpty() ? null : supported.getLast();
    }

    /**
     * Returns the unmodifiable ascending-version list for a nonnull client
     * variant. The registry must be frozen, and the list is empty when that
     * distribution has no registrations; protocol instances retain their
     * identities.
     */
    public List<Protocol> getSupported(ClientVariant variant) {
        var snapshot = getFrozenSnapshot();
        return snapshot.protocolsByVariant().get(Objects.requireNonNull(variant, "variant"));
    }

    /**
     * Returns an unmodifiable list of every protocol in the frozen registry.
     * Iteration order is unspecified, and the list retains the registered
     * protocol objects rather than creating copies of their runtime data.
     */
    public Collection<Protocol> getProtocols() {
        return List.copyOf(getFrozenSnapshot().protocols().values());
    }

    /**
     * Returns whether the complete immutable lookup snapshot has been
     * published.
     */
    public boolean isFrozen() {
        return frozenSnapshot != null;
    }

    /**
     * Creates and freezes a registry containing the explicitly supported
     * built-in protocol variants. Source registries are frozen first, then each
     * protocol is initialized; failures propagate without installing a
     * process-wide default or undoing source freezes.
     */
    public static ProtocolRegistry createDefault() {
        // Freeze shared inputs before building any version so later
        // registrations cannot give different protocols inconsistent startup
        // content.
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
        supported.add(new Protocol_v860_NetEase());
        supported.add(new Protocol_v898());
        supported.add(new Protocol_v924());
        supported.add(new Protocol_v944());
        supported.add(new Protocol_v975());
        supported.add(new Protocol_v1001());
        supported.add(new Protocol_v2168());
        supported.add(new Protocol_v2169());
        supported.add(new Protocol_v2193());

        for (var protocol : supported) {
            protocol.initialize();
            registry.register(protocol);
        }
        registry.freeze();
        return registry;
    }

    /**
     * Freezes the server registries consumed by built-in protocol
     * initialization. This changes the shared registries in sequence and has no
     * rollback, so callers must finish content registration before creating the
     * default protocol set.
     */
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
     * Installs a nonnull, already frozen registry as the process-wide default.
     * A mutable registry is rejected before installation, and any later
     * installation attempt throws instead of replacing the existing default.
     *
     * <p> The class monitor keeps validation of the empty default and
     * assignment in one synchronized operation. The volatile reference then
     * exposes the selected frozen registry to readers without requiring them to
     * acquire that monitor. Failed validation leaves the existing reference
     * unchanged, and this method never constructs or freezes a registry on the
     * caller's behalf.
     */
    public static synchronized void installDefault(ProtocolRegistry registry) {
        Objects.requireNonNull(registry, "registry");
        if (!registry.isFrozen()) {
            throw new IllegalArgumentException("Default protocol registry must be frozen");
        }
        if (defaultRegistry != null) {
            throw new IllegalStateException("Default protocol registry is already installed");
        }
        // Publish only the validated frozen registry after the synchronized
        // duplicate check, preserving a single process-wide negotiation source.
        defaultRegistry = registry;
    }

    /**
     * Returns the process-wide frozen registry installed for protocol
     * negotiation. An absent default throws {@code IllegalStateException};
     * calling this method does not construct protocols or invoke
     * {@code initialize} missing server registry content.
     */
    public static ProtocolRegistry getDefault() {
        var registry = defaultRegistry;
        if (registry == null) {
            throw new IllegalStateException("Default protocol registry has not been initialized");
        }
        return registry;
    }

    /**
     * Returns whether a process-wide default protocol registry has been
     * installed.
     */
    public static boolean hasDefault() {
        return defaultRegistry != null;
    }

    /**
     * Rejects mutation after the frozen lookup snapshot has been published.
     * Callers performing a mutation must hold this registry's monitor so the
     * check remains coordinated with registration and snapshot publication.
     */
    private void ensureMutable() {
        if (frozenSnapshot != null) {
            throw new IllegalStateException("Protocol registry is frozen");
        }
    }

    /**
     * Returns the currently published lookup snapshot or throws when freezing
     * has not completed. The single volatile read keeps the returned pair of
     * indexes tied to the same publication.
     */
    private Snapshot getFrozenSnapshot() {
        var snapshot = frozenSnapshot;
        if (snapshot == null) {
            throw new IllegalStateException("Protocol registry has not been frozen");
        }
        return snapshot;
    }

    /**
     * {@code Key} identifies one exact client distribution and wire-version
     * registration. {@code variant} and {@code protocolVersion} form the
     * composite lookup key, so equal wire versions from different distributions
     * remain distinct registrations.
     *
     * @param variant client distribution identity
     *
     * @param protocolVersion numeric wire protocol version
     */
    private record Key(ClientVariant variant, int protocolVersion) {
    }

    /**
     * {@code Snapshot} represents the two lookup indexes published together for
     * registry readers. {@code protocols} and {@code protocolsByVariant} come
     * from the same frozen registration set; {@code freeze} constructs both
     * unmodifiable indexes before publishing this record through
     * {@code frozenSnapshot}.
     *
     * @param protocols exact registration lookup
     *
     * @param protocolsByVariant ascending-version lists by distribution
     */
    private record Snapshot(
            Map<Key, Protocol> protocols,
            Map<ClientVariant, List<Protocol>> protocolsByVariant
    ) {
    }
}
