package org.allaymc.server.player;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.AllayNetworkInterface;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.protocol.Protocol;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.BedrockPeer;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * {@code AllayPlayerProtocolLifecycleTest} verifies send-boundary empty results and
 * protocol installation ordering against disconnection callbacks.
 */
@ExtendWith(AllayTestExtension.class)
class AllayPlayerProtocolLifecycleTest {

    /**
     * Supplies {@code null} packets, {@code null} collections, and empty collections to both send
     * paths. Each call must return without requiring a negotiated protocol or
     * passing a nonexistent packet to transport handling.
     */
    @Test
    void emptyEncoderResultsAreIgnoredAtTheSendBoundary() {
        var player = new TestPlayer(mock(BedrockServerSession.class), mock(AllayNetworkInterface.class));

        player.sendPacket(null);
        player.sendPackets(null);
        player.sendPackets(List.of());
        player.sendPacketImmediately(null);
        player.sendPacketsImmediately(null);
        player.sendPacketsImmediately(List.of());
    }

    /**
     * Pauses protocol installation at the transport connection check while a
     * disconnect callback competes. Releasing installation must leave the installed
     * session disconnected and invoke player cleanup once after the lifecycle lock
     * is released.
     */
    @Test
    void disconnectWaitsForProtocolInstallAndTransitionsInstalledSession() throws Exception {
        var handler = new AtomicReference<BedrockPacketHandler>();
        var bedrockSession = sessionCapturingHandler(handler);
        var player = new TestPlayer(bedrockSession, mock(AllayNetworkInterface.class));
        var protocol = protocolForInstall(bedrockSession);
        var installReachedConnectionCheck = new CountDownLatch(1);
        var allowInstallToContinue = new CountDownLatch(1);
        when(bedrockSession.isConnected()).thenAnswer(invocation -> {
            installReachedConnectionCheck.countDown();
            assertTrue(allowInstallToContinue.await(5, TimeUnit.SECONDS));
            return true;
        });

        // Run installation and disconnection on separate workers while latches hold installation
        // inside the lifecycle lock, making the tested ordering deterministic.
        try (var executor = Executors.newFixedThreadPool(2)) {
            var install = executor.submit(() -> player.installProtocol(protocol));
            assertTrue(installReachedConnectionCheck.await(5, TimeUnit.SECONDS));
            var disconnect = executor.submit(() -> handler.get().onDisconnect("closed"));

            // Release installation after scheduling the competing disconnect so both futures can
            // finish and the final state can verify serialized lifecycle handling.
            allowInstallToContinue.countDown();

            assertTrue(install.get(5, TimeUnit.SECONDS));
            disconnect.get(5, TimeUnit.SECONDS);
        }

        assertNotNull(player.getProtocolSession());
        assertEquals(ClientState.DISCONNECTED, player.getClientState());
        assertEquals(1, player.disconnectCallbacks.get());
    }

    /**
     * Delivers a disconnect callback before attempting protocol installation.
     * Installation must return failure without publishing a protocol session, while
     * the client state remains disconnected and cleanup runs once.
     */
    @Test
    void protocolCannotBeInstalledAfterDisconnectCallbackWins() {
        var handler = new AtomicReference<BedrockPacketHandler>();
        var bedrockSession = sessionCapturingHandler(handler);
        when(bedrockSession.isConnected()).thenReturn(true);
        var player = new TestPlayer(bedrockSession, mock(AllayNetworkInterface.class));

        handler.get().onDisconnect("closed");

        assertFalse(player.installProtocol(mock(Protocol.class)));
        assertNull(player.getProtocolSession());
        assertEquals(ClientState.DISCONNECTED, player.getClientState());
        assertEquals(1, player.disconnectCallbacks.get());
    }

    /**
     * Creates a mocked session that captures its installed packet handler reference.
     * Tests invoke that handler directly to model transport callbacks without
     * starting a network connection or peer pipeline.
     */
    private static BedrockServerSession sessionCapturingHandler(AtomicReference<BedrockPacketHandler> handler) {
        var session = mock(BedrockServerSession.class);
        doAnswer(invocation -> {
            handler.set(invocation.getArgument(0));
            return null;
        }).when(session).setPacketHandler(any(BedrockPacketHandler.class));
        return session;
    }

    /**
     * Creates an initialized mocked protocol with empty definitions and a frozen
     * processor registry. The transport codec, peer, and helper stubs allow real
     * installation to proceed without a running network pipeline.
     */
    private static Protocol protocolForInstall(BedrockServerSession session) {
        var registry = new PacketProcessorRegistry();
        registry.freeze();
        PacketProcessorHolder holder = new PacketProcessorHolder(registry);
        var protocol = mock(Protocol.class);
        var data = mock(ProtocolData.class);
        var codec = mock(BedrockCodec.class);
        var bootstrapCodec = mock(BedrockCodec.class);
        var peer = mock(BedrockPeer.class);
        var codecHelper = mock(BedrockCodecHelper.class);

        when(protocol.isInitialized()).thenReturn(true);
        when(protocol.createProcessorHolder()).thenReturn(holder);
        when(protocol.getCodec()).thenReturn(codec);
        when(protocol.getData()).thenReturn(data);
        when(data.itemDefinitions()).thenReturn(List.of());
        when(data.blockDefinitions()).thenReturn(List.of());
        when(session.getCodec()).thenReturn(bootstrapCodec);
        when(session.getPeer()).thenReturn(peer);
        when(peer.getCodecHelper()).thenReturn(codecHelper);
        return protocol;
    }

    /**
     * {@code TestPlayer} counts disconnect cleanup callbacks without running real
     * server membership cleanup. This isolates lifecycle ordering assertions from
     * unrelated player storage and dimension state.
     */
    private static final class TestPlayer extends AllayPlayer {
        /**
         * Count of observed player cleanup callbacks.
         */
        private final AtomicInteger disconnectCallbacks = new AtomicInteger();

        /**
         * Creates a player over supplied mocked transport and network dependencies.
         */
        private TestPlayer(BedrockServerSession session, AllayNetworkInterface sourceInterface) {
            super(session, sourceInterface);
        }

        /**
         * Counts the cleanup callback selected by the connection lifecycle
         * transition.
         */
        @Override
        protected void onDisconnect(String disconnectReason) {
            disconnectCallbacks.incrementAndGet();
        }
    }
}
