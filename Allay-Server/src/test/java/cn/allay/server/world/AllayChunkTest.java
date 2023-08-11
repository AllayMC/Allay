package cn.allay.server.world;

import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.World;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.heightmap.HeightMapType;
import cn.allay.server.world.chunk.AllayChunk;
import cn.allay.testutils.AllayTestExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@Slf4j
@ExtendWith(AllayTestExtension.class)
public class AllayChunkTest {
    final World world = Mockito.mock(World.class);
    final Chunk chunk = new AllayChunk(world, 0, 0, DimensionInfo.of(0));

    @Test
    void testUpdateBlockState() {
        chunk.setBlockState(0, 0, 0, VanillaBlockTypes.WOOD_TYPE.getDefaultState(), false);
        Assertions.assertEquals(VanillaBlockTypes.WOOD_TYPE.getDefaultState(), chunk.getBlockState(0, 0, 0, false));
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
        chunk.setHeight(HeightMapType.WORLD_SURFACE, 0, 0, 100);
        Assertions.assertEquals(100, chunk.getHeight(HeightMapType.WORLD_SURFACE, 0, 0));
    }
}
