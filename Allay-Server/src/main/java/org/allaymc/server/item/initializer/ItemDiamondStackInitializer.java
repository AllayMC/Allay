package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDiamondStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_TYPE = ItemTypeBuilder
            .builder(ItemDiamondStack.class)
            .vanillaItem(VanillaItemId.DIAMOND)
            .build();
  }
}
