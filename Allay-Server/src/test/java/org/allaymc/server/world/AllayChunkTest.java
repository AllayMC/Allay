package org.allaymc.server.world;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.interfaces.BlockAirBehavior;
import org.allaymc.api.block.interfaces.liquid.BlockWaterBehavior;
import org.allaymc.api.block.interfaces.wood.BlockWoodBehavior;
import org.allaymc.api.blockentity.init.SimpleBlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.data.VanillaBiomeId;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.server.world.chunk.AllayChunk;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(AllayTestExtension.class)
class AllayChunkTest {
    final Chunk chunk = new AllayChunk(AllayUnsafeChunk.builder().emptyChunk(0, 0, DimensionInfo.OVERWORLD));

    @Test
    void testInvalidGetBlockStateMethodCall() {
        assertEquals(BlockAirBehavior.AIR_TYPE.getDefaultState(), chunk.getBlockState(0, -10000, 0));
    }

    @Test
    void testUpdateBlockState() {
        chunk.setBlockState(0, 0, 0, BlockWoodBehavior.WOOD_TYPE.getDefaultState(), 0);
        chunk.setBlockState(0, 0, 0, BlockWaterBehavior.WATER_TYPE.getDefaultState(), 1);
        assertEquals(BlockWoodBehavior.WOOD_TYPE.getDefaultState(), chunk.getBlockState(0, 0, 0, 0));
        assertEquals(BlockWaterBehavior.WATER_TYPE.getDefaultState(), chunk.getBlockState(0, 0, 0, 1));
    }

    @Test
    void testUpdateBiome() {
        chunk.setBiome(0, 10, 0, VanillaBiomeId.CHERRY_GROVE);
        assertEquals(VanillaBiomeId.CHERRY_GROVE, chunk.getBiome(0, 10, 0));
    }

    @Test
    void testUpdateSkyLight() {
        chunk.setSkyLight(0, 20, 0, 5);
        assertEquals(5, chunk.getSkyLight(0, 20, 0));
    }

    @Test
    void testUpdateBlockLight() {
        chunk.setBlockLight(0, 30, 0, 6);
        assertEquals(6, chunk.getBlockLight(0, 30, 0));
    }

    @Test
    void testUpdateHeight() {
        chunk.setHeight(0, 0, 100);
        assertEquals(100, chunk.getHeight(0, 0));
    }

    @Test
    void testUpdateBlockEntity() {
        var blockEntity = BlockEntityBarrel.BARREL_TYPE.createBlockEntity(
                SimpleBlockEntityInitInfo
                        .builder()
                        .pos(11, 45, 14)
                        .build()
        );
        chunk.addBlockEntity(blockEntity);
        assertNotNull(chunk.getBlockEntity(11, 45, 14));
        chunk.removeBlockEntity(11, 45, 14);
        assertNull(chunk.getBlockEntity(11, 45, 14));
    }
}
