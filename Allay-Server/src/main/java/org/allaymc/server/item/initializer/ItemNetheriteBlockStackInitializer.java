package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetheriteBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteBlockStackInitializer {
  static void init() {
    ItemTypes.NETHERITE_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteBlockStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_BLOCK)
            .build();
  }
}
