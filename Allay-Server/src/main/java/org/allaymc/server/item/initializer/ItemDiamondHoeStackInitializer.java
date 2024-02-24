package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDiamondHoeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondHoeStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_HOE_TYPE = ItemTypeBuilder
            .builder(ItemDiamondHoeStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_HOE)
            .build();
  }
}
