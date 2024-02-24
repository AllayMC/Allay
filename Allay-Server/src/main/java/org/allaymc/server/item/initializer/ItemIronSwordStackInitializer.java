package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemIronSwordStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronSwordStackInitializer {
  static void init() {
    ItemTypes.IRON_SWORD_TYPE = ItemTypeBuilder
            .builder(ItemIronSwordStack.class)
            .vanillaItem(VanillaItemId.IRON_SWORD)
            .build();
  }
}
