package org.allaymc.api.item.recipe.descriptor;

import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.allaymc.api.item.type.ItemTypes.ACACIA_PLANKS;
import static org.allaymc.api.item.type.ItemTypes.PLANKS;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class ItemDescriptorTest {
    @Test
    void testItemTagDescriptor() {
        var descriptor = new ItemTagDescriptor(ItemTags.PLANKS);
        var plankItemStack = ACACIA_PLANKS.createItemStack(
                ItemStackInitInfo.builder().count(1).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }

    @Test
    void testDefaultDescriptor() {
        var descriptor = new ItemTypeDescriptor(PLANKS, 32767);
        var plankItemStack = PLANKS.createItemStack(
                ItemStackInitInfo.builder().meta(1).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }
}
