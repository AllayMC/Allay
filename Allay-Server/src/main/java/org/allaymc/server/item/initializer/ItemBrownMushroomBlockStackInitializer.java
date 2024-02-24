package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBrownMushroomBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownMushroomBlockStackInitializer {
  static void init() {
    ItemTypes.BROWN_MUSHROOM_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemBrownMushroomBlockStack.class)
            .vanillaItem(VanillaItemId.BROWN_MUSHROOM_BLOCK)
            .build();
  }
}
