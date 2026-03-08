package org.allaymc.server.world.storage.leveldb.codec;

import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class BlockEntityCodecTest {

    @Test
    void testSerializeEmptyReturnsNull() {
        assertNull(BlockEntityCodec.serialize(List.of()));
    }

    @Test
    void testSerializeAndDeserialize() {
        var blockEntity = BlockEntityTypes.BARREL.createBlockEntity(
                BlockEntityInitInfo.builder()
                        .pos(3, 65, 7)
                        .build()
        );

        byte[] data = BlockEntityCodec.serialize(List.of(blockEntity));
        assertNotNull(data);

        // Set up mock world and dimension
        var mockWorld = Mockito.mock(World.class);
        var mockDimension = Mockito.mock(Dimension.class);
        Mockito.when(mockWorld.getDimension(DimensionInfo.OVERWORLD.dimensionId())).thenReturn(mockDimension);

        var deserialized = BlockEntityCodec.deserialize(data, mockWorld, DimensionInfo.OVERWORLD);
        assertFalse(deserialized.isEmpty());

        // Find the block entity - there should be exactly one
        assertEquals(1, deserialized.size());
        var recovered = deserialized.values().iterator().next();
        assertNotNull(recovered);
        assertEquals(3, recovered.getPosition().x());
        assertEquals(65, recovered.getPosition().y());
        assertEquals(7, recovered.getPosition().z());
    }
}
