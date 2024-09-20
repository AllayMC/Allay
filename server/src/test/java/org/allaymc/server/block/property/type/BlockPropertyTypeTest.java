package org.allaymc.server.block.property.type;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BooleanPropertyType;
import org.allaymc.api.block.property.type.EnumPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author daoge_cmd
 */
class BlockPropertyTypeTest {
    @Test
    void testGetPropertyType() {
        Assertions.assertEquals(BlockPropertyType.Type.BOOLEAN, BlockPropertyType.getPropertyType(BooleanPropertyType.class));
        assertEquals(BlockPropertyType.Type.ENUM, BlockPropertyType.getPropertyType(EnumPropertyType.class));
        assertEquals(BlockPropertyType.Type.INT, BlockPropertyType.getPropertyType(IntPropertyType.class));
    }
}