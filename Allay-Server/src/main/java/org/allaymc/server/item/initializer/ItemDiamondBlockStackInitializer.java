package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDiamondBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondBlockStackInitializer {
  static void init() {
    ItemTypes.DIAMOND_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemDiamondBlockStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_BLOCK)
            .build();
  }
}
