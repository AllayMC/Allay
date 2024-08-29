package org.allaymc.server.world.service;

import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Allay Project 2024/8/29
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AllayChunkServiceTest {
    static Dimension mockDimension = Mockito.mock(Dimension.class);
    static AllayChunkService chunkService;
    static TestChunkLoader chunkLoader1 = new TestChunkLoader();

    @BeforeAll
    static void init() {
        Mockito.when(mockDimension.getDimensionInfo()).thenReturn(DimensionInfo.OVERWORLD);
        chunkService = new AllayChunkService(mockDimension, new TestWorldStorage());
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

    static void tick() {
        chunkService.tick(0);
    }

    static void tick(int count) {
        for (int i = 0; i < count; i++) {
            chunkService.tick(0);
        }
    }
}
