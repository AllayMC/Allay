package org.allaymc.server.player;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserCacheTest {

    private static final String TEST_CACHE_DIR = "players";
    private static final String TEST_CACHE_FILE = "players/players.json";
    private UserCache userCache;

    @BeforeEach
    void setUp() {
        deleteCacheFile();
        userCache = new UserCache();
    }

    @AfterEach
    void tearDown() {
        deleteCacheFile();
    }

    private void deleteCacheFile() {
        File file = new File(TEST_CACHE_FILE);
        if (file.exists()) {
            file.delete();
        }
        File dir = new File(TEST_CACHE_DIR);
        if (dir.exists() && dir.isDirectory() && dir.list().length == 0) {
            dir.delete();
        }
    }

    @Test
    void testAddAndRetrieve() {
        UUID uuid = UUID.randomUUID();
        String name = "TestPlayer";

        userCache.add(name, uuid);

        assertEquals(name, userCache.getOfflinePlayerName(uuid));
        assertEquals(uuid, userCache.getOfflinePlayerUUID(name));
        assertEquals(uuid, userCache.getOfflinePlayerUUID(name.toLowerCase()));
        assertEquals(uuid, userCache.getOfflinePlayerUUID(name.toUpperCase()));
    }

    @Test
    void testPersistence() {
        UUID uuid = UUID.randomUUID();
        String name = "PersistPlayer";

        userCache.add(name, uuid);
        userCache.save();

        UserCache newCache = new UserCache();
        assertEquals(name, newCache.getOfflinePlayerName(uuid));
    }

    @Test
    void testDuplicateNameUpdatesUUID() {
        String name = "MovingPlayer";
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();

        userCache.add(name, uuid1);
        assertEquals(uuid1, userCache.getOfflinePlayerUUID(name));

        userCache.add(name, uuid2);
        assertEquals(uuid2, userCache.getOfflinePlayerUUID(name));

    }

    @Test
    void testLimitSize() {
        for (int i = 0; i < 1100; i++) {
            userCache.add("Player" + i, UUID.randomUUID());
        }

        assertNull(userCache.getOfflinePlayerUUID("Player0"));
        assertNotNull(userCache.getOfflinePlayerUUID("Player1099"));
    }
}
