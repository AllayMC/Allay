package org.allaymc.server.network.protocol;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.server.Server;
import org.allaymc.server.eventbus.event.network.PacketReceiveEvent;
import org.allaymc.server.eventbus.event.network.PacketSendEvent;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.world.AllayWorld;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.BedrockPeer;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v818.Bedrock_v818;
import org.cloudburstmc.protocol.bedrock.codec.v819.Bedrock_v819;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168_hotfix4;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.definition.DefinitionRegistry;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * {@code ProtocolSessionTest} checks event replacement, transport validation,
 * and codec lifecycle behavior for one protocol session.
 */
@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class ProtocolSessionTest {

    /**
     * Mock negotiated protocol identity.
     */
    @Mock
    private Protocol protocol;
    /**
     * Mock packet transport connection.
     */
    @Mock
    private BedrockServerSession bedrockSession;
    /**
     * Mock player sending or receiving.
     */
    @Mock
    private AllayPlayer player;

    /**
     * {@code protocolSession} holds the session created for each case. Tests
     * may replace it with a holder containing explicit processors before
     * exercising inbound dispatch.
     */
    private ProtocolSession protocolSession;

    /**
     * Creates a fresh connected session backed by an empty frozen processor
     * registry. {@code Protocol} identity and readiness are stubbed before
     * construction, while codec stubs remain lenient for tests that only
     * exercise dispatch.
     */
    @BeforeEach
    void setUp() {
        var registry = new PacketProcessorRegistry();
        registry.freeze();
        var holder = new PacketProcessorHolder(registry);

        when(protocol.isInitialized()).thenReturn(true);
        when(protocol.createProcessorHolder()).thenReturn(holder);
        lenient().when(protocol.getCodec()).thenReturn(Bedrock_v818.CODEC);
        lenient().when(protocol.getProtocolVersion()).thenReturn(Bedrock_v818.CODEC.getProtocolVersion());
        protocolSession = new ProtocolSession(protocol, bedrockSession);
    }

    /**
     * Sends one registered text packet through queued and immediate paths. Both
     * transport calls must receive the original object, and immediate
     * submission returns that same packet.
     */
    @Test
    void registeredOriginalPacketIsAcceptedForBothSendModes() {
        var packet = new TextPacket();
        when(bedrockSession.isConnected()).thenReturn(true);

        protocolSession.send(player, packet);
        assertSame(packet, protocolSession.sendImmediatelyAndGet(player, packet));

        InOrder order = inOrder(bedrockSession);
        order.verify(bedrockSession).sendPacket(packet);
        order.verify(bedrockSession).sendPacketImmediately(packet);
    }

    /**
     * Cancels every send event while exercising both send modes and the
     * returning overload. No packet may reach either transport method, and the
     * returning overload must report rejection with {@code null}.
     */
    @Test
    void cancellationBlocksQueuedAndImmediateSends() {
        var packet = new TextPacket();
        Consumer<PacketSendEvent> listener = PacketSendEvent::cancel;

        withPacketSendListener(listener, () -> {
            protocolSession.send(player, packet);
            protocolSession.sendImmediately(player, packet);
            assertNull(protocolSession.sendImmediatelyAndGet(player, packet));
        });

        verify(bedrockSession, never()).sendPacket(any());
        verify(bedrockSession, never()).sendPacketImmediately(any());
    }

    /**
     * Replaces two outbound packets with different registered packet types
     * through an event listener. Queued and immediate transport calls must
     * receive their respective replacements, and the immediate result must
     * preserve replacement identity.
     */
    @Test
    void replacementIsSentAndReturnedForBothSendModes() {
        var original = new TextPacket();
        var queuedReplacement = new AnimatePacket();
        var immediateReplacement = new PlayStatusPacket();
        Consumer<PacketSendEvent> listener = event -> event.setPacket(
                event.getPacket() == original ? queuedReplacement : immediateReplacement
        );
        when(bedrockSession.isConnected()).thenReturn(true);

        withPacketSendListener(listener, () -> {
            protocolSession.send(player, original);
            assertSame(
                    immediateReplacement,
                    protocolSession.sendImmediatelyAndGet(player, queuedReplacement)
            );
        });

        InOrder order = inOrder(bedrockSession);
        order.verify(bedrockSession).sendPacket(queuedReplacement);
        order.verify(bedrockSession).sendPacketImmediately(immediateReplacement);
    }

    /**
     * Replaces a valid text packet with a packet absent from the selected
     * codec. Both send modes must reject that replacement before transport
     * submission, including the immediate-returning overload.
     */
    @Test
    void eventReplacementUnregisteredInTargetCodecIsRejected() {
        var original = new TextPacket();
        var unsupportedReplacement = new VoxelShapesPacket();
        Consumer<PacketSendEvent> listener = event -> event.setPacket(unsupportedReplacement);
        when(bedrockSession.isConnected()).thenReturn(true);

        withPacketSendListener(listener, () -> {
            protocolSession.send(player, original);
            assertNull(protocolSession.sendImmediatelyAndGet(player, original));
        });

        verify(bedrockSession, never()).sendPacket(any());
        verify(bedrockSession, never()).sendPacketImmediately(any());
    }

    /**
     * Submits an unsupported packet without event replacement to an otherwise
     * connected transport. Queued and immediate sending must both omit it, and
     * immediate submission must return {@code null}.
     */
    @Test
    void unregisteredOriginalPacketIsRejected() {
        var unsupported = new VoxelShapesPacket();
        when(bedrockSession.isConnected()).thenReturn(true);

        protocolSession.send(player, unsupported);
        assertNull(protocolSession.sendImmediatelyAndGet(player, unsupported));

        verify(bedrockSession, never()).sendPacket(any());
        verify(bedrockSession, never()).sendPacketImmediately(any());
    }

    /**
     * Uses a login packet registered exclusively for server receipt in the
     * codec. Outbound validation must reject it in both send modes even though
     * the packet class is registered.
     */
    @Test
    void serverboundPacketIsRejectedByBothSendModes() {
        var serverbound = new LoginPacket();
        assertEquals(
                PacketRecipient.SERVER,
                Bedrock_v818.CODEC.getPacketDefinition(LoginPacket.class).recipient()
        );
        when(bedrockSession.isConnected()).thenReturn(true);

        protocolSession.send(player, serverbound);
        assertNull(protocolSession.sendImmediatelyAndGet(player, serverbound));

        verify(bedrockSession, never()).sendPacket(any());
        verify(bedrockSession, never()).sendPacketImmediately(any());
    }

    /**
     * Sends collections containing cancelled, replaced, unsupported, and
     * unchanged packets in both modes. Accepted entries must retain their
     * iteration order while each rejected entry is omitted independently.
     */
    @Test
    void collectionsApplyCancellationAndReplacementToEachPacketIndependently() {
        var cancelled = new LoginPacket();
        var replaced = new TextPacket();
        var replacement = new PlayStatusPacket();
        var unchanged = new AnimatePacket();
        var unsupported = new VoxelShapesPacket();
        Consumer<PacketSendEvent> listener = event -> {
            if (event.getPacket() == cancelled) {
                event.cancel();
            } else if (event.getPacket() == replaced) {
                event.setPacket(replacement);
            }
        };
        when(bedrockSession.isConnected()).thenReturn(true);

        withPacketSendListener(listener, () -> {
            protocolSession.send(player, List.of(cancelled, replaced, unsupported, unchanged));
            protocolSession.sendImmediately(player, List.of(cancelled, replaced, unsupported, unchanged));
        });

        InOrder order = inOrder(bedrockSession);
        order.verify(bedrockSession).sendPacket(replacement);
        order.verify(bedrockSession).sendPacket(unchanged);
        order.verify(bedrockSession).sendPacketImmediately(replacement);
        order.verify(bedrockSession).sendPacketImmediately(unchanged);
        verify(bedrockSession, never()).sendPacket(cancelled);
        verify(bedrockSession, never()).sendPacketImmediately(cancelled);
        verify(bedrockSession, never()).sendPacket(unsupported);
        verify(bedrockSession, never()).sendPacketImmediately(unsupported);
    }

    /**
     * Disconnects the protocol session inside the send event callback.
     * Immediate sending must recheck the state after listeners return and avoid
     * submitting the packet to the transport.
     */
    @Test
    void disconnectDuringSendEventPreventsPacketFromReachingTransport() {
        var packet = new TextPacket();
        Consumer<PacketSendEvent> listener = event ->
                protocolSession.setClientState(ClientState.DISCONNECTED, false);

        withPacketSendListener(listener, () -> protocolSession.sendImmediately(player, packet));

        verify(bedrockSession, never()).sendPacketImmediately(any(BedrockPacket.class));
    }

    /**
     * Replaces an inbound login packet with {@code null} through a temporary
     * receive listener. Receiving must consume the event result without
     * dispatch failure, and the listener is removed afterward.
     */
    @Test
    void nullReceiveEventReplacementIsIgnored() {
        Consumer<PacketReceiveEvent> listener = event -> event.setPacket(null);

        // Bound this registration to the action so failures cannot leave a
        // listener altering later tests on the shared server bus.
        var eventBus = Server.getInstance().getEventBus();
        eventBus.registerListenerFor(PacketReceiveEvent.class, listener);
        try {
            assertSame(PacketSignal.HANDLED, protocolSession.receive(player, new LoginPacket()));
        } finally {
            eventBus.unregisterListenerFor(PacketReceiveEvent.class, listener);
        }
    }

    /**
     * Cancels an inbound packet that has a registered processor. Receiving must
     * return {@code HANDLED} without invoking either the asynchronous or
     * synchronous phase of that processor.
     */
    @Test
    void receiveCancellationSkipsProcessor() {
        var processor = processor(PacketSignal.HANDLED);
        protocolSession = sessionWithProcessors(Map.of(BedrockPacketType.LOGIN, processor));
        Consumer<PacketReceiveEvent> listener = PacketReceiveEvent::cancel;

        withPacketReceiveListener(listener, () ->
                assertSame(PacketSignal.HANDLED, protocolSession.receive(player, new LoginPacket()))
        );

        verify(processor, never()).handleAsync(any(), any(), anyLong());
        verify(processor, never()).handleSync(any(), any(), anyLong());
    }

    /**
     * Replaces a login packet with a text packet while both types have
     * processors. Dispatch must select the text processor and pass it the
     * replacement object, leaving the original processor unused.
     */
    @Test
    void receiveReplacementUsesReplacementProcessor() {
        var originalProcessor = processor(PacketSignal.HANDLED);
        var replacementProcessor = processor(PacketSignal.HANDLED);
        protocolSession = sessionWithProcessors(Map.of(
                BedrockPacketType.LOGIN, originalProcessor,
                BedrockPacketType.TEXT, replacementProcessor
        ));
        var replacement = new TextPacket();
        Consumer<PacketReceiveEvent> listener = event -> event.setPacket(replacement);

        withPacketReceiveListener(listener, () ->
                assertSame(PacketSignal.HANDLED, protocolSession.receive(player, new LoginPacket()))
        );

        verify(originalProcessor, never()).handleAsync(any(), any(), anyLong());
        verify(replacementProcessor).handleAsync(eq(player), same(replacement), anyLong());
    }

    /**
     * Registers a processor whose asynchronous phase consumes a login packet.
     * Receiving must invoke that phase once and avoid the synchronous phase
     * after the {@code HANDLED} result.
     */
    @Test
    void asyncHandledPacketDoesNotEnterSyncPath() {
        var processor = processor(PacketSignal.HANDLED);
        protocolSession = sessionWithProcessors(Map.of(BedrockPacketType.LOGIN, processor));

        assertSame(PacketSignal.HANDLED, protocolSession.receive(player, new LoginPacket()));

        verify(processor).handleAsync(eq(player), any(LoginPacket.class), anyLong());
        verify(processor, never()).handleSync(any(), any(), anyLong());
    }

    /**
     * Replaces a login packet with text whose asynchronous processor requests
     * synchronous work. The world queue must receive the replacement packet,
     * its selected processor, and the entity receive tick together.
     */
    @Test
    void unhandledReplacementQueuesReplacementWithItsProcessor() {
        var originalProcessor = processor(PacketSignal.HANDLED);
        var replacementProcessor = processor(PacketSignal.UNHANDLED);
        protocolSession = sessionWithProcessors(Map.of(
                BedrockPacketType.LOGIN, originalProcessor,
                BedrockPacketType.TEXT, replacementProcessor
        ));
        var entity = mock(EntityPlayer.class);
        var world = mock(AllayWorld.class);
        var replacement = new TextPacket();
        when(player.getControlledEntity()).thenReturn(entity);
        when(entity.getWorld()).thenReturn(world);
        when(entity.getTick()).thenReturn(123L);
        Consumer<PacketReceiveEvent> listener = event -> event.setPacket(replacement);

        withPacketReceiveListener(listener, () ->
                assertSame(PacketSignal.HANDLED, protocolSession.receive(player, new LoginPacket()))
        );

        verify(originalProcessor, never()).handleAsync(any(), any(), anyLong());
        verify(replacementProcessor).handleAsync(player, replacement, 123L);
        verify(world).addSyncPacketToQueue(player, replacement, 123L, replacementProcessor);
        verify(replacementProcessor, never()).handleSync(any(), any(), anyLong());
    }

    /**
     * Installs a negotiated codec with prevalidated item and block registries
     * on a replacement helper. Repeating installation must fail, and the first
     * installation must set the codec before either registry.
     */
    @Test
    @SuppressWarnings("unchecked")
    void installsPrevalidatedDefinitionsExactlyOnce() {
        var peer = mock(BedrockPeer.class);
        var previousHelper = mock(BedrockCodecHelper.class);
        var installedHelper = mock(BedrockCodecHelper.class);
        var previousItems = (DefinitionRegistry<ItemDefinition>) mock(DefinitionRegistry.class);
        var previousBlocks = (DefinitionRegistry<BlockDefinition>) mock(DefinitionRegistry.class);
        var selectedItems = (DefinitionRegistry<ItemDefinition>) mock(DefinitionRegistry.class);
        var selectedBlocks = (DefinitionRegistry<BlockDefinition>) mock(DefinitionRegistry.class);

        when(bedrockSession.getCodec()).thenReturn(Bedrock_v819.CODEC);
        when(bedrockSession.getPeer()).thenReturn(peer);
        when(peer.getCodecHelper()).thenReturn(previousHelper, installedHelper);
        when(previousHelper.getItemDefinitions()).thenReturn(previousItems);
        when(previousHelper.getBlockDefinitions()).thenReturn(previousBlocks);
        when(protocol.getItemDefinitionRegistry()).thenReturn(selectedItems);
        when(protocol.getBlockDefinitionRegistry()).thenReturn(selectedBlocks);

        protocolSession.installCodec();
        assertThrows(IllegalStateException.class, protocolSession::installCodec);

        var order = inOrder(bedrockSession, installedHelper);
        order.verify(bedrockSession).setCodec(Bedrock_v818.CODEC);
        order.verify(installedHelper).setItemDefinitions(selectedItems);
        order.verify(installedHelper).setBlockDefinitions(selectedBlocks);
        verify(bedrockSession).setCodec(Bedrock_v818.CODEC);
    }

    /**
     * Switches a connected session between codecs sharing the negotiated wire
     * version. {@code Protocol} identity, processor holder, and client state
     * must remain unchanged while the replacement helper receives the selected
     * definitions.
     */
    @Test
    @SuppressWarnings("unchecked")
    void switchesTransportCodecWithoutReplacingProtocolStateBeforeLoginCompletes() {
        var peer = mock(BedrockPeer.class);
        var bootstrapHelper = mock(BedrockCodecHelper.class);
        var hotfixHelper = mock(BedrockCodecHelper.class);
        var baseHelper = mock(BedrockCodecHelper.class);
        var bootstrapItems = (DefinitionRegistry<ItemDefinition>) mock(DefinitionRegistry.class);
        var bootstrapBlocks = (DefinitionRegistry<BlockDefinition>) mock(DefinitionRegistry.class);
        var selectedItems = (DefinitionRegistry<ItemDefinition>) mock(DefinitionRegistry.class);
        var selectedBlocks = (DefinitionRegistry<BlockDefinition>) mock(DefinitionRegistry.class);

        when(protocol.getCodec()).thenReturn(Bedrock_v2168_hotfix4.CODEC);
        when(protocol.getProtocolVersion()).thenReturn(Bedrock_v2168.CODEC.getProtocolVersion());
        when(protocol.getItemDefinitionRegistry()).thenReturn(selectedItems);
        when(protocol.getBlockDefinitionRegistry()).thenReturn(selectedBlocks);
        when(bedrockSession.getCodec()).thenReturn(
                Bedrock_v819.CODEC,
                Bedrock_v2168_hotfix4.CODEC,
                Bedrock_v2168_hotfix4.CODEC
        );
        when(bedrockSession.getPeer()).thenReturn(peer);
        // The helper sequence models installation, the pre-switch snapshot, and
        // replacement so assertions distinguish retained protocol data from
        // transport helpers.
        when(peer.getCodecHelper()).thenReturn(bootstrapHelper, hotfixHelper, hotfixHelper, baseHelper);
        when(bootstrapHelper.getItemDefinitions()).thenReturn(bootstrapItems);
        when(bootstrapHelper.getBlockDefinitions()).thenReturn(bootstrapBlocks);
        when(hotfixHelper.getItemDefinitions()).thenReturn(selectedItems);
        when(hotfixHelper.getBlockDefinitions()).thenReturn(selectedBlocks);

        var originalHolder = protocolSession.getProcessorHolder();
        protocolSession.installCodec();
        protocolSession.switchCodec(Bedrock_v2168.CODEC);

        assertSame(protocol, protocolSession.getProtocol());
        assertSame(originalHolder, protocolSession.getProcessorHolder());
        assertEquals(ClientState.CONNECTED, protocolSession.getClientState());

        var order = inOrder(bedrockSession, hotfixHelper, baseHelper);
        order.verify(bedrockSession).setCodec(Bedrock_v2168_hotfix4.CODEC);
        order.verify(hotfixHelper).setItemDefinitions(selectedItems);
        order.verify(hotfixHelper).setBlockDefinitions(selectedBlocks);
        order.verify(bedrockSession).setCodec(Bedrock_v2168.CODEC);
        order.verify(baseHelper).setItemDefinitions(selectedItems);
        order.verify(baseHelper).setBlockDefinitions(selectedBlocks);
    }

    /**
     * Advances a successfully installed session into the logged-in state before
     * requesting another codec. The switch must fail without installing the
     * replacement or changing the negotiated protocol.
     */
    @Test
    @SuppressWarnings("unchecked")
    void refusesTransportCodecSwitchAfterLoginStateHasAdvanced() {
        var peer = mock(BedrockPeer.class);
        var bootstrapHelper = mock(BedrockCodecHelper.class);
        var installedHelper = mock(BedrockCodecHelper.class);
        var items = (DefinitionRegistry<ItemDefinition>) mock(DefinitionRegistry.class);
        var blocks = (DefinitionRegistry<BlockDefinition>) mock(DefinitionRegistry.class);

        when(protocol.getCodec()).thenReturn(Bedrock_v2168_hotfix4.CODEC);
        when(protocol.getProtocolVersion()).thenReturn(Bedrock_v2168.CODEC.getProtocolVersion());
        when(bedrockSession.getCodec()).thenReturn(Bedrock_v819.CODEC);
        when(bedrockSession.getPeer()).thenReturn(peer);
        when(peer.getCodecHelper()).thenReturn(bootstrapHelper, installedHelper);
        when(bootstrapHelper.getItemDefinitions()).thenReturn(items);
        when(bootstrapHelper.getBlockDefinitions()).thenReturn(blocks);
        when(protocol.getItemDefinitionRegistry()).thenReturn(items);
        when(protocol.getBlockDefinitionRegistry()).thenReturn(blocks);

        protocolSession.installCodec();
        assertTrue(protocolSession.setClientState(ClientState.LOGGED_IN));

        assertThrows(IllegalStateException.class, () -> protocolSession.switchCodec(Bedrock_v2168.CODEC));
        assertSame(protocol, protocolSession.getProtocol());
        verify(bedrockSession, never()).setCodec(Bedrock_v2168.CODEC);
    }

    /**
     * Attempts installation when the transport has no bootstrap codec. The
     * session must reject the request before changing the codec or obtaining
     * the transport peer.
     */
    @Test
    void refusesInstallationWithoutBootstrapCodecBeforeChangingSession() {
        assertThrows(IllegalStateException.class, protocolSession::installCodec);

        verify(bedrockSession, never()).setCodec(any());
        verify(bedrockSession, never()).getPeer();
    }

    /**
     * Makes block-definition installation throw after the new codec and item
     * definitions were applied. The session must restore the previous codec and
     * definitions in order before propagating the installation failure.
     */
    @Test
    @SuppressWarnings("unchecked")
    void definitionFailureRestoresPreviousCodecAndDefinitions() {
        var peer = mock(BedrockPeer.class);
        var previousHelper = mock(BedrockCodecHelper.class);
        var installedHelper = mock(BedrockCodecHelper.class);
        var restoredHelper = mock(BedrockCodecHelper.class);
        var previousItems = (DefinitionRegistry<ItemDefinition>) mock(DefinitionRegistry.class);
        var previousBlocks = (DefinitionRegistry<BlockDefinition>) mock(DefinitionRegistry.class);
        var selectedItems = (DefinitionRegistry<ItemDefinition>) mock(DefinitionRegistry.class);
        var selectedBlocks = (DefinitionRegistry<BlockDefinition>) mock(DefinitionRegistry.class);

        when(bedrockSession.getCodec()).thenReturn(Bedrock_v819.CODEC);
        when(bedrockSession.getPeer()).thenReturn(peer);
        // Return a distinct helper for each codec replacement so rollback must
        // restore definitions on the replacement helper, not a stale reference.
        when(peer.getCodecHelper()).thenReturn(previousHelper, installedHelper, restoredHelper);
        when(previousHelper.getItemDefinitions()).thenReturn(previousItems);
        when(previousHelper.getBlockDefinitions()).thenReturn(previousBlocks);
        when(protocol.getItemDefinitionRegistry()).thenReturn(selectedItems);
        when(protocol.getBlockDefinitionRegistry()).thenReturn(selectedBlocks);
        doThrow(new IllegalStateException("definition failure"))
                .when(installedHelper).setBlockDefinitions(selectedBlocks);

        assertThrows(IllegalStateException.class, protocolSession::installCodec);

        var order = inOrder(bedrockSession, installedHelper, restoredHelper);
        order.verify(bedrockSession).setCodec(Bedrock_v818.CODEC);
        order.verify(installedHelper).setItemDefinitions(selectedItems);
        order.verify(installedHelper).setBlockDefinitions(selectedBlocks);
        order.verify(bedrockSession).setCodec(Bedrock_v819.CODEC);
        order.verify(restoredHelper).setItemDefinitions(previousItems);
        order.verify(restoredHelper).setBlockDefinitions(previousBlocks);
    }

    /**
     * Runs an action with a temporary listener on the shared send-event bus.
     * The listener is removed in a {@code finally} block, including when the
     * action throws, so later tests do not inherit its behavior.
     */
    private static void withPacketSendListener(Consumer<PacketSendEvent> listener, Runnable action) {
        // Bound this registration to the action so failures cannot leave a
        // listener altering later tests on the shared server bus.
        var eventBus = Server.getInstance().getEventBus();
        eventBus.registerListenerFor(PacketSendEvent.class, listener);
        try {
            action.run();
        } finally {
            eventBus.unregisterListenerFor(PacketSendEvent.class, listener);
        }
    }

    /**
     * Runs an action with a temporary listener on the shared receive-event bus.
     * A {@code finally} block removes the listener even after assertion or
     * dispatch failure, keeping the registration lifetime bounded to this
     * action.
     */
    private static void withPacketReceiveListener(Consumer<PacketReceiveEvent> listener, Runnable action) {
        // Bound this registration to the action so failures cannot leave a
        // listener altering later tests on the shared server bus.
        var eventBus = Server.getInstance().getEventBus();
        eventBus.registerListenerFor(PacketReceiveEvent.class, listener);
        try {
            action.run();
        } finally {
            eventBus.unregisterListenerFor(PacketReceiveEvent.class, listener);
        }
    }

    /**
     * Creates a mock processor returning the supplied asynchronous signal for
     * any packet. The stub is lenient because cancellation cases deliberately
     * exercise paths where the processor must never be invoked.
     */
    @SuppressWarnings("unchecked")
    private PacketProcessor<BedrockPacket> processor(PacketSignal asyncResult) {
        var processor = (PacketProcessor<BedrockPacket>) mock(PacketProcessor.class);
        lenient().when(processor.handleAsync(any(), any(), anyLong())).thenReturn(asyncResult);
        return processor;
    }

    /**
     * Creates a connected session whose holder uses the supplied packet-type
     * processors. Each mock reports its registered type, and freezing occurs
     * before holder construction so dispatch uses a validated registration set.
     */
    private ProtocolSession sessionWithProcessors(
            Map<BedrockPacketType, PacketProcessor<BedrockPacket>> processors
    ) {
        var registry = new PacketProcessorRegistry();
        processors.forEach((packetType, processor) -> {
            when(processor.getPacketType()).thenReturn(packetType);
            registry.register(ClientState.CONNECTED, packetType, () -> processor);
        });
        registry.freeze();
        var holder = new PacketProcessorHolder(registry);
        when(protocol.createProcessorHolder()).thenReturn(holder);
        return new ProtocolSession(protocol, bedrockSession);
    }
}
