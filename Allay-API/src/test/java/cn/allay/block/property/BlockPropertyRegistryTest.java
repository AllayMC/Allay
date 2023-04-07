package cn.allay.block.property;

import cn.allay.block.property.type.BlockProperty;
import cn.allay.block.property.vanilla.VanillaBlockProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
class BlockPropertyRegistryTest {

    @Test
    void testInit() {
        var registry = BlockPropertyRegistry.getInstance();
        for (BlockProperty<?> property : VanillaBlockProperties.values()) {
            assertEquals(property, registry.get(property.getName()));
        }
    }

}