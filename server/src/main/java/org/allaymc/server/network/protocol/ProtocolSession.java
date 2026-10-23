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
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

import java.util.Collection;
import java.util.Objects;

/**
 * {@code ProtocolSession} binds an initialized protocol to one transport
 * connection and selects its connection-local packet processors. Receive and
 * send events may replace packets, so dispatch and outbound validation use the
 * post-event value. Each session starts in the connected state independently of
 * other sessions using the same protocol.
 *
 * <p> Codec installation and switching update the transport before assigning
 * the selected protocol's definition registries to the resulting helper.
 * Runtime failures trigger an attempt to restore the previous codec and
 * definitions; restoration itself can fail and cannot guarantee an unchanged
 * transport. Inbound asynchronous processing decides whether synchronous work
 * is needed, while queued work retains the selected processor and receive tick
 * rather than reconstructing their identities later.
 */
@Slf4j
@Getter
public final class ProtocolSession {
    /**
     * {@code protocol} identifies the initialized protocol selected for this
     * connection. {@code getProtocol()} exposes the same instance, including
     * its shared encoder and cached payload bundle.
     */
    private final Protocol protocol;
    /**
     * {@code session} holds the transport connection supplied at construction.
     * {@code getSession()} exposes that same mutable object; codec lifecycle
     * synchronization covers calls through this wrapper.
     */
    private final BedrockServerSession session;
    /**
     * {@code processorHolder} owns this connection's processor instances and
     * client state. {@code getProcessorHolder()} exposes the same holder used
     * by receive dispatch and state-transition methods.
     */
    private final PacketProcessorHolder processorHolder;
    /**
     * {@code codecInstalled} records successful initial codec installation.
     * Synchronized {@code installCodec} and {@code switchCodec} operations read
     * or update it; failed installation leaves it {@code false} even if
     * rollback also fails.
     */
    @Getter(AccessLevel.NONE)
    private boolean codecInstalled;

    /**
     * Creates processors for a nonnull, initialized protocol and a nonnull
     * transport session. The holder must accept the transition to
     * {@code CONNECTED}; construction rejects an uninitialized protocol or an
     * unsuccessful initial state transition.
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
     * Installs the selected codec and definition registries once successfully.
     * The connection must already have a bootstrap codec, and a previous
     * successful installation causes another call to throw before touching the
     * transport.
     *
     * <p> The session monitor serializes this success check with codec
     * application and recording completion. Runtime failures leave the
     * completion flag unset and attempt to restore the previous transport
     * state, allowing a later retry. Restoration can also fail, so an unset
     * flag does not itself prove that the bootstrap codec and definitions
     * remain installed.
     */
    public synchronized void installCodec() {
        if (codecInstalled) {
            throw new IllegalStateException("Protocol codec is already installed");
        }

        applyCodec(protocol.getCodec());
        codecInstalled = true;
    }

    /**
     * Replaces the transport codec after checking that the client state is
     * {@code CONNECTED}. Installation must have succeeded and the replacement
     * must use the negotiated wire version; the same codec instance is a no-op.
     * {@code Protocol} processors and definitions remain selected from the
     * original protocol, and state transitions use the holder's separate
     * synchronization.
     */
    public synchronized void switchCodec(BedrockCodec codec) {
        codec = Objects.requireNonNull(codec, "codec");
        if (!codecInstalled) {
            throw new IllegalStateException("Protocol codec must be installed before switching");
        }
        if (codec.getProtocolVersion() != protocol.getProtocolVersion()) {
            throw new IllegalArgumentException("Replacement codec must use the negotiated protocol version");
        }
        if (processorHolder.getClientState() != ClientState.CONNECTED) {
            throw new IllegalStateException("Protocol codec can only change while the client is connected");
        }
        if (session.getCodec() == codec) {
            return;
        }
        applyCodec(codec);
    }

    /**
     * Replaces the transport codec and installs the negotiated protocol's
     * definition registries. A bootstrap codec must already be present so its
     * codec and helper definitions can be captured before mutation.
     *
     * <p> Changing the codec may replace the transport helper, so definitions
     * are installed on the helper obtained afterward. A
     * {@code RuntimeException} triggers restoration through another codec
     * replacement and a newly obtained helper. If restoration also throws, that
     * exception is suppressed on the original failure; callers must not assume
     * that the transport has been restored.
     */
    private void applyCodec(BedrockCodec codec) {
        var previousCodec = session.getCodec();
        if (previousCodec == null) {
            throw new IllegalStateException("Session must have a bootstrap codec before protocol installation");
        }
        var previousHelper = session.getPeer().getCodecHelper();
        var previousItemDefinitions = previousHelper.getItemDefinitions();
        var previousBlockDefinitions = previousHelper.getBlockDefinitions();
        // Codec replacement can create a different helper; install both
        // registries on that helper to preserve negotiated definition
        // identities.
        try {
            session.setCodec(codec);
            var helper = session.getPeer().getCodecHelper();
            helper.setItemDefinitions(protocol.getItemDefinitionRegistry());
            helper.setBlockDefinitions(protocol.getBlockDefinitionRegistry());
        } catch (RuntimeException exception) {
            // Restore the codec before its definitions because restoring the
            // codec can replace the helper a second time.
            try {
                session.setCodec(previousCodec);
                var restoredHelper = session.getPeer().getCodecHelper();
                restoredHelper.setItemDefinitions(previousItemDefinitions);
                restoredHelper.setBlockDefinitions(previousBlockDefinitions);
            } catch (RuntimeException rollbackException) {
                // Preserve the original installation failure while exposing
                // incomplete restoration to the caller through its suppressed
                // exception.
                exception.addSuppressed(rollbackException);
            }
            throw exception;
        }
    }

    /**
     * Applies receive listeners and dispatches the resulting packet for this
     * session. Disabled packet handling, cancellation, {@code null}
     * replacements, and absent processors consume the packet without further
     * processing.
     *
     * <p> The selected processor receives its asynchronous phase immediately.
     * If it requests further handling, the packet, receive tick, and processor
     * travel together to the player's world queue; without a world, the
     * synchronous phase runs directly on the calling thread. The method always
     * returns {@code HANDLED} after normal completion, while listener or
     * processor exceptions propagate.
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

        // Listeners may replace packet identity or type; dispatch must use
        // their final value rather than the original input.
        packet = event.getPacket();
        if (packet == null) {
            log.warn("Ignored a null packet produced by PacketReceiveEvent for {}", protocol);
            return PacketSignal.HANDLED;
        }
        // A packet can lack a handler in the current state; consume it without
        // invoking a processor registered for a different phase.
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

        // Preserve the chosen processor and receive tick across dispatch;
        // worldless connections cannot defer work to a world queue.
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
     * Runs the synchronous phase selected from the current client state. Unlike
     * receive-time queued dispatch, this method resolves the processor again;
     * an absent registration is logged and ignored, and {@code receiveTime} is
     * the original receive tick.
     */
    public void handleSync(AllayPlayer player, BedrockPacket packet, long receiveTime) {
        // A packet can lack a handler in the current state; consume it without
        // invoking a processor registered for a different phase.
        var processor = processorHolder.getProcessor(packet);
        if (processor == null) {
            log.debug("Received a sync packet without a processor: {}, client state: {}", packet, getClientState());
            return;
        }
        processor.handleSync(player, packet, receiveTime);
    }

    /**
     * Applies send listeners and queues the accepted packet for this
     * connection. Cancellation, disabled state, disconnection, unsupported
     * packet classes, and serverbound replacements prevent transmission;
     * listeners may replace the supplied packet.
     */
    public void send(AllayPlayer player, BedrockPacket packet) {
        sendPacket(player, packet, false);
    }

    /**
     * Applies send listeners and immediately submits the accepted packet to the
     * transport. The same rejection rules as queued sending apply, including
     * validation of a listener replacement against the negotiated protocol.
     */
    public void sendImmediately(AllayPlayer player, BedrockPacket packet) {
        sendPacket(player, packet, true);
    }

    /**
     * Applies send listeners and immediately submits the accepted packet,
     * returning that exact object. A {@code null} result means sending was
     * cancelled or rejected; a nonnull result confirms submission, not delivery
     * to the remote client.
     */
    public BedrockPacket sendImmediatelyAndGet(AllayPlayer player, BedrockPacket packet) {
        return sendPacket(player, packet, true);
    }

    /**
     * Queues collection entries in iteration order, applying send listeners
     * separately to each packet. Rejection of one entry does not prevent later
     * entries, while an exception terminates the iteration and leaves prior
     * submissions intact.
     */
    public void send(AllayPlayer player, Collection<? extends BedrockPacket> packets) {
        for (var packet : packets) {
            sendPacket(player, packet, false);
        }
    }

    /**
     * Immediately submits collection entries in iteration order with separate
     * send events. Rejected entries are skipped individually; an exception
     * stops processing subsequent entries without undoing packets already
     * submitted to the transport.
     */
    public void sendImmediately(AllayPlayer player, Collection<? extends BedrockPacket> packets) {
        for (var packet : packets) {
            sendPacket(player, packet, true);
        }
    }

    /**
     * Validates and submits the packet left by send listeners, or returns
     * {@code null} when rejected. State and transport connectivity are checked
     * after the event because listeners can disconnect the player; registration
     * and recipient checks use the negotiated protocol codec.
     */
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
        // A listener replacement may be unsupported or serverbound, so validate
        // the resulting object before choosing either transport send mode.
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
     * Returns the packet-processing state currently held by this connection.
     */
    public ClientState getClientState() {
        return processorHolder.getClientState();
    }

    /**
     * Returns the state preceding the most recent successful holder transition.
     * The {@code ProtocolSession} constructor transitions its holder from
     * {@code DISCONNECTED} to {@code CONNECTED}, so a normally created session
     * begins with {@code DISCONNECTED} as its previous state.
     */
    public ClientState getLastClientState() {
        return processorHolder.getLastClientState();
    }

    /**
     * Requests a state transition and logs a rejected request. Repeating the
     * current state or violating the target state predecessor rule returns
     * {@code false}; success records the former state before publishing the new
     * state.
     */
    public boolean setClientState(ClientState state) {
        return processorHolder.setClientState(state);
    }

    /**
     * Requests a transition with optional warnings for rejected requests. The
     * holder serializes predecessor validation and updates; repeated states and
     * invalid predecessors return {@code false}, while successful transitions
     * retain the previous state.
     */
    public boolean setClientState(ClientState state, boolean warnIfFailed) {
        return processorHolder.setClientState(state, warnIfFailed);
    }
}
