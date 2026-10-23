package org.allaymc.server.block.property.type;

import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BooleanPropertyType;
import org.allaymc.api.block.property.type.EnumPropertyType;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code BlockPropertyTypeTest} verifies category lookup for each concrete property
 * implementation class.
 *
 * @author daoge_cmd
 */
class BlockPropertyTypeTest {
    /**
     * Queries the category for the boolean, enum, and integer implementation
     * classes. Each exact class must map to its matching storage category for
     * consumers that inspect property representation.
     */
    @Test
    void testGetPropertyType() {
        assertEquals(BlockPropertyType.Type.BOOLEAN, BlockPropertyType.getPropertyType(BooleanPropertyType.class));
        assertEquals(BlockPropertyType.Type.ENUM, BlockPropertyType.getPropertyType(EnumPropertyType.class));
        assertEquals(BlockPropertyType.Type.INT, BlockPropertyType.getPropertyType(IntPropertyType.class));
    }
}