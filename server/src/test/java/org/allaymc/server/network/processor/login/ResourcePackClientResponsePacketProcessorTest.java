package org.allaymc.server.network.processor.login;

import org.allaymc.server.AllayServer;
import org.allaymc.server.ServerSettings;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.Protocol;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackClientResponsePacket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class ResourcePackClientResponsePacketProcessorTest {

    private static final UUID URL_PACK_ID = UUID.fromString("00000000-0000-0000-0000-0000000000aa");

    @Mock
    private AllayPlayer player;
    @Mock
    private Protocol protocol;
    @Mock
    private PacketEncoder encoder;

    @Test
    void sendPacksWithUrlPackIdSkipsWithoutDisconnectOrDataInfo() {
        var settings = settingsWithUrlPacks(List.of(urlPack()));
        when(player.getProtocol()).thenReturn(protocol);
        when(protocol.getEncoder()).thenReturn(encoder);

        var packet = new ResourcePackClientResponsePacket();
        packet.setStatus(ResourcePackClientResponsePacket.Status.SEND_PACKS);
        packet.getPackIds().add(URL_PACK_ID + "_1.0.0");

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);
            new ResourcePackClientResponsePacketProcessor().handle(player, packet);
        }

        verify(player, never()).disconnect(anyString());
        verify(player, never()).sendPacket(any());
        verify(encoder, never()).encodeResourcePackDataInfo(any(), anyInt());
    }

    @Test
    void sendPacksWithUnknownPackIdStillDisconnects() {
        var settings = settingsWithUrlPacks(List.of());
        when(player.getProtocol()).thenReturn(protocol);
        when(protocol.getEncoder()).thenReturn(encoder);

        var packet = new ResourcePackClientResponsePacket();
        packet.setStatus(ResourcePackClientResponsePacket.Status.SEND_PACKS);
        packet.getPackIds().add("11111111-1111-1111-1111-111111111111_1.0.0");

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);
            new ResourcePackClientResponsePacketProcessor().handle(player, packet);
        }

        verify(player).disconnect(anyString());
        verify(player, never()).sendPacket(any());
    }

    @Test
    void sendPacksWithBlankUrlEntryStillDisconnects() {
        var settings = settingsWithUrlPacks(List.of(
                new ServerSettings.ResourcePackSettings.UrlPackInfo()
                        .uuid(URL_PACK_ID.toString())
                        .version("1.0.0")
                        .url("  ")
        ));
        when(player.getProtocol()).thenReturn(protocol);
        when(protocol.getEncoder()).thenReturn(encoder);

        var packet = new ResourcePackClientResponsePacket();
        packet.setStatus(ResourcePackClientResponsePacket.Status.SEND_PACKS);
        packet.getPackIds().add(URL_PACK_ID + "_1.0.0");

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);
            new ResourcePackClientResponsePacketProcessor().handle(player, packet);
        }

        verify(player).disconnect(anyString());
        verify(player, never()).sendPacket(any());
    }

    private static ServerSettings settingsWithUrlPacks(List<ServerSettings.ResourcePackSettings.UrlPackInfo> urlPacks) {
        var settings = mock(ServerSettings.class);
        var resourcePackSettings = mock(ServerSettings.ResourcePackSettings.class);
        when(settings.resourcePackSettings()).thenReturn(resourcePackSettings);
        when(resourcePackSettings.maxChunkSize()).thenReturn(100);
        when(resourcePackSettings.urlPacks()).thenReturn(urlPacks);
        return settings;
    }

    private static ServerSettings.ResourcePackSettings.UrlPackInfo urlPack() {
        return new ServerSettings.ResourcePackSettings.UrlPackInfo()
                .uuid(URL_PACK_ID.toString())
                .version("1.0.0")
                .url("https://example.com/pack.mcpack");
    }
}