package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemReedsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemReedsStackInitializer {
  static void init() {
    ItemTypes.ITEM_REEDS_TYPE = ItemTypeBuilder
            .builder(ItemItemReedsStack.class)
            .vanillaItem(VanillaItemId.ITEM_REEDS)
            .build();
  }
}
