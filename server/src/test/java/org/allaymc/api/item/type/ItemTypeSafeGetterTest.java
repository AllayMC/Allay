package org.allaymc.api.item.type;

import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class ItemTypeSafeGetterTest {
    @Test
    void testItemTypeSafeGetter() {
        var itemType = ItemTypeSafeGetter.name("minecraft:coal").meta(1).itemType();
        assertEquals(ItemTypes.CHARCOAL, itemType);

        itemType = ItemTypeSafeGetter.name("minecraft:stone").meta(1).itemType();
        assertEquals(ItemTypes.GRANITE, itemType);

        itemType = ItemTypeSafeGetter.id(5).itemType();
        assertEquals(ItemTypes.PLANKS, itemType);
    }
}
