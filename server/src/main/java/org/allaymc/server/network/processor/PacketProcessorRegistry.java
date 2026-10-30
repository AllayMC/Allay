package org.allaymc.server.network.processor;

import lombok.Getter;
import org.allaymc.api.player.ClientState;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.*;
import java.util.function.Supplier;

/**
 * {@code PacketProcessorRegistry} stores processor factories by client state and
 * packet type during protocol initialization. Registration replaces an existing
 * factory for the same pair,
 * allowing a later protocol override to specialize one state without rebuilding
 * unrelated registrations. A factory object can be stored under several states
 * so connection holders can share one created processor across those entries.
 *
 * <p>
 * Mutation and freezing use the registry monitor. Freezing copies the
 * insertion-ordered mapping into an unmodifiable snapshot before publishing the
 * volatile {@code frozen} flag; readers require that flag before obtaining the
 * snapshot. The snapshot retains factory identities rather than created
 * processors, leaving connection-local state allocation to each holder. No
 * registration can be added after freezing, and factory output types are
 * checked when holders are constructed.
 *
 * @author daoge_cmd
 */
public final class PacketProcessorRegistry {
    /**
     * {@code factories} stores insertion-ordered registrations while mutable.
     * {@code register} and {@code freeze} access this map under the registry
     * monitor, and it is never exposed
     * directly.
     */
    private final Map<Key, Supplier<? extends PacketProcessor<?>>> factories = new LinkedHashMap<>();

    // The frozen flag publishes frozenFactories after its copied map is
    // assigned. Readers test the flag first; these fields are never reset after
    // publication.
    /**
     * {@code frozen} marks the registry as frozen and publishes its completed
     * factory snapshot to readers.
     */
    @Getter
    private volatile boolean frozen;
    /**
     * {@code frozenFactories} holds the unmodifiable registration snapshot once
     * frozen. Factory objects retain their original identities for holder-side
     * deduplication.
     */
    private Map<Key, Supplier<? extends PacketProcessor<?>>> frozenFactories;

    /**
     * Registers a nonnull factory for one nonnull state and packet type. Later
     * registration for the same key replaces its factory; frozen registries
     * reject this operation before validating its arguments.
     */
    public synchronized void register(
            ClientState state,
            BedrockPacketType packetType,
            Supplier<? extends PacketProcessor<?>> factory
    ) {
        ensureMutable();
        factories.put(
                new Key(Objects.requireNonNull(state, "state"), Objects.requireNonNull(packetType, "packetType")),
                Objects.requireNonNull(factory, "factory")
        );
    }

    /**
     * Registers one factory object for a nonempty set of client states. The
     * set, each state, packet type, and factory must be nonnull, and callers
     * must keep the supplied set stable throughout this operation.
     *
     * <p>
     * Every state is validated before the first registration changes. Thus an
     * empty set or {@code null} element cannot leave a partially updated set of
     * registrations. Valid entries replace factories under the registry
     * monitor, and the unchanged factory identity lets a holder reuse one
     * processor across all of these state keys. Frozen registries reject the
     * operation.
     */
    public synchronized void register(
            Set<ClientState> states,
            BedrockPacketType packetType,
            Supplier<? extends PacketProcessor<?>> factory
    ) {
        ensureMutable();
        Objects.requireNonNull(states, "states");
        Objects.requireNonNull(packetType, "packetType");
        Objects.requireNonNull(factory, "factory");
        if (states.isEmpty()) {
            throw new IllegalArgumentException("At least one client state is required");
        }

        // Validate the complete state set before changing any key so a bad
        // later state cannot leave earlier registrations partially replaced.
        for (var state : states) {
            Objects.requireNonNull(state, "state");
        }
        for (var state : states) {
            factories.put(new Key(state, packetType), factory);
        }
    }

    /**
     * Publishes an unmodifiable copy of the current factory mapping and
     * prevents later registration. Repeated calls return without replacing the
     * previously published snapshot, preserving its identity for subsequent
     * connection holders.
     *
     * <p>
     * The copied mapping is assigned before the volatile {@code frozen} flag is set.
     * Readers that observe the flag can therefore access the completed snapshot
     * without acquiring this monitor. The copy retains supplier references, so
     * immutability protects registry membership rather than any mutable state
     * inside a supplier.
     */
    public synchronized void freeze() {
        if (frozen) {
            return;
        }

        // Assign the finished snapshot before the volatile flag so readers
        // cannot observe a frozen registry whose factory map is still absent.
        frozenFactories = Collections.unmodifiableMap(new LinkedHashMap<>(factories));
        frozen = true;
    }

    /**
     * Returns the frozen factory snapshot without copying it again. Calling
     * before freezing throws {@code IllegalStateException}; after publication the map
     * cannot change, although its supplier objects remain shared references.
     */
    Map<Key, Supplier<? extends PacketProcessor<?>>> getFactories() {
        if (!frozen) {
            throw new IllegalStateException("Packet processor registry is not frozen");
        }
        return frozenFactories;
    }

    /**
     * Rejects mutation once the registry has published its frozen snapshot.
     * Callers hold the registry monitor while checking this condition and
     * changing registrations, preventing a concurrent freeze between those
     * operations.
     */
    private void ensureMutable() {
        if (frozen) {
            throw new IllegalStateException("Packet processor registry is frozen");
        }
    }

    /**
     * {@code Key} identifies a processor registration by nonnull client state and packet
     * type.
     *
     * @param state matching client lifecycle state
     * @param packetType matching Bedrock packet type
     */
    public record Key(ClientState state, BedrockPacketType packetType) {
        /**
         * Creates a registration key after rejecting a {@code null} state or packet
         * type. The key retains both supplied enum values for equality-based
         * registration lookup and replacement in the registry.
         */
        public Key {
            Objects.requireNonNull(state, "state");
            Objects.requireNonNull(packetType, "packetType");
        }
    }

}
