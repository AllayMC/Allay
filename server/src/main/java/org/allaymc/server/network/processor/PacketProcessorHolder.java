package org.allaymc.server.network.processor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.ClientState;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

import java.util.*;
import java.util.function.Supplier;

/**
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

    public PacketProcessor<BedrockPacket> getProcessor(BedrockPacket packet) {
        var map = processors.get(clientState);
        return map != null ? map.get(packet.getPacketType()) : null;
    }

    public boolean setClientState(ClientState clientState) {
        return setClientState(clientState, true);
    }

    // We use lock here because this method won't be called frequently
    // Instead, method getClientState() will be called frequently
    public synchronized boolean setClientState(ClientState clientState, boolean warnIfFailed) {
        // Already in the target state
        if (this.clientState == clientState) {
            if (warnIfFailed) {
                log.warn("Client state is already in {}", this.clientState);
            }
            return false;
        }

        // PreviousState != null means that we should check if the previous state is correct
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
