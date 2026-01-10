package org.allaymc.server.player;

import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.player.LoginData;
import org.allaymc.api.player.OfflinePlayer;
import org.allaymc.api.player.PlayerManager;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.WorldPool;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.CleanupMode;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author IWareQ
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(AllayTestExtension.class)
public class AllayOfflinePlayerManagerTest {
    @TempDir(cleanup = CleanupMode.ALWAYS)
    private static Path tempDir;
    private static Path dbPath;

    private AllayOfflinePlayerManager offlinePlayerManager;
    private AllayPlayerStorage mockStorage;
    private MockedStatic<Server> serverStatic;

    @BeforeEach
    void setUp() {
        dbPath = tempDir.resolve(UUID.randomUUID().toString());
        mockStorage = new AllayNBTFilePlayerStorage(tempDir.resolve(UUID.randomUUID().toString()));
        offlinePlayerManager = new AllayOfflinePlayerManager(dbPath, mockStorage);


        var mockServer = mock(Server.class);
        var mockPlayerManager = mock(PlayerManager.class);
        var mockWorldPool = mock(WorldPool.class);

        var mockDimension = mock(Dimension.class);
        var mockWorld = mock(World.class);
        var mockWorldData = mock(WorldData.class);
        var mockEventBus = mock(EventBus.class);

        when(mockWorldData.getDisplayName()).thenReturn("world");
        when(mockWorld.getWorldData()).thenReturn(mockWorldData);
        when(mockDimension.getWorld()).thenReturn(mockWorld);
        when(mockDimension.getDimensionInfo()).thenReturn(mock(DimensionInfo.class));
        when(mockDimension.getDimensionInfo().dimensionId()).thenReturn(0);

        var spawn = new Location3i(0, 64, 0, mockDimension);

        when(mockServer.getPlayerManager()).thenReturn(mockPlayerManager);
        when(mockPlayerManager.getPlayerStorage()).thenReturn(mockStorage);
        when(mockServer.getWorldPool()).thenReturn(mockWorldPool);
        when(mockWorldPool.getGlobalSpawnPoint()).thenReturn(spawn);
        when(mockServer.getEventBus()).thenReturn(mockEventBus);

        serverStatic = mockStatic(Server.class);
        serverStatic.when(Server::getInstance).thenReturn(mockServer);
    }


    @AfterEach
    void tearDown() {
        if (offlinePlayerManager != null) {
            offlinePlayerManager.shutdown();
        }
        if (serverStatic != null) {
            serverStatic.close();
        }
    }

    @Test
    @Order(1)
    @DisplayName("Create new player with Xbox authentication")
    void testCreateNewPlayerWithXboxAuth() {
        var loginData = createMockLoginData(123456789L, "Steve");
        var player = offlinePlayerManager.handleUpdates(loginData);

        assertNotNull(player, "Player should be created");
        assertEquals("Steve", player.getName());
        assertEquals(123456789L, player.getXuid());
        assertNotNull(player.getStorageUuid());
    }

    @Test
    @Order(2)
    @DisplayName("Create new player in offline mode")
    void testCreateNewPlayerOfflineMode() {
        var loginData = createMockLoginData(null, "Alex");
        var player = offlinePlayerManager.handleUpdates(loginData);

        assertNotNull(player);
        assertEquals("Alex", player.getName());
        assertEquals(0L, player.getXuid());
        assertNotNull(player.getStorageUuid());
    }

    @Test
    @Order(3)
    @DisplayName("Get player by Xbox User ID")
    void testGetByXboxUserId() {
        var loginData = createMockLoginData(999888777L, "TestPlayer");
        var created = offlinePlayerManager.handleUpdates(loginData);

        var found = offlinePlayerManager.getByXboxUserId(999888777L);

        assertNotNull(found);
        assertEquals(created.getStorageUuid(), found.getStorageUuid());
        assertEquals("TestPlayer", found.getName());
    }

    @Test
    @Order(4)
    @DisplayName("Get player by nickname")
    void testGetByName() {
        var loginData = createMockLoginData(111222333L, "UniqueNick");
        offlinePlayerManager.handleUpdates(loginData);

        var found1 = offlinePlayerManager.getByName("UniqueNick");
        var found2 = offlinePlayerManager.getByName("uniquenick");
        var found3 = offlinePlayerManager.getByName("UNIQUENICK");

        assertNotNull(found1);
        assertNotNull(found2);
        assertNotNull(found3);
        assertEquals(found1.getStorageUuid(), found2.getStorageUuid());
        assertEquals(found2.getStorageUuid(), found3.getStorageUuid());
    }

    @Test
    @Order(5)
    @DisplayName("Get player by nickname UUID")
    void testGetByNameUuid() {
        var nickname = "TestNick";
        var loginData = createMockLoginData(null, nickname);
        var created = offlinePlayerManager.handleUpdates(loginData);

        var nameUuid = created.getNameUuid();
        var found = offlinePlayerManager.getByNameUuid(nameUuid);

        assertNotNull(found);
        assertEquals(created.getStorageUuid(), found.getStorageUuid());
    }

    @Test
    @Order(6)
    @DisplayName("Return null for non-existent player")
    void testGetNonExistentPlayer() {
        assertNull(offlinePlayerManager.getByXboxUserId(99999999L));
        assertNull(offlinePlayerManager.getByName("NonExistent"));
        assertNull(offlinePlayerManager.getByNameUuid(UUID.randomUUID()));
    }

    @Test
    @Order(10)
    @DisplayName("Handle simple nickname change")
    void testSimpleNicknameChange() {
        var login1 = createMockLoginData(555666777L, "OriginalName");
        var player1 = offlinePlayerManager.handleUpdates(login1);
        var storageUuid = player1.getStorageUuid();

        var login2 = createMockLoginData(555666777L, "NewName");
        var player2 = offlinePlayerManager.handleUpdates(login2);

        assertEquals(storageUuid, player2.getStorageUuid(), "Storage UUID should remain the same");
        assertEquals("NewName", player2.getName());

        assertNull(offlinePlayerManager.getByName("OriginalName"));
        assertNotNull(offlinePlayerManager.getByName("NewName"));
    }

    @Test
    @Order(11)
    @DisplayName("Handle nickname case change")
    void testNicknameCaseChange() {
        var login1 = createMockLoginData(null, "steve");
        var player1 = offlinePlayerManager.handleUpdates(login1);
        var storageUuid = player1.getStorageUuid();

        var login2 = createMockLoginData(null, "Steve");
        var player2 = offlinePlayerManager.handleUpdates(login2);

        assertEquals(storageUuid, player2.getStorageUuid());
        assertEquals("steve", player2.getName());

        assertNotNull(offlinePlayerManager.getByName("steve"));
        assertNotNull(offlinePlayerManager.getByName("Steve"));
        assertNotNull(offlinePlayerManager.getByName("STEVE"));
    }

    @Test
    @Order(20)
    @DisplayName("Handle nickname collision - two players swap nicknames")
    void testNicknameCollision_Swap() {
        var loginA1 = createMockLoginData(111L, "Alice");
        var loginB1 = createMockLoginData(222L, "Bob");

        var playerA = offlinePlayerManager.handleUpdates(loginA1);
        var playerB = offlinePlayerManager.handleUpdates(loginB1);

        var storageA = playerA.getStorageUuid();
        var storageB = playerB.getStorageUuid();

        // Act - PlayerA changes to "Bob" (takes it from PlayerB)
        var loginA2 = createMockLoginData(111L, "Bob");
        var playerA_v2 = offlinePlayerManager.handleUpdates(loginA2);

        // Assert - PlayerA now has "Bob"
        assertEquals(storageA, playerA_v2.getStorageUuid());
        assertEquals("Bob", playerA_v2.getName());

        // Act - PlayerB changes to "Alice" (takes it from PlayerA)
        var loginB2 = createMockLoginData(222L, "Alice");
        var playerB_v2 = offlinePlayerManager.handleUpdates(loginB2);

        // Assert - PlayerB now has "Alice"
        assertEquals(storageB, playerB_v2.getStorageUuid());
        assertEquals("Alice", playerB_v2.getName());

        // Check collision flags
        assertEquals("Bob", playerB_v2.getOriginalName());
        // null because original nickname added only for b player, because player A join first and take player B nickname
        assertNull(playerA_v2.getOriginalName());
    }

    @Test
    @Order(21)
    @DisplayName("Handle nickname collision - three-way swap")
    void testNicknameCollision_ThreeWay() {
        var playerA = offlinePlayerManager.handleUpdates(createMockLoginData(1001L, "A"));
        var playerB = offlinePlayerManager.handleUpdates(createMockLoginData(1002L, "B"));
        var playerC = offlinePlayerManager.handleUpdates(createMockLoginData(1003L, "C"));

        var storageA = playerA.getStorageUuid();
        var storageB = playerB.getStorageUuid();
        var storageC = playerC.getStorageUuid();

        // Act - Circular swap: A→B, B→C, C→A
        var playerA_v2 = offlinePlayerManager.handleUpdates(createMockLoginData(1001L, "B"));
        assertEquals("B", playerA_v2.getName());

        var playerB_v2 = offlinePlayerManager.handleUpdates(createMockLoginData(1002L, "C"));
        assertEquals("C", playerB_v2.getName());

        var playerC_v2 = offlinePlayerManager.handleUpdates(createMockLoginData(1003L, "A"));
        assertEquals("A", playerC_v2.getName());

        assertEquals(storageA, playerA_v2.getStorageUuid());
        assertEquals(storageB, playerB_v2.getStorageUuid());
        assertEquals(storageC, playerC_v2.getStorageUuid());
    }

    @Test
    @Order(22)
    @DisplayName("Handle nickname change for single player")
    void testNicknameChange_ForSinglePlayer() {
        var playerXuid = 1000L;

        var player_v1 = offlinePlayerManager.handleUpdates(createMockLoginData(playerXuid, "A"));
        var player_v2 = offlinePlayerManager.handleUpdates(createMockLoginData(playerXuid, "B"));
        var player_v3 = offlinePlayerManager.handleUpdates(createMockLoginData(playerXuid, "C"));

        var storage_v1 = player_v1.getStorageUuid();
        var storage_v2 = player_v2.getStorageUuid();
        var storage_v3 = player_v3.getStorageUuid();

        assertEquals(storage_v1, storage_v2);
        assertEquals(storage_v2, storage_v3);

        assertNull(offlinePlayerManager.getByName("A"));
        assertNull(offlinePlayerManager.getByName("B"));
        assertNotNull(offlinePlayerManager.getByName("C"));

        // all in lower case
        assertNull(offlinePlayerManager.getByNameUuid(UUID.nameUUIDFromBytes("a".getBytes(StandardCharsets.UTF_8))));
        assertNull(offlinePlayerManager.getByNameUuid(UUID.nameUUIDFromBytes("b".getBytes(StandardCharsets.UTF_8))));
        assertNotNull(offlinePlayerManager.getByNameUuid(UUID.nameUUIDFromBytes("c".getBytes(StandardCharsets.UTF_8))));

        var byXboxUserId = offlinePlayerManager.getByXboxUserId(playerXuid);
        assertNotNull(byXboxUserId);
        assertEquals("C", byXboxUserId.getName());
        assertEquals(storage_v1, byXboxUserId.getStorageUuid());
    }

    // ========== Data Persistence Tests ==========

    @Test
    @Order(30)
    @DisplayName("Player data persists across service restarts")
    void testDataPersistence() {
        var loginData = createMockLoginData(777888999L, "PersistTest");
        var player1 = offlinePlayerManager.handleUpdates(loginData);
        var storageUuid = player1.getStorageUuid();

        offlinePlayerManager.shutdown();
        offlinePlayerManager = new AllayOfflinePlayerManager(dbPath, mockStorage);

        var player2 = offlinePlayerManager.getByXboxUserId(777888999L);
        assertNotNull(player2);
        assertEquals(storageUuid, player2.getStorageUuid());
        assertEquals("PersistTest", player2.getName());
    }

    @Test
    @Order(31)
    @DisplayName("SaveAll saves all cached players")
    void testSaveAll() {
        offlinePlayerManager.handleUpdates(createMockLoginData(3001L, "Player1"));
        offlinePlayerManager.handleUpdates(createMockLoginData(3002L, "Player2"));
        offlinePlayerManager.handleUpdates(createMockLoginData(3003L, "Player3"));

        offlinePlayerManager.saveAll();
    }

    @Test
    @Order(40)
    @DisplayName("Handle null XUID gracefully")
    void testNullXuid() {
        var player = offlinePlayerManager.handleUpdates(createMockLoginData(null, "NullXuidPlayer"));

        assertNotNull(player);
        assertEquals(0L, player.getXuid());
        assertEquals("NullXuidPlayer", player.getName());
    }

    @Test
    @Order(41)
    @DisplayName("Handle empty nickname")
    void testEmptyNickname() {
        assertNull(offlinePlayerManager.getByName(""));
        assertNull(offlinePlayerManager.getByName(null));
    }

    @Test
    @Order(42)
    @DisplayName("Handle very long nickname")
    void testVeryLongNickname() {
        var longNickname = "A".repeat(100);
        var loginData = createMockLoginData(4001L, longNickname);

        var player = offlinePlayerManager.handleUpdates(loginData);

        assertNotNull(player);
        assertEquals(longNickname, player.getName());
    }

    @Test
    @Order(43)
    @DisplayName("Handle special characters in nickname")
    void testSpecialCharactersInNickname() {
        var specialNick = "Player_123-ABC";
        var loginData = createMockLoginData(4002L, specialNick);

        var player = offlinePlayerManager.handleUpdates(loginData);

        assertNotNull(player);
        assertEquals(specialNick, player.getName());

        assertNotNull(offlinePlayerManager.getByName(specialNick));
    }

    @Test
    @Order(44)
    @DisplayName("Handle Unicode nickname")
    void testUnicodeNickname() {
        var unicodeNick = "玩家_123";
        var loginData = createMockLoginData(4003L, unicodeNick);

        var player = offlinePlayerManager.handleUpdates(loginData);

        assertNotNull(player);
        assertEquals(unicodeNick, player.getName());
    }

    @Test
    @Order(50)
    @DisplayName("Offline NBT data is preserved")
    void testOfflineNbtData() {
        var player = offlinePlayerManager.handleUpdates(createMockLoginData(5001L, "DataTest"));

        var offlineData = NbtMap.builder()
                .putString("CustomKey", "CustomValue")
                .putInt("CustomInt", 42)
                .build();
        player.setOfflineNbtData(offlineData);
        player.save();

        var retrieved = player.getOfflineNbtData();
        assertEquals("CustomValue", retrieved.getString("CustomKey"));
        assertEquals(42, retrieved.getInt("CustomInt"));
    }

    @Test
    @Order(51)
    @DisplayName("Collision metadata is stored in offline data")
    void testCollisionMetadataStorage() {
        offlinePlayerManager.handleUpdates(createMockLoginData(5101L, "Original"));
        offlinePlayerManager.handleUpdates(createMockLoginData(5102L, "Stolen"));

        offlinePlayerManager.handleUpdates(createMockLoginData(5101L, "Stolen"));

        var victim = offlinePlayerManager.handleUpdates(createMockLoginData(5102L, "Original"));

        var offlineData = victim.getOfflineNbtData();
        assertEquals("Stolen", offlineData.getString(OfflinePlayer.TAG_ORIGINAL_NAME));
    }

    @Test
    @Order(60)
    @DisplayName("Players are cached after first load")
    void testPlayerCaching() {
        var loginData = createMockLoginData(6001L, "CacheTest");
        var player1 = offlinePlayerManager.handleUpdates(loginData);

        var player2 = offlinePlayerManager.getByXboxUserId(6001L);
        var player3 = offlinePlayerManager.getByName("CacheTest");

        assertSame(player1, player2);
        assertSame(player1, player3);
    }

    @Test
    @Order(61)
    @DisplayName("Cache is cleared on shutdown")
    void testCacheClearedOnShutdown() {
        offlinePlayerManager.handleUpdates(createMockLoginData(6101L, "ShutdownTest"));

        offlinePlayerManager.shutdown();

        assertThrows(Exception.class, () -> {
            offlinePlayerManager.getByXboxUserId(6101L);
        });
    }

    @Test
    @Order(70)
    @DisplayName("Complete player lifecycle")
    void testCompletePlayerLifecycle() {
        // 1. Create player
        var login1 = createMockLoginData(7001L, "LifecycleTest");
        var player = offlinePlayerManager.handleUpdates(login1);
        assertNotNull(player);
        assertEquals("LifecycleTest", player.getName());

        // 2. Change nickname
        var login2 = createMockLoginData(7001L, "NewLifecycleTest");
        player = offlinePlayerManager.handleUpdates(login2);
        assertEquals("NewLifecycleTest", player.getName());

        // 3. Set custom data
        var customData = NbtMap.builder().putString("key", "value").build();
        player.setOfflineNbtData(customData);
        player.save();

        // 4. Retrieve by different methods
        var byXuid = offlinePlayerManager.getByXboxUserId(7001L);
        var byNick = offlinePlayerManager.getByName("NewLifecycleTest");

        // 5. Verify consistency
        assertEquals(player.getStorageUuid(), byXuid.getStorageUuid());
        assertEquals(player.getStorageUuid(), byNick.getStorageUuid());
        assertEquals("value", byXuid.getOfflineNbtData().getString("key"));
    }

    private LoginData createMockLoginData(Long xuid, String nickname) {
        var loginData = mock(LoginData.class);
        when(loginData.getParsedXuid()).thenReturn(xuid);
        when(loginData.getXname()).thenReturn(nickname);
        when(loginData.getUuid()).thenReturn(UUID.randomUUID());
        return loginData;
    }
}
