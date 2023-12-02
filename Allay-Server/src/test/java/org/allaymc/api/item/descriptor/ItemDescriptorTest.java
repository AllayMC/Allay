package org.allaymc.api.item.descriptor;

import org.allaymc.api.data.VanillaItemTags;
import org.allaymc.api.item.init.SimpleItemStackInitInfo;
import org.allaymc.api.item.interfaces.planks.ItemPlanksStack;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class ItemDescriptorTest {
    @Test
    void testItemTagDescriptor() {
        var descriptor = new ItemTagDescriptor(VanillaItemTags.PLANKS);
        var plankItemStack = ItemPlanksStack.PLANKS_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(1).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }

    @Test
    void testDefaultDescriptor() {
        var descriptor = new DefaultDescriptor(ItemPlanksStack.PLANKS_TYPE, 32767);
        var plankItemStack = ItemPlanksStack.PLANKS_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().meta(1).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }

    @Test
    void testItemDescriptorWithCount() {
        var descriptor = new ItemDescriptorWithCount(new DefaultDescriptor(ItemPlanksStack.PLANKS_TYPE), 32);
        var plankItemStack = ItemPlanksStack.PLANKS_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(32).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }
}
