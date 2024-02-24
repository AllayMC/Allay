package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemHopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemHopperStackInitializer {
  static void init() {
    ItemTypes.ITEM_HOPPER_TYPE = ItemTypeBuilder
            .builder(ItemItemHopperStack.class)
            .vanillaItem(VanillaItemId.ITEM_HOPPER)
            .build();
  }
}
