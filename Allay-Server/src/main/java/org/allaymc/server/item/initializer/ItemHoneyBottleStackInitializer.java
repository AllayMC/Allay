package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHoneyBottleStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHoneyBottleStackInitializer {
  static void init() {
    ItemTypes.HONEY_BOTTLE_TYPE = ItemTypeBuilder
            .builder(ItemHoneyBottleStack.class)
            .vanillaItem(VanillaItemId.HONEY_BOTTLE)
            .build();
  }
}
