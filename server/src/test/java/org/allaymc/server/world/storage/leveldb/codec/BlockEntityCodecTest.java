package org.allaymc.server.world.storage.leveldb.codec;

import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code BlockEntityCodecTest} checks
 * empty block-entity records and reconstruction of a stored barrel
 * position.
 */
@ExtendWith(AllayTestExtension.class)
class BlockEntityCodecTest {

    /**
     * Serializes a collection that contains no block entities to persist. The
     * codec must return {@code null}, which the storage layer uses to delete an older
     * block-entity record.
     */
    @Test
    void testSerializeEmptyReturnsNull() {
        assertNull(BlockEntityCodec.serialize(List.of()));
    }

    /**
     * Encodes one barrel block entity and reconstructs it using a mock world
     * dimension. The result must contain one entity at the original block
     * coordinates.
     */
    @Test
    void testSerializeAndDeserialize() {
        var blockEntity = BlockEntityTypes.BARREL.createBlockEntity(
                BlockEntityInitInfo.builder()
                        .pos(3, 65, 7)
                        .build()
        );

        byte[] data = BlockEntityCodec.serialize(List.of(blockEntity));
        assertNotNull(data);

        // Block-entity reconstruction resolves the saved type in the requested world
        // dimension, so provide that lookup without starting a complete world lifecycle.
        var mockWorld = Mockito.mock(World.class);
        var mockDimension = Mockito.mock(Dimension.class);
        Mockito.when(mockWorld.getDimension(DimensionTypes.OVERWORLD)).thenReturn(mockDimension);

        var deserialized = BlockEntityCodec.deserialize(data, mockWorld, DimensionTypes.OVERWORLD);
        assertFalse(deserialized.isEmpty());

        assertEquals(1, deserialized.size());
        var recovered = deserialized.values().iterator().next();
        assertNotNull(recovered);
        assertEquals(3, recovered.getPosition().x());
        assertEquals(65, recovered.getPosition().y());
        assertEquals(7, recovered.getPosition().z());
    }
}
