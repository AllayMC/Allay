package cn.allay.server.world;

import cn.allay.api.block.type.VanillaBlockTypes;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.server.world.chunk.AllayChunk;
import cn.allay.testutils.AllayTestExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(AllayTestExtension.class)
public class AllayChunkTest {
    @Test
    void testParallelUpdateBlock() throws InterruptedException {
        final Chunk chunk = new AllayChunk(0, 0, DimensionInfo.of(0));
        Thread first = new Thread(() -> {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    for (int k = 0; k < 100; k++) {
                        chunk.setBlock(i, k, j, VanillaBlockTypes.WOOD_TYPE.getDefaultState());
                        log.info("%d %d %d write".formatted(i, k, j));
                    }
                }
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    for (int k = 0; k < 100; k++) {
                        assertEquals(VanillaBlockTypes.WOOD_TYPE.getDefaultState(), chunk.getBlock(i, k, j), "(" + i + "," + k + "," + j + ") GET BLOCK ERROR!");
                        log.info("%d %d %d read".formatted(i, k, j));
                    }
                }
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
    }

    @Test
    void testParallelUpdateLight() throws InterruptedException {
        final Chunk chunk = new AllayChunk(0, 0, DimensionInfo.of(0));
        Thread first = new Thread(() -> {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    for (int k = 0; k < 100; k++) {
                        chunk.setBlockLight(i, k, j, 5);
                        chunk.setSkyLight(i, k, j, 6);
                    }
                }
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    for (int k = 0; k < 100; k++) {
                        assertEquals(5, chunk.getBlockLight(i, k, j), "(" + i + "," + k + "," + j + ") GET BLOCK LIGHT ERROR!");
                        assertEquals(6, chunk.getSkyLight(i, k, j), "(" + i + "," + k + "," + j + ") GET SKY LIGHT ERROR!");
                    }
                }
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
    }

    @Test
    void testBatchProcess() {
//        for (int i = 1; i <= 1000; i++) {
//            final Chunk chunk = new AllayChunk(0, 0, DimensionInfo.of(0));
//            chunk.batchProcess(a1->{
//                a1.setBlock();
//            }, a2->{
//
//            },a3->{
//
//            },a4->{
//
//            });
//        }
    }
}
