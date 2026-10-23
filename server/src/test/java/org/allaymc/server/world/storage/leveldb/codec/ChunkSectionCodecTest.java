package org.allaymc.server.world.storage.leveldb.codec;

import org.allaymc.api.block.property.enums.MinecraftCorner;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.datastruct.palette.Palette;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.server.world.storage.leveldb.LevelDBUtils;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;
import static org.allaymc.api.block.type.BlockTypes.OAK_WOOD;
import static org.allaymc.api.block.type.BlockTypes.STONE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code ChunkSectionCodecTest} checks palette migration, section round trips,
 * unsupported versions, and empty-section initialization across feature updates.
 */
@ExtendWith(AllayTestExtension.class)
class ChunkSectionCodecTest {

    /**
     * Loads legacy stairs and fences carrying the current storage version.
     * Missing hashes must trigger migration without consuming the following valid
     * palette entry or losing the old stair orientation.
     */
    @Test
    void upgradesMissingHashesEvenWhenTheStorageVersionMatches() {
        var oldStairs = NbtMap.builder()
                .putString("name", "minecraft:oak_stairs")
                .putCompound("states", NbtMap.builder()
                        .putByte("upside_down_bit", (byte) 1)
                        .putInt("weirdo_direction", 2)
                        .build())
                .putInt("version", ProtocolInfo.BLOCK_STATE_VERSION_NUM)
                .build();
        var oldFence = NbtMap.builder()
                .putString("name", "minecraft:oak_fence")
                .putCompound("states", NbtMap.EMPTY)
                .putInt("version", ProtocolInfo.BLOCK_STATE_VERSION_NUM)
                .build();
        var palette = new Palette<>(oldStairs);
        palette.set(1, oldFence);
        palette.set(2, BlockTypes.DIAMOND_BLOCK.getDefaultState().getBlockStateNBT());
        var bytes = LevelDBUtils.withByteBufToArray(buffer -> {
            buffer.writeByte(AllayChunkSection.CURRENT_CHUNK_SECTION_VERSION);
            buffer.writeByte(1);
            buffer.writeByte(0);
            palette.writeToStorage(buffer, state -> state);
        });

        var decoded = ChunkSectionCodec.deserialize(bytes, 0, 0, 0);

        assertNotNull(decoded);
        assertEquals(BlockTypes.OAK_STAIRS.getDefaultState()
                .setPropertyValue(UPSIDE_DOWN_BIT, true)
                .setPropertyValue(WEIRDO_DIRECTION, 2)
                .setPropertyValue(MINECRAFT_CORNER, MinecraftCorner.NONE), decoded.blockLayers()[0].get(0));
        assertEquals(BlockTypes.OAK_FENCE.getDefaultState(), decoded.blockLayers()[0].get(1));
        assertEquals(BlockTypes.DIAMOND_BLOCK.getDefaultState(), decoded.blockLayers()[0].get(2));
    }

    /**
     * Saves a corner stair beside connected glass, copper bars, and tripwire.
     * Reloading the section must preserve every selected connection and corner
     * value instead of returning each type's default state.
     */
    @Test
    void preservesNewCornerAndConnectionStates() {
        var section = new AllayChunkSection((byte) 0);
        var states = section.blockLayers()[0];
        states.set(0, BlockTypes.OAK_STAIRS.getDefaultState().setPropertyValue(MINECRAFT_CORNER, MinecraftCorner.INNER_LEFT));
        states.set(1, BlockTypes.GLASS_PANE.getDefaultState().setPropertyValue(MINECRAFT_CONNECTION_EAST, true));
        states.set(2, BlockTypes.COPPER_BARS.getDefaultState().setPropertyValue(MINECRAFT_CONNECTION_NORTH, true));
        states.set(3, BlockTypes.TRIP_WIRE.getDefaultState().setPropertyValue(MINECRAFT_CONNECTION_SOUTH, true));

        var decoded = ChunkSectionCodec.deserialize(ChunkSectionCodec.serialize(section, 0), 0, 0, 0);

        assertNotNull(decoded);
        for (int i = 0; i < 4; i++) {
            assertEquals(states.get(i), decoded.blockLayers()[0].get(i));
        }
    }

    /**
     * Stores distinct block states in both supported section layers before
     * encoding. Decoding must preserve the sampled positions and their layer
     * identities rather than merging the palettes.
     */
    @Test
    void testSerializeAndDeserialize() {
        var section = new AllayChunkSection((byte) 0);

        section.setBlockState(0, 0, 0, OAK_WOOD.getDefaultState(), 0);
        section.setBlockState(1, 1, 1, STONE.getDefaultState(), 0);

        section.setBlockState(2, 2, 2, OAK_WOOD.getDefaultState(), 1);

        byte[] data = ChunkSectionCodec.serialize(section, 0);
        assertNotNull(data);

        AllayChunkSection deserialized = ChunkSectionCodec.deserialize(data, 0, 0, 0);
        assertNotNull(deserialized);

        assertEquals(OAK_WOOD.getDefaultState(), deserialized.getBlockState(0, 0, 0, 0));
        assertEquals(STONE.getDefaultState(), deserialized.getBlockState(1, 1, 1, 0));
        assertEquals(OAK_WOOD.getDefaultState(), deserialized.getBlockState(2, 2, 2, 1));
    }

    /**
     * Supplies a section record whose leading version byte has no supported
     * decoder. The codec must return {@code null} so its storage caller can handle that
     * unsupported section.
     */
    @Test
    void testDeserializeUnknownVersionReturnsNull() {
        // Version 99 selects the unsupported-header path before any palette payload is
        // decoded, isolating fallback behavior from malformed-palette handling.
        byte[] data = new byte[]{99, 0, 0, 0};
        AllayChunkSection result = ChunkSectionCodec.deserialize(data, 0, 0, 0);
        assertNull(result);
    }

    /**
     * Starts with a section array containing no allocated section objects.
     * Filling the array must create every section with its coordinate offset
     * from the dimension's minimum section height.
     */
    @Test
    void testFillNullSections() {
        var dimensionType = DimensionTypes.OVERWORLD;
        var sections = new AllayChunkSection[dimensionType.chunkSectionCount()];
        assertNull(sections[0]);

        ChunkSectionCodec.fillNullSections(sections, dimensionType);

        for (int i = 0; i < sections.length; i++) {
            assertNotNull(sections[i], "Section at index " + i + " should not be null after fill");
            assertEquals((byte) (i + dimensionType.minSectionY()), sections[i].sectionY());
        }
    }
}
