package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStoneHoeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneHoeStackInitializer {
  static void init() {
    ItemTypes.STONE_HOE_TYPE = ItemTypeBuilder
            .builder(ItemStoneHoeStack.class)
            .vanillaItem(VanillaItemId.STONE_HOE)
            .build();
  }
}
