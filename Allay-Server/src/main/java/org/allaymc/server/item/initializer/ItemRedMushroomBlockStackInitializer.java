package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRedMushroomBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedMushroomBlockStackInitializer {
  static void init() {
    ItemTypes.RED_MUSHROOM_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemRedMushroomBlockStack.class)
            .vanillaItem(VanillaItemId.RED_MUSHROOM_BLOCK)
            .build();
  }
}
