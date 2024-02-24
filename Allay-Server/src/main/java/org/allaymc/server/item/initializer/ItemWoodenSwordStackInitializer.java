package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWoodenSwordStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenSwordStackInitializer {
  static void init() {
    ItemTypes.WOODEN_SWORD_TYPE = ItemTypeBuilder
            .builder(ItemWoodenSwordStack.class)
            .vanillaItem(VanillaItemId.WOODEN_SWORD)
            .build();
  }
}
