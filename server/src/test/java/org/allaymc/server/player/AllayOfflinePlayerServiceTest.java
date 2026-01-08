package org.allaymc.server.player;

import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.player.LoginData;
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
public class AllayOfflinePlayerServiceTest {
    @TempDir(cleanup = CleanupMode.ALWAYS)
    private static Path tempDir;
    private static Path dbPath;

    private AllayOfflinePlayerService service;
    private AllayPlayerStorage mockStorage;
    private MockedStatic<Server> serverStatic;

    @BeforeEach
    void setUp() {
        dbPath = tempDir.resolve(UUID.randomUUID().toString());
        mockStorage = new AllayNBTFilePlayerStorage(tempDir.resolve(UUID.randomUUID().toString()));
        service = new AllayOfflinePlayerService(dbPath, mockStorage);


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
        if (service != null) {
            service.shutdown();
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
        var player = service.handleUpdates(loginData);

        assertNotNull(player, "Player should be created");
        assertEquals("Steve", player.getNickname());
        assertEquals(123456789L, player.getXuid());
        assertNotNull(player.getStorageUuid());
    }

    @Test
    @Order(2)
    @DisplayName("Create new player in offline mode")
    void testCreateNewPlayerOfflineMode() {
        var loginData = createMockLoginData(null, "Alex");
        var player = service.handleUpdates(loginData);

        assertNotNull(player);
        assertEquals("Alex", player.getNickname());
        assertEquals(0L, player.getXuid());
        assertNotNull(player.getStorageUuid());
    }

    @Test
    @Order(3)
    @DisplayName("Get player by Xbox User ID")
    void testGetByXboxUserId() {
        var loginData = createMockLoginData(999888777L, "TestPlayer");
        var created = service.handleUpdates(loginData);

        var found = service.getByXboxUserId(999888777L);

        assertNotNull(found);
        assertEquals(created.getStorageUuid(), found.getStorageUuid());
        assertEquals("TestPlayer", found.getNickname());
    }

    @Test
    @Order(4)
    @DisplayName("Get player by nickname")
    void testGetByNickname() {
        var loginData = createMockLoginData(111222333L, "UniqueNick");
        service.handleUpdates(loginData);

        var found1 = service.getByNickname("UniqueNick");
        var found2 = service.getByNickname("uniquenick");
        var found3 = service.getByNickname("UNIQUENICK");

        assertNotNull(found1);
        assertNotNull(found2);
        assertNotNull(found3);
        assertEquals(found1.getStorageUuid(), found2.getStorageUuid());
        assertEquals(found2.getStorageUuid(), found3.getStorageUuid());
    }

    @Test
    @Order(5)
    @DisplayName("Get player by nickname UUID")
    void testGetByNicknameUuid() {
        var nickname = "TestNick";
        var loginData = createMockLoginData(null, nickname);
        var created = service.handleUpdates(loginData);

        var nameUuid = created.getNameUuid();
        var found = service.getByNicknameUuid(nameUuid);

        assertNotNull(found);
        assertEquals(created.getStorageUuid(), found.getStorageUuid());
    }

    @Test
    @Order(6)
    @DisplayName("Return null for non-existent player")
    void testGetNonExistentPlayer() {
        assertNull(service.getByXboxUserId(99999999L));
        assertNull(service.getByNickname("NonExistent"));
        assertNull(service.getByNicknameUuid(UUID.randomUUID()));
    }

    @Test
    @Order(10)
    @DisplayName("Handle simple nickname change")
    void testSimpleNicknameChange() {
        var login1 = createMockLoginData(555666777L, "OriginalName");
        var player1 = service.handleUpdates(login1);
        var storageUuid = player1.getStorageUuid();

        var login2 = createMockLoginData(555666777L, "NewName");
        var player2 = service.handleUpdates(login2);

        assertEquals(storageUuid, player2.getStorageUuid(), "Storage UUID should remain the same");
        assertEquals("NewName", player2.getNickname());

        assertNull(service.getByNickname("OriginalName"));
        assertNotNull(service.getByNickname("NewName"));
    }

    @Test
    @Order(11)
    @DisplayName("Handle nickname case change")
    void testNicknameCaseChange() {
        var login1 = createMockLoginData(null, "steve");
        var player1 = service.handleUpdates(login1);
        var storageUuid = player1.getStorageUuid();

        var login2 = createMockLoginData(null, "Steve");
        var player2 = service.handleUpdates(login2);

        assertEquals(storageUuid, player2.getStorageUuid());
        assertEquals("steve", player2.getNickname());

        assertNotNull(service.getByNickname("steve"));
        assertNotNull(service.getByNickname("Steve"));
        assertNotNull(service.getByNickname("STEVE"));
    }

    @Test
    @Order(20)
    @DisplayName("Handle nickname collision - two players swap nicknames")
    void testNicknameCollision_Swap() {
        var loginA1 = createMockLoginData(111L, "Alice");
        var loginB1 = createMockLoginData(222L, "Bob");

        var playerA = service.handleUpdates(loginA1);
        var playerB = service.handleUpdates(loginB1);

        var storageA = playerA.getStorageUuid();
        var storageB = playerB.getStorageUuid();

        // Act - PlayerA changes to "Bob" (takes it from PlayerB)
        var loginA2 = createMockLoginData(111L, "Bob");
        var playerA_v2 = service.handleUpdates(loginA2);

        // Assert - PlayerA now has "Bob"
        assertEquals(storageA, playerA_v2.getStorageUuid());
        assertEquals("Bob", playerA_v2.getNickname());

        // Act - PlayerB changes to "Alice" (takes it from PlayerA)
        var loginB2 = createMockLoginData(222L, "Alice");
        var playerB_v2 = service.handleUpdates(loginB2);

        // Assert - PlayerB now has "Alice"
        assertEquals(storageB, playerB_v2.getStorageUuid());
        assertEquals("Alice", playerB_v2.getNickname());

        // Check collision flags
        assertEquals("Bob", playerB_v2.getOriginalNickname());
        // null because original nickname added only for b player, because player A join first and take player B nickname
        assertNull(playerA_v2.getOriginalNickname());
    }

    @Test
    @Order(21)
    @DisplayName("Handle nickname collision - three-way swap")
    void testNicknameCollision_ThreeWay() {
        var playerA = service.handleUpdates(createMockLoginData(1001L, "A"));
        var playerB = service.handleUpdates(createMockLoginData(1002L, "B"));
        var playerC = service.handleUpdates(createMockLoginData(1003L, "C"));

        var storageA = playerA.getStorageUuid();
        var storageB = playerB.getStorageUuid();
        var storageC = playerC.getStorageUuid();

        // Act - Circular swap: A→B, B→C, C→A
        var playerA_v2 = service.handleUpdates(createMockLoginData(1001L, "B"));
        assertEquals("B", playerA_v2.getNickname());

        var playerB_v2 = service.handleUpdates(createMockLoginData(1002L, "C"));
        assertEquals("C", playerB_v2.getNickname());

        var playerC_v2 = service.handleUpdates(createMockLoginData(1003L, "A"));
        assertEquals("A", playerC_v2.getNickname());

        assertEquals(storageA, playerA_v2.getStorageUuid());
        assertEquals(storageB, playerB_v2.getStorageUuid());
        assertEquals(storageC, playerC_v2.getStorageUuid());
    }

    @Test
    @Order(22)
    @DisplayName("Handle nickname change for single player")
    void testNicknameChange_ForSinglePlayer() {
        var playerXuid = 1000L;

        var player_v1 = service.handleUpdates(createMockLoginData(playerXuid, "A"));
        var player_v2 = service.handleUpdates(createMockLoginData(playerXuid, "B"));
        var player_v3 = service.handleUpdates(createMockLoginData(playerXuid, "C"));

        var storage_v1 = player_v1.getStorageUuid();
        var storage_v2 = player_v2.getStorageUuid();
        var storage_v3 = player_v3.getStorageUuid();

        assertEquals(storage_v1, storage_v2);
        assertEquals(storage_v2, storage_v3);

        assertNull(service.getByNickname("A"));
        assertNull(service.getByNickname("B"));
        assertNotNull(service.getByNickname("C"));

        // all in lower case
        assertNull(service.getByNicknameUuid(UUID.nameUUIDFromBytes("a".getBytes(StandardCharsets.UTF_8))));
        assertNull(service.getByNicknameUuid(UUID.nameUUIDFromBytes("b".getBytes(StandardCharsets.UTF_8))));
        assertNotNull(service.getByNicknameUuid(UUID.nameUUIDFromBytes("c".getBytes(StandardCharsets.UTF_8))));

        var byXboxUserId = service.getByXboxUserId(playerXuid);
        assertNotNull(byXboxUserId);
        assertEquals("C", byXboxUserId.getNickname());
        assertEquals(storage_v1, byXboxUserId.getStorageUuid());
    }

    // ========== Data Persistence Tests ==========

    @Test
    @Order(30)
    @DisplayName("Player data persists across service restarts")
    void testDataPersistence() {
        var loginData = createMockLoginData(777888999L, "PersistTest");
        var player1 = service.handleUpdates(loginData);
        var storageUuid = player1.getStorageUuid();

        service.shutdown();
        service = new AllayOfflinePlayerService(dbPath, mockStorage);

        var player2 = service.getByXboxUserId(777888999L);
        assertNotNull(player2);
        assertEquals(storageUuid, player2.getStorageUuid());
        assertEquals("PersistTest", player2.getNickname());
    }

    @Test
    @Order(31)
    @DisplayName("SaveAll saves all cached players")
    void testSaveAll() {
        service.handleUpdates(createMockLoginData(3001L, "Player1"));
        service.handleUpdates(createMockLoginData(3002L, "Player2"));
        service.handleUpdates(createMockLoginData(3003L, "Player3"));

        service.saveAll();
    }

    @Test
    @Order(40)
    @DisplayName("Handle null XUID gracefully")
    void testNullXuid() {
        var player = service.handleUpdates(createMockLoginData(null, "NullXuidPlayer"));

        assertNotNull(player);
        assertEquals(0L, player.getXuid());
        assertEquals("NullXuidPlayer", player.getNickname());
    }

    @Test
    @Order(41)
    @DisplayName("Handle empty nickname")
    void testEmptyNickname() {
        assertNull(service.getByNickname(""));
        assertNull(service.getByNickname(null));
    }

    @Test
    @Order(42)
    @DisplayName("Handle very long nickname")
    void testVeryLongNickname() {
        var longNickname = "A".repeat(100);
        var loginData = createMockLoginData(4001L, longNickname);

        var player = service.handleUpdates(loginData);

        assertNotNull(player);
        assertEquals(longNickname, player.getNickname());
    }

    @Test
    @Order(43)
    @DisplayName("Handle special characters in nickname")
    void testSpecialCharactersInNickname() {
        var specialNick = "Player_123-ABC";
        var loginData = createMockLoginData(4002L, specialNick);

        var player = service.handleUpdates(loginData);

        assertNotNull(player);
        assertEquals(specialNick, player.getNickname());

        assertNotNull(service.getByNickname(specialNick));
    }

    @Test
    @Order(44)
    @DisplayName("Handle Unicode nickname")
    void testUnicodeNickname() {
        var unicodeNick = "玩家_123";
        var loginData = createMockLoginData(4003L, unicodeNick);

        var player = service.handleUpdates(loginData);

        assertNotNull(player);
        assertEquals(unicodeNick, player.getNickname());
    }

    @Test
    @Order(50)
    @DisplayName("Offline NBT data is preserved")
    void testOfflineNbtData() {
        var player = service.handleUpdates(createMockLoginData(5001L, "DataTest"));

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
        service.handleUpdates(createMockLoginData(5101L, "Original"));
        service.handleUpdates(createMockLoginData(5102L, "Stolen"));

        service.handleUpdates(createMockLoginData(5101L, "Stolen"));

        var victim = service.handleUpdates(createMockLoginData(5102L, "Original"));

        var offlineData = victim.getOfflineNbtData();
        assertEquals("Stolen", offlineData.getString(AllayOfflinePlayerService.TAG_ORIGINAL_NICKNAME));
    }

    @Test
    @Order(60)
    @DisplayName("Players are cached after first load")
    void testPlayerCaching() {
        var loginData = createMockLoginData(6001L, "CacheTest");
        var player1 = service.handleUpdates(loginData);

        var player2 = service.getByXboxUserId(6001L);
        var player3 = service.getByNickname("CacheTest");

        assertSame(player1, player2);
        assertSame(player1, player3);
    }

    @Test
    @Order(61)
    @DisplayName("Cache is cleared on shutdown")
    void testCacheClearedOnShutdown() {
        service.handleUpdates(createMockLoginData(6101L, "ShutdownTest"));

        service.shutdown();

        assertThrows(Exception.class, () -> {
            service.getByXboxUserId(6101L);
        });
    }

    @Test
    @Order(70)
    @DisplayName("Complete player lifecycle")
    void testCompletePlayerLifecycle() {
        // 1. Create player
        var login1 = createMockLoginData(7001L, "LifecycleTest");
        var player = service.handleUpdates(login1);
        assertNotNull(player);
        assertEquals("LifecycleTest", player.getNickname());

        // 2. Change nickname
        var login2 = createMockLoginData(7001L, "NewLifecycleTest");
        player = service.handleUpdates(login2);
        assertEquals("NewLifecycleTest", player.getNickname());

        // 3. Set custom data
        var customData = NbtMap.builder().putString("key", "value").build();
        player.setOfflineNbtData(customData);
        player.save();

        // 4. Retrieve by different methods
        var byXuid = service.getByXboxUserId(7001L);
        var byNick = service.getByNickname("NewLifecycleTest");

        // 5. Verify consistency
        assertEquals(player.getStorageUuid(), byXuid.getStorageUuid());
        assertEquals(player.getStorageUuid(), byNick.getStorageUuid());
        assertEquals("value", byXuid.getOfflineNbtData().getString("key"));
    }

    private LoginData createMockLoginData(Long xuid, String nickname) {
        var loginData = mock(LoginData.class);
        when(loginData.getParsedXuid()).thenReturn(xuid);
        when(loginData.getXname()).thenReturn(nickname);
        return loginData;
    }
}
