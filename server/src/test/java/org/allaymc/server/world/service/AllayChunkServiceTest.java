package org.allaymc.server.world.service;

import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.World;
import org.allaymc.server.world.AllayWorldData;
import org.allaymc.server.world.generator.AllayWorldGenerator;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AllayChunkServiceTest {
    static Dimension mockDimension = Mockito.mock(Dimension.class);
    static World mockWorld = Mockito.mock(World.class);
    static AllayChunkService chunkService;
    static TestChunkLoader chunkLoader1 = new TestChunkLoader();

    @BeforeAll
    static void init() {
        var testLightService = new TestLightService();
        var testWorldStorage = new TestWorldStorage();
        var testEntityService = new TestEntityService(mockDimension, testWorldStorage);
        var defaultWorldData = AllayWorldData.builder().build();
        chunkService = new AllayChunkService(mockDimension, AllayWorldGenerator.builder().name("TEST").build(), testWorldStorage);

        Mockito.when(mockDimension.getDimensionInfo()).thenReturn(DimensionInfo.OVERWORLD);
        Mockito.when(mockDimension.getLightService()).thenReturn(testLightService);
        Mockito.when(mockDimension.getEntityService()).thenReturn(testEntityService);
        Mockito.when(mockDimension.getWorld()).thenReturn(mockWorld);
        Mockito.when(mockWorld.getWorldData()).thenReturn(defaultWorldData);
    }

    static void tick() {
        chunkService.tick(0);
    }

    static void tick(int count) {
        for (int i = 0; i < count; i++) {
            chunkService.tick(0);
        }
    }

    @Test
    @Order(1)
    void testLoadChunks() {
        chunkLoader1.setChunkTrySendCountPerTick(16);
        chunkService.addChunkLoader(chunkLoader1);
        assertEquals(0, chunkService.getLoadedChunks().size());
        assertEquals(1, chunkService.getChunkLoaders().size());
        tick();
        assertEquals(16, chunkService.getLoadedChunks().size());
        tick();
        assertEquals(32, chunkService.getLoadedChunks().size());
        tick();
        assertEquals(48, chunkService.getLoadedChunks().size());
        tick();
        assertEquals(64, chunkService.getLoadedChunks().size());
        tick(30);
    }

    @Test
    @Order(2)
    void testUnloadChunks() {
        chunkService.setRemoveUnneededChunkCycle(5);
        chunkService.removeChunkLoader(chunkLoader1);
        tick(4);
        assertNotEquals(0, chunkService.getLoadedChunks().size());
        tick(2);
        assertEquals(0, chunkService.getLoadedChunks().size());
    }
}
