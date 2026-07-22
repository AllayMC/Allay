package org.allaymc.server.network.protocol;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.server.Server;
import org.allaymc.server.eventbus.event.network.PacketReceiveEvent;
import org.allaymc.server.eventbus.event.network.PacketSendEvent;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.world.AllayWorld;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

import java.util.Collection;
import java.util.Objects;

/**
 * Binds an initialized protocol to one connection and owns its packet processors and client state.
 *
 * <p>Inbound and outbound events are applied here so validation and dispatch always use the packet
 * that event listeners leave behind.</p>
 */
@Slf4j
@Getter
public final class ProtocolSession {
    private final Protocol protocol;
    private final BedrockServerSession session;
    private final PacketProcessorHolder processorHolder;
    @Getter(AccessLevel.NONE)
    private boolean codecInstalled;

    /**
     * Creates connection-local state for an initialized protocol.
     *
     * @param protocol the selected protocol
     * @param session the connection that will use the protocol
     * @throws IllegalArgumentException if the protocol is not initialized
     */
    public ProtocolSession(Protocol protocol, BedrockServerSession session) {
        this.protocol = Objects.requireNonNull(protocol, "protocol");
        this.session = Objects.requireNonNull(session, "session");
        if (!protocol.isInitialized()) {
            throw new IllegalArgumentException("Protocol must be initialized");
        }
        this.processorHolder = protocol.createProcessorHolder();
        if (!processorHolder.setClientState(ClientState.CONNECTED)) {
            throw new IllegalStateException("Failed to initialize protocol session state");
        }
    }

    /**
     * Installs the selected codec and its immutable definition registries.
     *
     * <p>If installation fails, the previous bootstrap codec and definition registries are restored.</p>
     *
     * @throws IllegalStateException if installation already ran or no bootstrap codec is present
     */
    public synchronized void installCodec() {
        if (codecInstalled) {
            throw new IllegalStateException("Protocol codec is already installed");
        }

        var previousCodec = session.getCodec();
        if (previousCodec == null) {
            throw new IllegalStateException("Session must have a bootstrap codec before protocol installation");
        }
        var previousHelper = session.getPeer().getCodecHelper();
        var previousItemDefinitions = previousHelper.getItemDefinitions();
        var previousBlockDefinitions = previousHelper.getBlockDefinitions();
        try {
            session.setCodec(protocol.getCodec());
            var helper = session.getPeer().getCodecHelper();
            helper.setItemDefinitions(protocol.getItemDefinitionRegistry());
            helper.setBlockDefinitions(protocol.getBlockDefinitionRegistry());
            codecInstalled = true;
        } catch (RuntimeException exception) {
            try {
                session.setCodec(previousCodec);
                var restoredHelper = session.getPeer().getCodecHelper();
                restoredHelper.setItemDefinitions(previousItemDefinitions);
                restoredHelper.setBlockDefinitions(previousBlockDefinitions);
            } catch (RuntimeException rollbackException) {
                exception.addSuppressed(rollbackException);
            }
            throw exception;
        }
    }

    /**
     * Applies the receive event and dispatches an inbound packet to this connection's processor.
     *
     * <p>Processors run their asynchronous phase immediately. When they request synchronous work,
     * that work is queued on the player's world or run directly if no world is available.</p>
     *
     * @param player the receiving player
     * @param packet the inbound packet
     * @return {@link PacketSignal#HANDLED}; protocol dispatch consumes every inbound packet
     */
    public PacketSignal receive(AllayPlayer player, BedrockPacket packet) {
        Objects.requireNonNull(player, "player");
        Objects.requireNonNull(packet, "packet");
        if (!getClientState().canHandlePackets()) {
            return PacketSignal.HANDLED;
        }

        var event = new PacketReceiveEvent(player, packet);
        if (!event.call()) {
            return PacketSignal.HANDLED;
        }

        packet = event.getPacket();
        if (packet == null) {
            log.warn("Ignored a null packet produced by PacketReceiveEvent for {}", protocol);
            return PacketSignal.HANDLED;
        }
        var processor = processorHolder.getProcessor(packet);
        if (processor == null) {
            log.debug("Received a packet without a processor: {}, client state: {}", packet, getClientState());
            return PacketSignal.HANDLED;
        }

        var world = player.getControlledEntity() == null ? null : player.getControlledEntity().getWorld();
        var receiveTime = world == null ? Server.getInstance().getTick() : player.getControlledEntity().getTick();
        if (processor.handleAsync(player, packet, receiveTime) == PacketSignal.HANDLED) {
            return PacketSignal.HANDLED;
        }

        if (world == null) {
            log.warn(
                    "Cannot queue sync packet {} for player {} because the player is not in a world",
                    packet.getPacketType().name(),
                    player
            );
            processor.handleSync(player, packet, receiveTime);
        } else {
            ((AllayWorld) world).addSyncPacketToQueue(player, packet, receiveTime, processor);
        }
        return PacketSignal.HANDLED;
    }

    /**
     * Runs the synchronous phase of the processor selected for the current client state.
     *
     * @param player the receiving player
     * @param packet the inbound packet
     * @param receiveTime the tick at which the packet was received
     */
    public void handleSync(AllayPlayer player, BedrockPacket packet, long receiveTime) {
        var processor = processorHolder.getProcessor(packet);
        if (processor == null) {
            log.debug("Received a sync packet without a processor: {}, client state: {}", packet, getClientState());
            return;
        }
        processor.handleSync(player, packet, receiveTime);
    }

    /**
     * Applies the send event and queues a packet for transmission.
     *
     * @param player the sending player
     * @param packet the outbound packet
     */
    public void send(AllayPlayer player, BedrockPacket packet) {
        sendPacket(player, packet, false);
    }

    /**
     * Applies the send event and transmits a packet immediately.
     *
     * @param player the sending player
     * @param packet the outbound packet
     */
    public void sendImmediately(AllayPlayer player, BedrockPacket packet) {
        sendPacket(player, packet, true);
    }

    /**
     * Applies the send event, transmits a packet immediately, and returns the actual packet sent.
     *
     * @param player the sending player
     * @param packet the original outbound packet
     * @return the post-event packet, or {@code null} if transmission was rejected or cancelled
     */
    public BedrockPacket sendImmediatelyAndGet(AllayPlayer player, BedrockPacket packet) {
        return sendPacket(player, packet, true);
    }

    /**
     * Queues each packet for transmission, applying the send event separately to every packet.
     *
     * @param player the sending player
     * @param packets the outbound packets
     */
    public void send(AllayPlayer player, Collection<? extends BedrockPacket> packets) {
        for (var packet : packets) {
            sendPacket(player, packet, false);
        }
    }

    /**
     * Transmits each packet immediately, applying the send event separately to every packet.
     *
     * @param player the sending player
     * @param packets the outbound packets
     */
    public void sendImmediately(AllayPlayer player, Collection<? extends BedrockPacket> packets) {
        for (var packet : packets) {
            sendPacket(player, packet, true);
        }
    }

    private BedrockPacket sendPacket(AllayPlayer player, BedrockPacket packet, boolean immediately) {
        Objects.requireNonNull(player, "player");
        Objects.requireNonNull(packet, "packet");
        if (!getClientState().canHandlePackets()) {
            return null;
        }

        var event = new PacketSendEvent(player, packet);
        if (!event.call()) {
            return null;
        }
        var outgoingPacket = event.getPacket();
        if (outgoingPacket == null) {
            log.warn("Refused to send a null packet produced by PacketSendEvent for {}", protocol);
            return null;
        }
        if (!getClientState().canHandlePackets() || !session.isConnected()) {
            return null;
        }
        var packetDefinition = protocol.getCodec().getPacketDefinition(outgoingPacket.getClass());
        if (packetDefinition == null) {
            log.warn(
                    "Refused to send unregistered packet {} through {}",
                    outgoingPacket.getClass().getName(),
                    protocol
            );
            return null;
        }
        if (packetDefinition.recipient() == PacketRecipient.SERVER) {
            log.warn(
                    "Refused to send serverbound packet {} through {}",
                    outgoingPacket.getClass().getName(),
                    protocol
            );
            return null;
        }

        if (immediately) {
            session.sendPacketImmediately(outgoingPacket);
        } else {
            session.sendPacket(outgoingPacket);
        }
        return outgoingPacket;
    }

    /**
     * Returns the current packet-processing state.
     *
     * @return the current client state
     */
    public ClientState getClientState() {
        return processorHolder.getClientState();
    }

    /**
     * Returns the state preceding the most recent successful transition.
     *
     * @return the previous state, or {@code null} before the first transition
     */
    public ClientState getLastClientState() {
        return processorHolder.getLastClientState();
    }

    /**
     * Advances the packet-processing state when the requested transition is valid.
     *
     * @param state the target state
     * @return {@code true} if the state changed
     */
    public boolean setClientState(ClientState state) {
        return processorHolder.setClientState(state);
    }

    /**
     * Advances the packet-processing state when the requested transition is valid.
     *
     * @param state the target state
     * @param warnIfFailed whether to log rejected transitions
     * @return {@code true} if the state changed
     */
    public boolean setClientState(ClientState state, boolean warnIfFailed) {
        return processorHolder.setClientState(state, warnIfFailed);
    }
}
