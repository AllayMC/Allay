package cn.allay.server.block.registry;

import cn.allay.api.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.api.block.type.BlockTypeRegistry;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.component.impl.attribute.VanillaItemAttributeRegistry;
import cn.allay.api.item.type.ItemTypeRegistry;
import cn.allay.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/8 <br>
 * Allay Project <br>
 */
@ExtendWith(AllayTestExtension.class)
public class RegistryTest {

    @Test
    void testBlockTypeRegistry() {
        for (var id : VanillaBlockId.values()) {
            assertNotNull(BlockTypeRegistry.getRegistry().get(id.getIdentifier()));
        }
    }

    @Test
    void testBlockAttributeRegistry() {
        //Special case
        assertNotNull(VanillaBlockAttributeRegistry.getRegistry().get(VanillaBlockId.UNKNOWN).get(-2));
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
            assertNotNull(ItemTypeRegistry.getRegistry().get(id.getIdentifier()));
        }
    }
}
