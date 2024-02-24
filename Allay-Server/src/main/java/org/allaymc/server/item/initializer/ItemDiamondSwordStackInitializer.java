package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDiamondSwordStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondSwordStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_SWORD_TYPE = ItemTypeBuilder
            .builder(ItemDiamondSwordStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_SWORD)
            .build();
  }
}
