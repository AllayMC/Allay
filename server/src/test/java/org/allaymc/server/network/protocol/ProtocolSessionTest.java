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

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class ProtocolSessionTest {

    @Mock
    private Protocol protocol;
    @Mock
    private BedrockServerSession bedrockSession;
    @Mock
    private AllayPlayer player;

    private ProtocolSession protocolSession;

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

    @Test
    void unregisteredOriginalPacketIsRejected() {
        var unsupported = new VoxelShapesPacket();
        when(bedrockSession.isConnected()).thenReturn(true);

        protocolSession.send(player, unsupported);
        assertNull(protocolSession.sendImmediatelyAndGet(player, unsupported));

        verify(bedrockSession, never()).sendPacket(any());
        verify(bedrockSession, never()).sendPacketImmediately(any());
    }

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

    @Test
    void disconnectDuringSendEventPreventsPacketFromReachingTransport() {
        var packet = new TextPacket();
        Consumer<PacketSendEvent> listener = event ->
                protocolSession.setClientState(ClientState.DISCONNECTED, false);

        withPacketSendListener(listener, () -> protocolSession.sendImmediately(player, packet));

        verify(bedrockSession, never()).sendPacketImmediately(any(BedrockPacket.class));
    }

    @Test
    void nullReceiveEventReplacementIsIgnored() {
        Consumer<PacketReceiveEvent> listener = event -> event.setPacket(null);

        var eventBus = Server.getInstance().getEventBus();
        eventBus.registerListenerFor(PacketReceiveEvent.class, listener);
        try {
            assertSame(PacketSignal.HANDLED, protocolSession.receive(player, new LoginPacket()));
        } finally {
            eventBus.unregisterListenerFor(PacketReceiveEvent.class, listener);
        }
    }

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

    @Test
    void asyncHandledPacketDoesNotEnterSyncPath() {
        var processor = processor(PacketSignal.HANDLED);
        protocolSession = sessionWithProcessors(Map.of(BedrockPacketType.LOGIN, processor));

        assertSame(PacketSignal.HANDLED, protocolSession.receive(player, new LoginPacket()));

        verify(processor).handleAsync(eq(player), any(LoginPacket.class), anyLong());
        verify(processor, never()).handleSync(any(), any(), anyLong());
    }

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

    @Test
    void refusesInstallationWithoutBootstrapCodecBeforeChangingSession() {
        assertThrows(IllegalStateException.class, protocolSession::installCodec);

        verify(bedrockSession, never()).setCodec(any());
        verify(bedrockSession, never()).getPeer();
    }

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

    private static void withPacketSendListener(Consumer<PacketSendEvent> listener, Runnable action) {
        var eventBus = Server.getInstance().getEventBus();
        eventBus.registerListenerFor(PacketSendEvent.class, listener);
        try {
            action.run();
        } finally {
            eventBus.unregisterListenerFor(PacketSendEvent.class, listener);
        }
    }

    private static void withPacketReceiveListener(Consumer<PacketReceiveEvent> listener, Runnable action) {
        var eventBus = Server.getInstance().getEventBus();
        eventBus.registerListenerFor(PacketReceiveEvent.class, listener);
        try {
            action.run();
        } finally {
            eventBus.unregisterListenerFor(PacketReceiveEvent.class, listener);
        }
    }

    @SuppressWarnings("unchecked")
    private PacketProcessor<BedrockPacket> processor(PacketSignal asyncResult) {
        var processor = (PacketProcessor<BedrockPacket>) mock(PacketProcessor.class);
        lenient().when(processor.handleAsync(any(), any(), anyLong())).thenReturn(asyncResult);
        return processor;
    }

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
