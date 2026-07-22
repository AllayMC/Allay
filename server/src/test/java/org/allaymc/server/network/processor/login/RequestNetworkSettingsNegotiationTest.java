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

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class RequestNetworkSettingsNegotiationTest {

    @Mock
    private AllayPlayer player;
    @Mock
    private BedrockServerSession bedrockSession;
    @Mock
    private ProtocolSession protocolSession;

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

    @Test
    void missingVersionsInsideEachVariantRangeAreRejectedWithoutFallback() {
        assertMissingVersionRejected(new InternationalProcessor(), 820, false);
        assertMissingVersionRejected(new NetEaseProcessor(), 818, true);
    }

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

    private static ServerSettings netEaseNetworkSettings() {
        var settings = mock(ServerSettings.class);
        var networkSettings = mock(ServerSettings.NetworkSettings.class);
        when(settings.networkSettings()).thenReturn(networkSettings);
        when(networkSettings.neteaseClientSupport()).thenReturn(true);
        when(networkSettings.enableEncodingProtection()).thenReturn(true);
        return settings;
    }

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

    private static ServerSettings minimalNetworkSettings(boolean netEase) {
        var settings = mock(ServerSettings.class);
        var networkSettings = mock(ServerSettings.NetworkSettings.class);
        when(settings.networkSettings()).thenReturn(networkSettings);
        when(networkSettings.neteaseClientSupport()).thenReturn(netEase);
        return settings;
    }

    private static final class InternationalProcessor extends RequestNetworkSettingsPacketProcessor {
        @Override
        boolean isNetEaseClient(AllayPlayer allayPlayer) {
            return false;
        }
    }

    private static final class NetEaseProcessor extends RequestNetworkSettingsPacketProcessor {
        @Override
        boolean isNetEaseClient(AllayPlayer allayPlayer) {
            return true;
        }
    }
}
