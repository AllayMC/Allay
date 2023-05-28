package cn.allay.block.property.type;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/15 <br>
 * Allay Project <br>
 */
class BlockPropertyTypeTest {

    @Test
    void testGetPropertyType() {
        Assertions.assertEquals(BlockPropertyType.Type.BOOLEAN, BlockPropertyType.getPropertyType(BooleanPropertyType.class));
        assertEquals(BlockPropertyType.Type.ENUM, BlockPropertyType.getPropertyType(EnumPropertyType.class));
        assertEquals(BlockPropertyType.Type.INT, BlockPropertyType.getPropertyType(IntPropertyType.class));
    }
}