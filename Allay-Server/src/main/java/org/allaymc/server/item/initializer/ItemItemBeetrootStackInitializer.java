package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemBeetrootStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBeetrootStackInitializer {
  static void init() {
    ItemTypes.ITEM_BEETROOT_TYPE = ItemTypeBuilder
            .builder(ItemItemBeetrootStack.class)
            .vanillaItem(VanillaItemId.ITEM_BEETROOT)
            .build();
  }
}
