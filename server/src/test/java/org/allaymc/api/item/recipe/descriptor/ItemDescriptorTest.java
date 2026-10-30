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
 * {@code ItemDescriptorTest} checks tag membership and wildcard metadata in
 * recipe ingredient descriptors. Registered plank items provide concrete
 * fixtures, allowing the assertions to verify that matching accepts a tagged
 * type and a metadata variant without requiring an exact metadata value.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class ItemDescriptorTest {
    /**
     * Matches an acacia-plank stack against the plank item-tag descriptor. The
     * assertion protects acceptance through the item type's tag membership
     * rather than a single concrete plank identity.
     */
    @Test
    void testItemTagDescriptor() {
        var descriptor = new ItemTagDescriptor(ItemTags.PLANKS);
        var plankItemStack = ACACIA_PLANKS.createItemStack(
                ItemStackInitInfo.builder().count(1).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }

    /**
     * Matches a metadata variant against a plank descriptor using
     * {@code 32767}. The assertion protects wildcard metadata acceptance when
     * the candidate retains the required item-type identity.
     */
    @Test
    void testDefaultDescriptor() {
        var descriptor = new ItemTypeDescriptor(PLANKS, 32767);
        var plankItemStack = PLANKS.createItemStack(
                ItemStackInitInfo.builder().meta(1).build()
        );
        assertTrue(descriptor.match(plankItemStack));
    }
}
