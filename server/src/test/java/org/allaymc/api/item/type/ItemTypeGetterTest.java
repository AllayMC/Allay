package org.allaymc.api.item.type;

import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code ItemTypeGetterTest} verifies compatibility lookups for legacy names, metadata, and numeric IDs.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemTypeGetterTest {
    /**
     * Resolves legacy coal and stone metadata alongside a numeric plank ID.
     * The compatibility lookup must produce charcoal, granite, and planks through
     * the initialized server NBT loader.
     */
    @Test
    void testItemTypeGetter() {
        var itemType = ItemTypeGetter.name("minecraft:coal").meta(1).itemType();
        assertEquals(ItemTypes.CHARCOAL, itemType);

        itemType = ItemTypeGetter.name("minecraft:stone").meta(1).itemType();
        assertEquals(ItemTypes.GRANITE, itemType);

        itemType = ItemTypeGetter.id(5).itemType();
        assertEquals(ItemTypes.PLANKS, itemType);
    }
}
