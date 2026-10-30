package org.allaymc.server.network.processor.login;

import org.allaymc.server.AllayServer;
import org.allaymc.server.ServerSettings;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.ProtocolRegistry;
import org.allaymc.server.network.protocol.ProtocolSession;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.BedrockPeer;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.netty.codec.compression.CompressionStrategy;
import org.cloudburstmc.protocol.bedrock.netty.codec.compression.NetEaseCompression;
import org.cloudburstmc.protocol.bedrock.packet.NetworkSettingsPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayStatusPacket;
import org.cloudburstmc.protocol.bedrock.packet.RequestNetworkSettingsPacket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * {@code RequestNetworkSettingsNegotiationTest} checks exact-version negotiation
 * and compression installation after successful post-event settings transmission.
 */
@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class RequestNetworkSettingsNegotiationTest {

    /** Mock connection negotiating its protocol variant. */
    @Mock
    private AllayPlayer player;
    /** Mock transport receiving the compression strategy. */
    @Mock
    private BedrockServerSession bedrockSession;
    /** Mock protocol sender returning post-event packets. */
    @Mock
    private ProtocolSession protocolSession;

    /**
     * Models a NetEase settings send returning a replacement that advertises
     * {@code ZLIB}. The requested packet must advertise that algorithm, and raw
     * deflate installation must occur after the send.
     */
    @Test
    void netEaseAlwaysAdvertisesZlibAndInstallsRawDeflateAfterReplacementIsSent() {
        var protocol = ProtocolRegistry.getDefault().getLowest(ClientVariant.NETEASE);
        var request = new RequestNetworkSettingsPacket();
        request.setProtocolVersion(protocol.getProtocolVersion());
        var replacement = new NetworkSettingsPacket();
        replacement.setCompressionAlgorithm(PacketCompressionAlgorithm.ZLIB);
        var settings = netEaseNetworkSettings();
        var peer = mock(BedrockPeer.class);

        when(player.getSession()).thenReturn(bedrockSession);
        when(bedrockSession.getPeer()).thenReturn(peer);
        when(player.installProtocol(protocol)).thenReturn(true);
        when(player.getProtocolSession()).thenReturn(protocolSession);
        when(protocolSession.sendImmediatelyAndGet(eq(player), any(NetworkSettingsPacket.class)))
                .thenReturn(replacement);

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);
            new NetEaseProcessor().handle(player, request);
        }

        var advertised = ArgumentCaptor.forClass(NetworkSettingsPacket.class);
        var installed = ArgumentCaptor.forClass(CompressionStrategy.class);
        var order = inOrder(protocolSession, peer);
        order.verify(protocolSession).sendImmediatelyAndGet(eq(player), advertised.capture());
        order.verify(peer).setCompression(installed.capture());
        assertEquals(PacketCompressionAlgorithm.ZLIB, advertised.getValue().getCompressionAlgorithm());
        assertInstanceOf(NetEaseCompression.class, installed.getValue().getDefaultCompression());
    }

    /**
     * Models cancellation of a NetEase settings send through a {@code null}
     * returned packet. Negotiation must disconnect without accessing the peer
     * to install compression.
     */
    @Test
    void cancelledNetEaseSettingsPacketDoesNotEnableCompression() {
        var protocol = ProtocolRegistry.getDefault().getLowest(ClientVariant.NETEASE);
        var request = new RequestNetworkSettingsPacket();
        request.setProtocolVersion(protocol.getProtocolVersion());
        var settings = netEaseNetworkSettings();

        when(player.getSession()).thenReturn(bedrockSession);
        when(player.installProtocol(protocol)).thenReturn(true);
        when(player.getProtocolSession()).thenReturn(protocolSession);
        when(protocolSession.sendImmediatelyAndGet(eq(player), any(NetworkSettingsPacket.class)))
                .thenReturn(null);

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);
            new NetEaseProcessor().handle(player, request);
        }

        verify(player).disconnect(anyString());
        verify(bedrockSession, never()).getPeer();
    }

    /**
     * Returns a NetEase replacement advertising the unsupported {@code SNAPPY}
     * algorithm. Negotiation must disconnect without reaching peer access or
     * raw-deflate installation after that incompatible result.
     */
    @Test
    void nonZlibNetEaseReplacementIsRejectedWithoutEnablingCompression() {
        var protocol = ProtocolRegistry.getDefault().getLowest(ClientVariant.NETEASE);
        var request = new RequestNetworkSettingsPacket();
        request.setProtocolVersion(protocol.getProtocolVersion());
        var replacement = new NetworkSettingsPacket();
        replacement.setCompressionAlgorithm(PacketCompressionAlgorithm.SNAPPY);
        var settings = netEaseNetworkSettings();

        when(player.getSession()).thenReturn(bedrockSession);
        when(player.installProtocol(protocol)).thenReturn(true);
        when(player.getProtocolSession()).thenReturn(protocolSession);
        when(protocolSession.sendImmediatelyAndGet(eq(player), any(NetworkSettingsPacket.class)))
                .thenReturn(replacement);

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);
            new NetEaseProcessor().handle(player, request);
        }

        verify(player).disconnect(anyString());
        verify(bedrockSession, never()).getPeer();
    }

    /**
     * Returns an international settings replacement advertising {@code SNAPPY}
     * after an original {@code ZLIB} request. Compression must use the replacement
     * algorithm and be installed only after the send.
     */
    @Test
    void enablesCompressionOnlyAfterSettingsPacketHasBeenSent() {
        var protocol = ProtocolRegistry.getDefault().getLowest(ClientVariant.INTERNATIONAL);
        var request = new RequestNetworkSettingsPacket();
        request.setProtocolVersion(protocol.getProtocolVersion());
        var replacement = new NetworkSettingsPacket();
        replacement.setCompressionAlgorithm(PacketCompressionAlgorithm.SNAPPY);
        var settings = internationalNetworkSettings();

        when(player.getSession()).thenReturn(bedrockSession);
        when(player.installProtocol(protocol)).thenReturn(true);
        when(player.getProtocolSession()).thenReturn(protocolSession);
        when(protocolSession.sendImmediatelyAndGet(eq(player), any(NetworkSettingsPacket.class)))
                .thenReturn(replacement);

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);
            new InternationalProcessor().handle(player, request);
        }

        var sentPacket = ArgumentCaptor.forClass(NetworkSettingsPacket.class);
        var order = inOrder(protocolSession, bedrockSession);
        order.verify(protocolSession).sendImmediatelyAndGet(eq(player), sentPacket.capture());
        order.verify(bedrockSession).setCompression(PacketCompressionAlgorithm.SNAPPY);
        assertEquals(PacketCompressionAlgorithm.ZLIB, sentPacket.getValue().getCompressionAlgorithm());
    }

    /**
     * Models cancellation of an international settings send after protocol
     * installation succeeds. Negotiation must disconnect and never enable an
     * algorithm on the underlying transport session.
     */
    @Test
    void cancelledSettingsPacketDoesNotEnableCompression() {
        var protocol = ProtocolRegistry.getDefault().getLowest(ClientVariant.INTERNATIONAL);
        var request = new RequestNetworkSettingsPacket();
        request.setProtocolVersion(protocol.getProtocolVersion());
        var settings = internationalNetworkSettings();

        when(player.getSession()).thenReturn(bedrockSession);
        when(player.installProtocol(protocol)).thenReturn(true);
        when(player.getProtocolSession()).thenReturn(protocolSession);
        when(protocolSession.sendImmediatelyAndGet(eq(player), any(NetworkSettingsPacket.class)))
                .thenReturn(null);

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);
            new InternationalProcessor().handle(player, request);
        }

        verify(player).disconnect(anyString());
        verify(bedrockSession, never()).setCompression(any(PacketCompressionAlgorithm.class));
    }

    /**
     * Requests international wire versions just below and above the supported
     * registry range. Each request must produce its corresponding client-old or
     * server-old status without installing a protocol.
     */
    @Test
    void internationalRangeReportsTooOldAndTooNewClients() {
        var registry = ProtocolRegistry.getDefault();
        var processor = new InternationalProcessor();

        assertEquals(
                PlayStatusPacket.Status.LOGIN_FAILED_CLIENT_OLD,
                unsupportedStatus(
                        processor,
                        registry.getLowest(ClientVariant.INTERNATIONAL).getProtocolVersion() - 1,
                        false
                )
        );
        assertEquals(
                PlayStatusPacket.Status.LOGIN_FAILED_SERVER_OLD,
                unsupportedStatus(
                        processor,
                        registry.getLatest(ClientVariant.INTERNATIONAL).getProtocolVersion() + 1,
                        false
                )
        );
    }

    /**
     * Requests NetEase wire versions just below and above its supported registry
     * range. Each request must report the matching old-client or old-server
     * status without protocol installation.
     */
    @Test
    void netEaseRangeReportsTooOldAndTooNewClients() {
        var registry = ProtocolRegistry.getDefault();
        var processor = new NetEaseProcessor();

        assertEquals(
                PlayStatusPacket.Status.LOGIN_FAILED_CLIENT_OLD,
                unsupportedStatus(
                        processor,
                        registry.getLowest(ClientVariant.NETEASE).getProtocolVersion() - 1,
                        true
                )
        );
        assertEquals(
                PlayStatusPacket.Status.LOGIN_FAILED_SERVER_OLD,
                unsupportedStatus(
                        processor,
                        registry.getLatest(ClientVariant.NETEASE).getProtocolVersion() + 1,
                        true
                )
        );
    }

    /**
     * Requests missing versions inside both client variants' supported ranges.
     * Neither gap may select a nearby protocol or send a misleading range status;
     * each must disconnect.
     */
    @Test
    void missingVersionsInsideEachVariantRangeAreRejectedWithoutFallback() {
        assertMissingVersionRejected(new InternationalProcessor(), 820, false);
        assertMissingVersionRejected(new NetEaseProcessor(), 818, true);
    }

    /**
     * Returns mocked international settings using {@code ZLIB} and encoding
     * protection. NetEase support is disabled, keeping the successful negotiation
     * scenarios on the international branch without changing global configuration.
     */
    private static ServerSettings internationalNetworkSettings() {
        var settings = mock(ServerSettings.class);
        var networkSettings = mock(ServerSettings.NetworkSettings.class);
        when(settings.networkSettings()).thenReturn(networkSettings);
        when(networkSettings.neteaseClientSupport()).thenReturn(false);
        when(networkSettings.enableEncodingProtection()).thenReturn(true);
        when(networkSettings.compressionAlgorithm())
                .thenReturn(ServerSettings.NetworkSettings.CompressionAlgorithm.ZLIB);
        return settings;
    }

    /**
     * Returns mocked settings permitting NetEase negotiation with encoding
     * protection enabled. Compression selection remains the processor's NetEase
     * rule rather than a stubbed international algorithm, exposing its required
     * {@code ZLIB} behavior.
     */
    private static ServerSettings netEaseNetworkSettings() {
        var settings = mock(ServerSettings.class);
        var networkSettings = mock(ServerSettings.NetworkSettings.class);
        when(settings.networkSettings()).thenReturn(networkSettings);
        when(networkSettings.neteaseClientSupport()).thenReturn(true);
        when(networkSettings.enableEncodingProtection()).thenReturn(true);
        return settings;
    }

    /**
     * Returns the status sent for one out-of-range protocol request.
     * Static settings are scoped to this invocation, and the helper verifies that
     * rejection sends a status without installing any protocol.
     */
    private static PlayStatusPacket.Status unsupportedStatus(
            RequestNetworkSettingsPacketProcessor processor,
            int protocolVersion,
            boolean netEase
    ) {
        var target = mock(AllayPlayer.class);
        var request = new RequestNetworkSettingsPacket();
        request.setProtocolVersion(protocolVersion);
        var settings = minimalNetworkSettings(netEase);
        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);
            processor.handle(target, request);
        }

        var packet = ArgumentCaptor.forClass(org.cloudburstmc.protocol.bedrock.packet.BedrockPacket.class);
        verify(target).sendPacketImmediately(packet.capture());
        verify(target, never()).installProtocol(any());
        return assertInstanceOf(PlayStatusPacket.class, packet.getValue()).getStatus();
    }

    /**
     * Verifies rejection of one unsupported version inside a variant's range.
     * The helper scopes static settings to this request and requires a disconnect
     * without a status packet or protocol installation.
     */
    private static void assertMissingVersionRejected(
            RequestNetworkSettingsPacketProcessor processor,
            int protocolVersion,
            boolean netEase
    ) {
        var target = mock(AllayPlayer.class);
        var request = new RequestNetworkSettingsPacket();
        request.setProtocolVersion(protocolVersion);
        var settings = minimalNetworkSettings(netEase);
        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);
            processor.handle(target, request);
        }

        verify(target).disconnect(anyString());
        verify(target, never()).sendPacketImmediately(any());
        verify(target, never()).installProtocol(any());
    }

    /**
     * Returns mocked settings containing only the requested NetEase support flag.
     * Unsupported-version scenarios exit before compression settings are needed,
     * so these fixtures do not imply defaults for successful negotiation.
     */
    private static ServerSettings minimalNetworkSettings(boolean netEase) {
        var settings = mock(ServerSettings.class);
        var networkSettings = mock(ServerSettings.NetworkSettings.class);
        when(settings.networkSettings()).thenReturn(networkSettings);
        when(networkSettings.neteaseClientSupport()).thenReturn(netEase);
        return settings;
    }

    /**
     * {@code InternationalProcessor} fixes variant classification to international
     * for negotiation tests that do not exercise transport detection.
     */
    private static final class InternationalProcessor extends RequestNetworkSettingsPacketProcessor {
        /**
         * Returns {@code false} to select the international branch for every fixture.
         */
        @Override
        boolean isNetEaseClient(AllayPlayer allayPlayer) {
            return false;
        }
    }

    /**
     * {@code NetEaseProcessor} fixes variant classification to NetEase for
     * negotiation tests without requiring RakNet transport metadata.
     */
    private static final class NetEaseProcessor extends RequestNetworkSettingsPacketProcessor {
        /**
         * Returns {@code true} to select the NetEase branch for every fixture.
         */
        @Override
        boolean isNetEaseClient(AllayPlayer allayPlayer) {
            return true;
        }
    }
}
