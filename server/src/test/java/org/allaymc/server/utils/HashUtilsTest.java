package org.allaymc.server.utils;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import static org.allaymc.api.block.type.BlockTypes.OAK_WOOD;

/**
 * {@code HashUtilsTest} checks state-hash fixtures and reversible coordinate
 * encodings used by chunks.
 *
 * @author daoge_cmd
 */
@ExtendWith({AllayTestExtension.class})
public class HashUtilsTest {
    // testBlockStateHash is the expected hash for encodings derived from
    // testBlockState. The shared server fixture supplies that state before the
    // property-based assertions run; direct NBT construction checks the same hash.
    /** Expected oak-wood state hash. */
    static final int testBlockStateHash = 1622499771;
    /**
     * {@code testBlockState} holds the default oak-wood state. {@code before}
     * selects it after the shared server fixture initializes block types and
     * state registries.
     */
    static BlockState testBlockState;

    /**
     * Selects the initialized oak-wood default state for subsequent hash
     * assertions.
     */
    @BeforeAll
    static void before() {
        testBlockState = OAK_WOOD.getDefaultState();
    }

    /**
     * Supplies the default oak-wood properties through the list overload. Its
     * computed state hash must match the fixed fixture value used by the
     * equivalent array and NBT cases.
     */
    @Test
    public void testComputeBlockStateHash1() {
        var value = HashUtils.computeBlockStateHash(testBlockState.getBlockType().getIdentifier(), new ArrayList<>(testBlockState.getPropertyValues().values()));
        Assertions.assertEquals(testBlockStateHash, value);
    }

    /**
     * Supplies the default oak-wood properties through the array overload. Its
     * computed state hash must match the fixed fixture value used by the
     * equivalent list and NBT cases.
     */
    @Test
    public void testComputeBlockStateHash2() {
        BlockPropertyType.BlockPropertyValue<?, ?, ?>[] array = testBlockState.getPropertyValues().values().toArray(BlockPropertyType.BlockPropertyValue<?, ?, ?>[]::new);
        var value = HashUtils.computeBlockStateHash(testBlockState.getBlockType().getIdentifier(), array);
        Assertions.assertEquals(testBlockStateHash, value);
    }

    /**
     * Builds the oak-wood compound directly with its vertical pillar property.
     * Little-endian NBT hashing must produce the same fixture hash as the
     * property-value helpers used by block types.
     */
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

    /**
     * Packs positive X and Z coordinates into one long key. The result must
     * match the fixed bit-layout fixture, protecting which coordinate occupies
     * each half.
     */
    @Test
    public void testHashXZ() {
        long l = HashUtils.hashXZ(13, 53);
        Assertions.assertEquals(55834574901L, l);
    }

    /**
     * Decodes both coordinate halves from the fixed long-key fixture. The
     * extracted X and Z values must retain their original positions instead of
     * swapping the two halves.
     */
    @Test
    public void testGetXZFromHashXZ() {
        Assertions.assertEquals(13, HashUtils.getXFromHashXZ(55834574901L));
        Assertions.assertEquals(53, HashUtils.getZFromHashXZ(55834574901L));
    }

    /**
     * Packs equal horizontal positions with positive and negative absolute
     * heights. Both results must match their fixed integer fixtures, protecting
     * the height bias within the shared coordinate layout.
     */
    @Test
    public void testHashChunkXYZ() {
        int l = HashUtils.hashChunkXYZ(8, 64, 8);
        Assertions.assertEquals(-2013264888, l);

        int l2 = HashUtils.hashChunkXYZ(8, -64, 8);
        Assertions.assertEquals(-2013266936, l2);
    }

    /**
     * Extracts local horizontal coordinates and both signs of absolute height.
     * The fixture keys must decode to the original values, including the
     * negative height represented through the encoding bias.
     */
    @Test
    public void testGetXYZFromHashChunkXYZ() {
        Assertions.assertEquals(8, HashUtils.getXFromHashChunkXYZ(-2013264888));
        Assertions.assertEquals(64, HashUtils.getYFromHashChunkXYZ(-2013264888));
        Assertions.assertEquals(-64, HashUtils.getYFromHashChunkXYZ(-2013266936));
        Assertions.assertEquals(8, HashUtils.getZFromHashChunkXYZ(-2013264888));
    }
}
