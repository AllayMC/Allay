package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemBedStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBedStackInitializer {
  static void init() {
    ItemTypes.ITEM_BED_TYPE = ItemTypeBuilder
            .builder(ItemItemBedStack.class)
            .vanillaItem(VanillaItemId.ITEM_BED)
            .build();
  }
}
