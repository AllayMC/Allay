package cn.allay.block.property;

import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
class BlockPropertyTypeRegistryTest {

    @Test
    void testInit() {
        var registry = BlockPropertyTypeRegistry.getInstance();
        for (BlockPropertyType<?> property : VanillaBlockPropertyTypes.values()) {
            assertEquals(property, registry.get(property.getName()));
        }
    }

}