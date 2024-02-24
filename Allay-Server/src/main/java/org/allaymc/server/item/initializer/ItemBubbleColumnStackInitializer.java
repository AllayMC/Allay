package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBubbleColumnStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBubbleColumnStackInitializer {
  static void init() {
    ItemTypes.BUBBLE_COLUMN_TYPE = ItemTypeBuilder
            .builder(ItemBubbleColumnStack.class)
            .vanillaItem(VanillaItemId.BUBBLE_COLUMN)
            .build();
  }
}
