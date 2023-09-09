package cn.allay.server.world;

import cn.allay.api.block.interfaces.BlockWoodBehavior;
import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.server.world.chunk.AllayChunk;
import cn.allay.server.world.chunk.AllayUnsafeChunk;
import cn.allay.testutils.AllayTestExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Slf4j
@ExtendWith(AllayTestExtension.class)
public class AllayChunkTest {
    final Chunk chunk = new AllayChunk(AllayUnsafeChunk.builder().emptyChunk(0, 0, DimensionInfo.OVERWORLD));

    @Test
    void testUpdateBlockState() {
        chunk.setBlockState(0, 0, 0, BlockWoodBehavior.WOOD_TYPE.getDefaultState(), 0);
        Assertions.assertEquals(BlockWoodBehavior.WOOD_TYPE.getDefaultState(), chunk.getBlockState(0, 0, 0, 0));
    }

    @Test
    void testUpdateBiome() {
        chunk.setBiome(0, 10, 0, VanillaBiomeId.CHERRY_GROVE);
        Assertions.assertEquals(VanillaBiomeId.CHERRY_GROVE, chunk.getBiome(0, 10, 0));
    }

    @Test
    void testUpdateSkyLight() {
        chunk.setSkyLight(0, 20, 0, 5);
        Assertions.assertEquals(5, chunk.getSkyLight(0, 20, 0));
    }

    @Test
    void testUpdateBlockLight() {
        chunk.setBlockLight(0, 30, 0, 6);
        Assertions.assertEquals(6, chunk.getBlockLight(0, 30, 0));
    }

    @Test
    void testUpdateHeight() {
        chunk.setHeight(0, 0, 100);
        Assertions.assertEquals(100, chunk.getHeight(0, 0));
    }
}
