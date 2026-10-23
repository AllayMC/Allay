package org.allaymc.server.network.processor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.ClientState;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.*;
import java.util.function.Supplier;

/**
 * {@code PacketProcessorHolder} owns connection-local processors and selects them
 * by the connection's current state. Factory identity determines sharing:
 * multiple registrations using the same supplier object reuse one processor
 * within this holder, while different supplier objects are invoked
 * independently. Each created processor must report the packet type associated
 * with its registration.
 *
 * <p>
 * State transitions serialize their predecessor check and update under the
 * holder monitor. The current and previous states are separately volatile for
 * readers, so reading both accessors does not constitute an atomic pair
 * snapshot. The processor maps are populated during construction and then made
 * unmodifiable per state. Selection observes one current-state value but does
 * not lock processing against a subsequent state transition, leaving dispatch
 * scheduling to the owning session.
 *
 * @author Cool_Loong
 */
@Slf4j
public final class PacketProcessorHolder {
    /**
     * {@code processors} holds one unmodifiable packet-type map per client state
     * after construction; processor instances remain shared across keys using the
     * same supplier identity.
     */
    private final EnumMap<ClientState, Map<BedrockPacketType, PacketProcessor<BedrockPacket>>> processors;

    // clientState and lastClientState change together under the transition
    // monitor, with the previous state written first. Separate volatile reads
    // do not provide a coherent pair snapshot.
    /**
     * {@code clientState} selects the active processor map. Standalone holders
     * start disconnected until an accepted transition changes this value.
     */
    @Getter
    private volatile ClientState clientState = ClientState.DISCONNECTED;
    /**
     * {@code lastClientState} records the state preceding the last accepted
     * transition. It remains {@code null} until any transition succeeds.
     */
    @Getter
    private volatile ClientState lastClientState = null;

    /**
     * Creates processor instances from a nonnull frozen registry. An unfrozen
     * registry or a processor whose reported packet type disagrees with its key
     * causes {@code IllegalStateException}; a {@code null} factory result is rejected as well.
     *
     * <p>
     * An identity map invokes each distinct supplier object once, preserving
     * shared connection-local processor state across registrations using that
     * object. Equal but distinct suppliers are not merged. Successfully created
     * instances are not disposed if a later factory fails, so factories must
     * not rely on constructor rollback for cleanup. Each state's final lookup
     * map is made unmodifiable before construction returns.
     */
    public PacketProcessorHolder(PacketProcessorRegistry registry) {
        Objects.requireNonNull(registry, "registry");
        if (!registry.isFrozen()) {
            throw new IllegalStateException("Packet processor registry must be frozen");
        }

        this.processors = new EnumMap<>(ClientState.class);
        for (ClientState state : ClientState.values()) {
            processors.put(state, new HashMap<>());
        }

        // Use supplier identity rather than equals so only explicitly shared
        // factories merge processor state across registrations within this
        // connection.
        var processorInstances = new IdentityHashMap<Supplier<? extends PacketProcessor<?>>, PacketProcessor<BedrockPacket>>();
        for (var entry : registry.getFactories().entrySet()) {
            var key = entry.getKey();
            var factory = entry.getValue();
            var processor = processorInstances.get(factory);
            if (processor == null) {
                processor = createProcessor(factory);
                processorInstances.put(factory, processor);
            }

            if (processor.getPacketType() != key.packetType()) {
                throw new IllegalStateException(
                        "Packet processor factory registered for " + key.packetType() +
                        " created processor for " + processor.getPacketType()
                );
            }
            processors.get(key.state()).put(key.packetType(), processor);
        }

        for (var state : ClientState.values()) {
            processors.put(state, Map.copyOf(processors.get(state)));
        }
    }

    /**
     * Invokes a supplier and rejects a {@code null} processor result. The generic cast
     * supplies the holder's uniform storage type; construction separately
     * checks the reported packet type against each registration key.
     */
    @SuppressWarnings("unchecked")
    private static PacketProcessor<BedrockPacket> createProcessor(Supplier<? extends PacketProcessor<?>> factory) {
        var processor = Objects.requireNonNull(factory.get(), "Packet processor factory returned null");
        return (PacketProcessor<BedrockPacket>) processor;
    }

    /**
     * Returns the processor matching a nonnull packet in the currently observed
     * client state. Missing registrations return {@code null}, and a concurrent state
     * transition can occur after lookup without changing the returned processor
     * reference.
     */
    public PacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet) {
        var map = processors.get(clientState);
        return map != null ? map.get(packet.getPacketType()) : null;
    }

    /**
     * Attempts a client-state transition with warnings enabled for rejected
     * requests. The synchronized overload applies predecessor validation,
     * returns whether a transition occurred, and leaves both state fields
     * unchanged when it rejects the request.
     */
    public boolean setClientState(ClientState clientState) {
        return setClientState(clientState, true);
    }

    /**
     * Changes state when the nonnull target declares the current state as its
     * predecessor, or declares no predecessor. Requesting the current state
     * again or skipping a required predecessor returns {@code false}, optionally
     * logging a warning.
     *
     * <p>
     * The predecessor check and both writes share one monitor so concurrent
     * transitions cannot each validate against the same stale current state. A
     * successful transition records the old state before publishing the new
     * one. Separate volatile readers still need not observe a coherent pair,
     * and this lock does not serialize processor execution against state
     * changes.
     */
    public synchronized boolean setClientState(ClientState clientState, boolean warnIfFailed) {
        // Keep predecessor validation and publication under one monitor so a
        // second transition cannot invalidate the state accepted by the first
        // check.
        if (this.clientState == clientState) {
            if (warnIfFailed) {
                log.warn("Client state is already in {}", this.clientState);
            }
            return false;
        }

        if (clientState.getPreviousState() != null && this.clientState != clientState.getPreviousState()) {
            if (warnIfFailed) {
                log.warn("Failed to set client state to {}. Current is {}", clientState, this.clientState);
            }
            return false;
        }

        this.lastClientState = this.clientState;
        this.clientState = clientState;
        return true;
    }

}
