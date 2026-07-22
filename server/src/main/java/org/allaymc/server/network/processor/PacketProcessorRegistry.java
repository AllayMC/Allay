package org.allaymc.server.network.processor;

import lombok.Getter;
import org.allaymc.api.player.ClientState;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Stores packet processor factories by client state and packet type.
 *
 * <p>The registry is mutable while a protocol is being initialized. Once frozen, it exposes an
 * immutable snapshot that can be used to create connection-specific processor holders.</p>
 *
 * @author daoge_cmd
 */
public final class PacketProcessorRegistry {
    private final Map<Key, Supplier<? extends PacketProcessor<?>>> factories = new LinkedHashMap<>();

    @Getter
    private volatile boolean frozen;
    private Map<Key, Supplier<? extends PacketProcessor<?>>> frozenFactories;

    /**
     * Register a processor factory for one client state.
     *
     * <p>A later registration for the same state and packet type replaces the previous factory.</p>
     *
     * @param state      the client state
     * @param packetType the packet type
     * @param factory    the connection-specific processor factory
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
     * Register the same processor factory for one or more client states.
     *
     * <p>The exact factory object is stored for every state. A holder therefore creates one processor
     * instance for all entries that share that factory object.</p>
     *
     * @param packetType the packet type
     * @param factory    the connection-specific processor factory
     * @param states     the client states
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

        for (var state : states) {
            Objects.requireNonNull(state, "state");
        }
        for (var state : states) {
            factories.put(new Key(state, packetType), factory);
        }
    }

    /**
     * Freeze this registry and publish its immutable factory snapshot.
     *
     * <p>Calling this method more than once is harmless.</p>
     */
    public synchronized void freeze() {
        if (frozen) {
            return;
        }

        frozenFactories = Collections.unmodifiableMap(new LinkedHashMap<>(factories));
        frozen = true;
    }

    /**
     * Get the immutable frozen factory snapshot.
     *
     * @return the frozen factory snapshot
     * @throws IllegalStateException if this registry has not been frozen
     */
    Map<Key, Supplier<? extends PacketProcessor<?>>> getFactories() {
        if (!frozen) {
            throw new IllegalStateException("Packet processor registry is not frozen");
        }
        return frozenFactories;
    }

    private void ensureMutable() {
        if (frozen) {
            throw new IllegalStateException("Packet processor registry is frozen");
        }
    }

    /**
     * Identifies a processor registration by client state and packet type.
     *
     * @param state      the client state
     * @param packetType the packet type
     */
    public record Key(ClientState state, BedrockPacketType packetType) {
        public Key {
            Objects.requireNonNull(state, "state");
            Objects.requireNonNull(packetType, "packetType");
        }
    }

}
