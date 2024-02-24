package org.allaymc.server.utils;

import org.allaymc.api.block.interfaces.wood.BlockWoodBehavior;
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

import static org.allaymc.api.block.type.BlockTypes.WOOD_TYPE;

@ExtendWith({AllayTestExtension.class})
public class HashUtilsTest {
    static BlockState testBlockState;
    static final int testBlockStateHash = -1715809305;

    @BeforeAll
    static void before() {
        testBlockState = WOOD_TYPE.getDefaultState();
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
    public void test_fnv1a_32_nbt() {
        NbtMap tag = NbtMap.builder().putString("name", "minecraft:wood")
                .putCompound("states", NbtMap.builder()
                        .putString("pillar_axis", "x")
                        .putByte("stripped_bit", (byte) 0)
                        .putString("wood_type", "acacia")
                        .build())
                .build();
        Assertions.assertEquals(testBlockStateHash, HashUtils.fnv1a_32_nbt(tag));
    }

    @Test
    public void test_hashXZ() {
        long l = HashUtils.hashXZ(13, 53);
        Assertions.assertEquals(55834574901L, l);
    }

    @Test
    public void test_getXFromHashXZ() {
        int x = HashUtils.getXFromHashXZ(55834574901L);
        Assertions.assertEquals(13, x);
    }

    @Test
    public void test_getZFromHashXZ() {
        int z = HashUtils.getZFromHashXZ(55834574901L);
        Assertions.assertEquals(53, z);
    }
}
