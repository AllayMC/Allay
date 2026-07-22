package org.allaymc.server.network.processor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.ClientState;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.*;
import java.util.function.Supplier;

/**
 * Holds connection-local processor instances and selects them by client state and packet type.
 *
 * <p>A factory registered for multiple states is instantiated once so its connection-local state
 * is shared across those registrations.</p>
 *
 * @author Cool_Loong
 */
@Slf4j
public final class PacketProcessorHolder {
    private final EnumMap<ClientState, Map<BedrockPacketType, PacketProcessor<BedrockPacket>>> processors;

    @Getter
    private volatile ClientState clientState = ClientState.DISCONNECTED;
    @Getter
    private volatile ClientState lastClientState = null;

    /**
     * Create a connection-specific processor holder from a frozen registry.
     *
     * @param registry the frozen processor factory registry
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

    @SuppressWarnings("unchecked")
    private static PacketProcessor<BedrockPacket> createProcessor(Supplier<? extends PacketProcessor<?>> factory) {
        var processor = Objects.requireNonNull(factory.get(), "Packet processor factory returned null");
        return (PacketProcessor<BedrockPacket>) processor;
    }

    /**
     * Returns the processor registered for the packet in the current client state.
     *
     * @param packet the packet to process
     * @return the processor, or {@code null} when no registration matches
     */
    public PacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet) {
        var map = processors.get(clientState);
        return map != null ? map.get(packet.getPacketType()) : null;
    }

    /**
     * Advances to the requested client state and logs rejected transitions.
     *
     * @param clientState the target state
     * @return {@code true} if the state changed
     */
    public boolean setClientState(ClientState clientState) {
        return setClientState(clientState, true);
    }

    /**
     * Advances to the requested client state when its declared predecessor matches.
     *
     * @param clientState the target state
     * @param warnIfFailed whether to log a rejected transition
     * @return {@code true} if the state changed
     */
    public synchronized boolean setClientState(ClientState clientState, boolean warnIfFailed) {
        // Transitions are rare; synchronize the check-and-set while keeping volatile reads lock-free.
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
