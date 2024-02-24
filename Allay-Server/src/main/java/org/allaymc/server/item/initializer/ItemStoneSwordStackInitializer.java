package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStoneSwordStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneSwordStackInitializer {
  static void init() {
    ItemTypes.STONE_SWORD_TYPE = ItemTypeBuilder
            .builder(ItemStoneSwordStack.class)
            .vanillaItem(VanillaItemId.STONE_SWORD)
            .build();
  }
}
