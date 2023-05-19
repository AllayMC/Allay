package cn.allay.block.registry;

import cn.allay.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.palette.VanillaBlockPaletteRegistry;
import cn.allay.block.property.BlockPropertyTypeRegistry;
import cn.allay.block.property.type.BlockPropertyType;
import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.type.BlockTypeRegistry;
import cn.allay.item.component.impl.attribute.VanillaItemAttributeRegistry;
import cn.allay.item.data.VanillaItemId;
import cn.allay.item.type.ItemTypeRegistry;
import cn.allay.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
@ExtendWith(AllayTestExtension.class)
public class RegistryTest {

    @Test
    void testBlockTypeRegistry() {
        for (var id : VanillaBlockId.values()) {
            assertNotNull(BlockTypeRegistry.getRegistry().get(id.getNamespaceId()));
        }
    }

    @Test
    void testBlockAttributeRegistry() {
        //Special case
        assertNotNull(VanillaBlockAttributeRegistry.getRegistry().get(VanillaBlockId.UNKNOWN).get(-2));
    }

    @Test
    void testBlockPropertyRegistry() {
        for (BlockPropertyType<?> property : VanillaBlockPropertyTypes.values()) {
            assertEquals(property, BlockPropertyTypeRegistry.getRegistry().get(property.getName()));
        }
    }

    @Test
    void testBlockPaletteRegistry() {
        for (var id : VanillaBlockId.values()) {
            assertNotNull(VanillaBlockPaletteRegistry.getRegistry().get(id));
        }
    }

    @Test
    void testItemAttributeRegistry() {
        for (var id : VanillaItemId.values()) {
            assertNotNull(VanillaItemAttributeRegistry.getRegistry().get(id));
        }
    }

    @Test
    void testItemTypeRegistry() {
        for (var id : VanillaItemId.values()) {
            assertNotNull(ItemTypeRegistry.getRegistry().get(id.getNamespaceId()));
        }
    }
}
