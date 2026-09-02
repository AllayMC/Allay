package org.allaymc.server;

import eu.okaeri.configs.ConfigManager;
import org.allaymc.server.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerSettingsUrlPacksTest {

    @TempDir
    Path tempDir;

    @Test
    void urlPacksRoundTrip() throws Exception {
        var path = tempDir.resolve("server-settings.yml");
        var initializer = Utils.createConfigInitializer(path);
        var config = ConfigManager.create(ServerSettings.class, initializer);
        var field = ServerSettings.ResourcePackSettings.class.getDeclaredField("urlPacks");
        field.setAccessible(true);
        field.set(config.resourcePackSettings(), List.of(
                new ServerSettings.ResourcePackSettings.UrlPackInfo()
                        .uuid("00000000-0000-0000-0000-000000000001")
                        .version("1.0.0")
                        .url("https://example.com/pack.mcpack"),
                new ServerSettings.ResourcePackSettings.UrlPackInfo()
                        .uuid("00000000-0000-0000-0000-000000000002")
                        .version("2.0.0")
                        .url("https://example.com/pack2.mcpack")
        ));
        config.save();

        var loaded = ConfigManager.create(ServerSettings.class, initializer);
        var urlPacks = loaded.resourcePackSettings().urlPacks();
        assertEquals(2, urlPacks.size());
        assertEquals("00000000-0000-0000-0000-000000000001", urlPacks.get(0).uuid());
        assertEquals("1.0.0", urlPacks.get(0).version());
        assertEquals("https://example.com/pack.mcpack", urlPacks.get(0).url());
        assertEquals("00000000-0000-0000-0000-000000000002", urlPacks.get(1).uuid());
        assertEquals("2.0.0", urlPacks.get(1).version());
        assertEquals("https://example.com/pack2.mcpack", urlPacks.get(1).url());
    }

    @Test
    void urlPacksDefaultToEmptyList() {
        var path = tempDir.resolve("server-settings-default.yml");
        var config = ConfigManager.create(ServerSettings.class, Utils.createConfigInitializer(path));
        assertEquals(List.of(), config.resourcePackSettings().urlPacks());
    }
}