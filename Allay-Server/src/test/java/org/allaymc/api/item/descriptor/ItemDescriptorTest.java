package org.allaymc.api.item.descriptor;

import org.allaymc.api.item.initinfo.SimpleItemStackInitInfo;
import org.allaymc.api.item.tag.ItemTags;
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
                SimpleItemStackInitInfo.builder().count(1).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }

    @Test
    void testDefaultDescriptor() {
        var descriptor = new DefaultDescriptor(PLANKS, 32767);
        var plankItemStack = PLANKS.createItemStack(
                SimpleItemStackInitInfo.builder().meta(1).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }

    @Test
    void testItemDescriptorWithCount() {
        var descriptor = new ItemDescriptorWithCount(new DefaultDescriptor(PLANKS), 32);
        var plankItemStack = PLANKS.createItemStack(
                SimpleItemStackInitInfo.builder().count(32).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }
}
