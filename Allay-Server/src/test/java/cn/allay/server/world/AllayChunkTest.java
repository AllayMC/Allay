package cn.allay.server.world;

import cn.allay.api.block.type.VanillaBlockTypes;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.server.world.chunk.AllayChunk;
import cn.allay.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AllayTestExtension.class)
public class AllayChunkTest {
    public void update(Chunk chunk) {
        chunk.compareAndSetBlock(0, 0, 0, false, VanillaBlockTypes.AIR_TYPE.getDefaultState(), VanillaBlockTypes.STONE_TYPE.getDefaultState());
        chunk.setBlock(0, 0, 0, VanillaBlockTypes.WOOD_TYPE.getDefaultState());
    }

    @Test
    void testParallelOperate() throws InterruptedException {
        for (int i = 1; i <= 1000; i++) {
            final Chunk chunk = new AllayChunk(0, 0, DimensionInfo.of(0));
            Thread first = new Thread(() -> update(chunk));
            Thread second = new Thread(() -> update(chunk));
            first.start();
            second.start();
            first.join();
            second.join();
            assertEquals(VanillaBlockTypes.WOOD_TYPE.getDefaultState(), chunk.getBlock(0, 0, 0), "TEST" + i + "ERROR");
            System.out.println("TEST" + i + "SUCCESS");
        }
    }
}
