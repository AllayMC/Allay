package org.allaymc.server.utils;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import static org.allaymc.api.block.type.BlockTypes.OAK_WOOD;

/**
 * @author daoge_cmd
 */
@ExtendWith({AllayTestExtension.class})
public class HashUtilsTest {
    static final int testBlockStateHash = 1622499771;
    static BlockState testBlockState;

    @BeforeAll
    static void before() {
        testBlockState = OAK_WOOD.getDefaultState();
    }

    @Test
    public void testComputeBlockStateHash1() {
        var value = HashUtils.computeBlockStateHash(testBlockState.getBlockType().getIdentifier(), new ArrayList<>(testBlockState.getPropertyValues().values()));
        Assertions.assertEquals(testBlockStateHash, value);
    }

    @Test
    public void testComputeBlockStateHash2() {
        BlockPropertyType.BlockPropertyValue<?, ?, ?>[] array = testBlockState.getPropertyValues().values().toArray(BlockPropertyType.BlockPropertyValue<?, ?, ?>[]::new);
        var value = HashUtils.computeBlockStateHash(testBlockState.getBlockType().getIdentifier(), array);
        Assertions.assertEquals(testBlockStateHash, value);
    }

    @Test
    public void testFnv1A32Nbt() {
        NbtMap tag = NbtMap.builder()
                .putString("name", "minecraft:oak_wood")
                .putCompound("states", NbtMap.builder()
                        .putString("pillar_axis", "y")
                        .build())
                .build();
        Assertions.assertEquals(testBlockStateHash, HashUtils.fnv1a_32_nbt(tag));
    }

    @Test
    public void testHashXZ() {
        long l = HashUtils.hashXZ(13, 53);
        Assertions.assertEquals(55834574901L, l);
    }

    @Test
    public void testGetXZFromHashXZ() {
        Assertions.assertEquals(13, HashUtils.getXFromHashXZ(55834574901L));
        Assertions.assertEquals(53, HashUtils.getZFromHashXZ(55834574901L));
    }

    @Test
    public void testHashChunkXYZ() {
        int l = HashUtils.hashChunkXYZ(8, 64, 8);
        Assertions.assertEquals(-2013264888, l);

        int l2 = HashUtils.hashChunkXYZ(8, -64, 8);
        Assertions.assertEquals(-2013266936, l2);
    }

    @Test
    public void testGetXYZFromHashChunkXYZ() {
        Assertions.assertEquals(8, HashUtils.getXFromHashChunkXYZ(-2013264888));
        Assertions.assertEquals(64, HashUtils.getYFromHashChunkXYZ(-2013264888));
        Assertions.assertEquals(-64, HashUtils.getYFromHashChunkXYZ(-2013266936));
        Assertions.assertEquals(8, HashUtils.getZFromHashChunkXYZ(-2013264888));
    }
}
