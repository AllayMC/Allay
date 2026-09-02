package org.allaymc.server.network.protocol;

import org.allaymc.server.AllayServer;
import org.allaymc.server.ServerSettings;
import org.allaymc.server.network.protocol.v766.PacketEncoder_v766;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class UrlPackEncoderTest {

    private static final UUID URL_PACK_ID = UUID.fromString("00000000-0000-0000-0000-0000000000aa");

    @Test
    void urlPackIsAdvertisedWithCdnUrlAndZeroSize() {
        var settings = settingsWithUrlPacks(List.of(urlPack("1.0.0", "https://example.com/pack.mcpack")));
        var encoder = new PacketEncoder_v766(mock(ProtocolData.class));

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);

            var info = encoder.encodeResourcePacksInfo();
            var entry = info.getResourcePackInfos().stream()
                    .filter(e -> e.getPackId().equals(URL_PACK_ID))
                    .findFirst()
                    .orElseThrow();
            assertEquals("https://example.com/pack.mcpack", entry.getCdnUrl());
            assertEquals(0, entry.getPackSize());
            assertEquals("1.0.0", entry.getPackVersion());
            assertFalse(entry.isAddonPack());

            var stack = encoder.encodeResourcePackStack();
            var stackEntry = stack.getResourcePacks().stream()
                    .filter(e -> e.packId().equals(URL_PACK_ID.toString()))
                    .findFirst()
                    .orElseThrow();
            assertEquals("1.0.0", stackEntry.packVersion());
        }
    }

    @Test
    void blankUrlEntriesAreNotAdvertised() {
        var settings = settingsWithUrlPacks(List.of(urlPack("1.0.0", "  ")));
        var encoder = new PacketEncoder_v766(mock(ProtocolData.class));

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);

            var info = encoder.encodeResourcePacksInfo();
            assertTrue(info.getResourcePackInfos().stream().noneMatch(e -> e.getPackId().equals(URL_PACK_ID)));

            var stack = encoder.encodeResourcePackStack();
            assertTrue(stack.getResourcePacks().stream().noneMatch(e -> e.packId().equals(URL_PACK_ID.toString())));
        }
    }

    @Test
    void emptyUrlPacksLeavesPacketsUnchanged() {
        var settings = settingsWithUrlPacks(List.of());
        var encoder = new PacketEncoder_v766(mock(ProtocolData.class));

        try (MockedStatic<AllayServer> allayServer = mockStatic(AllayServer.class)) {
            allayServer.when(AllayServer::getSettings).thenReturn(settings);

            var info = encoder.encodeResourcePacksInfo();
            assertTrue(info.getResourcePackInfos().stream().noneMatch(e -> e.getCdnUrl() != null));

            var stack = encoder.encodeResourcePackStack();
            assertTrue(stack.getResourcePacks().stream().noneMatch(e -> e.packId().equals(URL_PACK_ID.toString())));
        }
    }

    private static ServerSettings settingsWithUrlPacks(List<ServerSettings.ResourcePackSettings.UrlPackInfo> urlPacks) {
        var settings = mock(ServerSettings.class);
        var resourcePackSettings = mock(ServerSettings.ResourcePackSettings.class);
        when(settings.resourcePackSettings()).thenReturn(resourcePackSettings);
        when(resourcePackSettings.forceResourcePacks()).thenReturn(false);
        when(resourcePackSettings.disableVibrantVisuals()).thenReturn(false);
        when(resourcePackSettings.urlPacks()).thenReturn(urlPacks);
        return settings;
    }

    private static ServerSettings.ResourcePackSettings.UrlPackInfo urlPack(String version, String url) {
        return new ServerSettings.ResourcePackSettings.UrlPackInfo()
                .uuid(URL_PACK_ID.toString())
                .version(version)
                .url(url);
    }
}