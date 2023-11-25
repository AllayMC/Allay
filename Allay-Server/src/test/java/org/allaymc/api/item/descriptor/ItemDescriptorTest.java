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
    void testItemIdentifierDescriptor() {
        var descriptor = new ItemIdentifierDescriptor(ItemPlanksStack.PLANKS_TYPE.getIdentifier());
        var plankItemStack = ItemPlanksStack.PLANKS_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(1).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }

    @Test
    void testItemIdentifierAndMetaDescriptor() {
        var descriptor = new ItemIdentifierAndMetaDescriptor(ItemPlanksStack.PLANKS_TYPE.getIdentifier(), 1);
        var plankItemStack = ItemPlanksStack.PLANKS_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(1).meta(1).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }

    @Test
    void testItemDescriptorWithCount() {
        var descriptor = new ItemDescriptorWithCount(new ItemIdentifierDescriptor(ItemPlanksStack.PLANKS_TYPE.getIdentifier()), 32);
        var plankItemStack = ItemPlanksStack.PLANKS_TYPE.createItemStack(
                SimpleItemStackInitInfo.builder().count(32).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }
}
