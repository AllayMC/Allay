package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBrickBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrickBlockStackInitializer {
  static void init() {
    ItemTypes.BRICK_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemBrickBlockStack.class)
            .vanillaItem(VanillaItemId.BRICK_BLOCK)
            .build();
  }
}
